package com.casa.booking.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;

import com.casa.booking.acknowledge.BookingNotification;
import com.casa.booking.dao.BookingInformationDAO;
import com.casa.booking.dao.CompanyDAO;
import com.casa.booking.dao.PdfTemplateDAO;
import com.casa.booking.dao.StoreDAO;
import com.casa.booking.dao.UserDAO;
import com.casa.booking.dto.BookingDetailsDTO;
import com.casa.booking.model.BookingInformation;
import com.casa.booking.model.Company;
import com.casa.booking.model.PdfTemplate;
import com.casa.booking.model.Store;
import com.casa.booking.model.User;
import com.casa.booking.pdf.BookingPDFHandler;
import com.casa.booking.pdf.ConvertXMLFormat;
import com.casa.booking.util.CasaConstants;
import com.casa.booking.util.PropsReader;
import com.casa.jaxb.acknowledge.TransactionAcknowledge;
import com.casa.jaxb.booking.BookingDetails;
import com.casa.jaxb.transaction.Transaction;

public class BookingService {
	
	@Autowired
	private BookingInformationDAO bookingInformationDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private PdfTemplateDAO pdfTemplateDAO;
	
	//@Autowired
	//private OffersDAO offersDAO;
	
	@Autowired
	private ConvertXMLFormat convertXMLFormat;
	
	@Autowired
	private BookingPDFHandler bookingPDFHandler;
	
	@Autowired
	private BookingNotification bookingNotification;
	
	/*
	public BookingService(ServiceConfig config){
		this.bookingDetailsDAO=config.bookingDetailsDAO();
		this.convertXMLFormat=config.convertXMLFormat();
		this.bookingPDFHandler=config.bookingPDFHandler();
	}*/
	
	public List<BookingDetailsDTO> getBookingDetails(String type, String query, String user, String role) throws ParseException{
		List<BookingDetailsDTO> dtos = new ArrayList<BookingDetailsDTO>();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		//SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		List<BookingInformation> details = null;
		User userObj = userDAO.getUserById(user);
		if(userObj==null)
			return dtos;
		List<Long> storeIds=null;
		if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
			List<Store> stores = storeDAO.getStoresByDistrict(userObj.getStore().getDistrict(), userObj.getStore().getCompany().getCompanyId());
			if(stores!=null){
				storeIds=new ArrayList<Long>();
				for(Store st : stores){
					storeIds.add(st.getStoreId());
				}
			}
		}
		if(type.equalsIgnoreCase("day")){
			Date date = format1.parse(format1.format(new Date()));
			if(query.equalsIgnoreCase("today")){
				details = bookingInformationDAO.getByBookingDate(date, role, userObj, storeIds);
			}else{
				Calendar calendar1 = Calendar.getInstance();
				calendar1.add(Calendar.DAY_OF_MONTH, Integer.parseInt(query.substring(query.indexOf("-"))));
				calendar1.set(Calendar.HOUR_OF_DAY, 0);
				calendar1.set(Calendar.MINUTE, 0);
				calendar1.set(Calendar.SECOND, 0);
				
				Calendar calendar2 = Calendar.getInstance();
				calendar2.set(Calendar.HOUR_OF_DAY, 23);
				calendar2.set(Calendar.MINUTE, 59);
				calendar2.set(Calendar.SECOND, 59);
				//System.out.println("#### "+calendar1.getTime());
				//System.out.println("#### "+calendar2.getTime());
				details = bookingInformationDAO.getBetweenTwoBookingDates(calendar1.getTime(), calendar2.getTime(), role, userObj, storeIds);
			}
		} if(type.equalsIgnoreCase("week")){
			Date date1 = format1.parse(format1.format(new Date()));
			Calendar calendar = Calendar.getInstance ();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) -7);
			Date date2 = format1.parse(format1.format(calendar.getTime()));
			details = bookingInformationDAO.getBetweenTwoBookingDates(date1, date2, role, userObj, storeIds);
		} if(type.equalsIgnoreCase("month")){
			//Date date1 = format1.parse(format1.format(new Date()));
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			if(query.equalsIgnoreCase("thismonth")){
				calendar1.set(Calendar.DAY_OF_MONTH, 1);
				calendar1.set(Calendar.HOUR_OF_DAY, 0);
				calendar1.set(Calendar.MINUTE, 0);
				calendar1.set(Calendar.SECOND, 0);
				
				calendar2.set(Calendar.DAY_OF_MONTH, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
				calendar2.set(Calendar.HOUR_OF_DAY, 23);
				calendar2.set(Calendar.MINUTE, 59);
				calendar2.set(Calendar.SECOND, 59);
			}else{
				//Date dt1 = format2.parse("01/"+query);
				//Date dt2 = format2.parse("01/"+queryString);
				//System.out.println("Int >> "+Integer.parseInt(queryString.substring(0, queryString.indexOf("/"))));
				//Calendar calendar1 = Calendar.getInstance();
				calendar1.set(Calendar.DAY_OF_MONTH, 1);
				calendar1.set(Calendar.MONTH, Integer.parseInt(query.substring(0, query.indexOf("/")))-1);
				calendar1.set(Calendar.YEAR, Integer.parseInt(query.substring(query.indexOf("/")+1)));
				calendar1.set(Calendar.HOUR_OF_DAY, 0);
				calendar1.set(Calendar.MINUTE, 0);
				calendar1.set(Calendar.SECOND, 0);
				//Calendar calendar2 = Calendar.getInstance();
				calendar2.set(Calendar.DAY_OF_MONTH, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
				calendar2.set(Calendar.MONTH, Integer.parseInt(query.substring(0, query.indexOf("/")))-1);
				calendar2.set(Calendar.YEAR, Integer.parseInt(query.substring(query.indexOf("/")+1)));
				calendar2.set(Calendar.HOUR_OF_DAY, 23);
				calendar2.set(Calendar.MINUTE, 59);
				calendar2.set(Calendar.SECOND, 59);
			}
			//System.out.println(calendar1.getTime());
			//System.out.println(calendar2.getTime());
			//Date date2 = format1.parse(format1.format(calendar.getTime()));
			details = bookingInformationDAO.getBetweenTwoBookingDates(calendar1.getTime(), calendar2.getTime(), role, userObj, storeIds);
		}
		if(details!=null){
			for(BookingInformation bd : details){
				dtos.add(convertBookingDetailsToDTO(bd));
			}
		}
		return dtos;
	}
	
	public List<BookingDetailsDTO> searchBookingDetails(String type, String queryString, String user, String role) throws ParseException{
		List<BookingDetailsDTO> dtos = new ArrayList<BookingDetailsDTO>();
		List<BookingInformation> details = null;
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		//SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		User userObj = userDAO.getUserById(user);
		if(userObj==null)
			return dtos;
		
		List<Long> storeIds=null;
		if(role.equalsIgnoreCase(CasaConstants.ROLE_DISTRICT_MANAGER)){
			List<Store> stores = storeDAO.getStoresByDistrict(userObj.getStore().getDistrict(), userObj.getStore().getCompany().getCompanyId());
			if(stores!=null){
				storeIds=new ArrayList<Long>();
				for(Store st : stores){
					storeIds.add(st.getStoreId());
				}
			}
		}
		
		if(type.equalsIgnoreCase("pnr")){
			details = bookingInformationDAO.getByPNR(queryString, role, userObj, storeIds);
		} else if(type.equalsIgnoreCase("travellerName")){
			details = bookingInformationDAO.getByTravellerName(queryString, role, userObj, storeIds);
		} else if(type.equalsIgnoreCase("fromLocation")){
			details = bookingInformationDAO.getByFromLocation(queryString, role, userObj, storeIds);
		} else if(type.equalsIgnoreCase("toLocation")){
			details = bookingInformationDAO.getByToLocation(queryString, role, userObj, storeIds);
		} else if(type.equalsIgnoreCase("bookingDate")){
			//Date dt1 = format2.parse("01/"+queryString);
			//Date dt2 = format2.parse("01/"+queryString);
			//System.out.println("Int >> "+Integer.parseInt(queryString.substring(0, queryString.indexOf("/"))));
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.DAY_OF_MONTH, 1);
			calendar1.set(Calendar.MONTH, Integer.parseInt(queryString.substring(0, queryString.indexOf("/")))-1);
			calendar1.set(Calendar.YEAR, Integer.parseInt(queryString.substring(queryString.indexOf("/")+1)));
			calendar1.set(Calendar.HOUR_OF_DAY, 0);
			calendar1.set(Calendar.MINUTE, 0);
			calendar1.set(Calendar.SECOND, 0);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(Calendar.DAY_OF_MONTH, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
			calendar2.set(Calendar.MONTH, Integer.parseInt(queryString.substring(0, queryString.indexOf("/")))-1);
			calendar2.set(Calendar.YEAR, Integer.parseInt(queryString.substring(queryString.indexOf("/")+1)));
			calendar2.set(Calendar.HOUR_OF_DAY, 23);
			calendar2.set(Calendar.MINUTE, 59);
			calendar2.set(Calendar.SECOND, 59);
			//Search for bookings in a Month
			//details = bookingInformationDAO.getByBookingDate(format1.parse(queryString), role, userObj, storeIds);
			//System.out.println(">>>>"+calendar1.getTime());
			//System.out.println(">>>>"+calendar2.getTime());
			details = bookingInformationDAO.getBetweenTwoBookingDates(calendar1.getTime(), calendar2.getTime(), role, userObj, storeIds);
		} else if(type.equalsIgnoreCase("travelDate")){
			details = bookingInformationDAO.getByTravelDate(format1.parse(queryString), role, userObj, storeIds);
		} else if(type.equalsIgnoreCase("userId")){
			details = bookingInformationDAO.getByUserId(queryString, role, userObj, storeIds);
		} else if(type.equalsIgnoreCase("amount")){
			queryString=queryString.trim();
			if(queryString.indexOf("-")!=-1){
				String[] values = queryString.split("-");
				double stAmt = Double.parseDouble(values[0].trim());
				double endAmt = Double.parseDouble(values[1].trim());
				details = bookingInformationDAO.getByAmountRange(stAmt, endAmt, role, userObj, storeIds);
			}else {
				Double queryAmount = Double.parseDouble(queryString);
				double startAmount = queryAmount.doubleValue()-100;
				if(startAmount<0)
					startAmount=0;
				double endAmount = queryAmount.doubleValue()+100;
				details = bookingInformationDAO.getByAmountRange(startAmount, endAmount, role, userObj, storeIds);
			}
		} else if(type.equalsIgnoreCase("airline")){
			details = bookingInformationDAO.getByAirline(queryString, role, userObj, storeIds);
		}
		if(details!=null){
			for(BookingInformation bd : details){
				dtos.add(convertBookingDetailsToDTO(bd));
			}
		}
		return dtos;
	}
	
	public String processXML(String xml) throws Exception{
		PropsReader propsReader = PropsReader.getInstance();
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		JAXBContext jaxc = JAXBContext.newInstance ("com.casa.jaxb.transaction");
	    Unmarshaller un = jaxc.createUnmarshaller ();
	    Transaction ts = (Transaction)un.unmarshal(is);
	    BookingDetails bd =  convertXMLFormat.convertTransactionXML(ts);
	    //ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    String fileName = System.currentTimeMillis()+".xml";
	    File file = new File(propsReader.getPropValue("xml.file.path")+fileName);
	    FileOutputStream fop = new FileOutputStream(file);
	    if (!file.exists()) {
			file.createNewFile();
		}
	    byte[] contentInBytes = xml.getBytes();
	    
		fop.write(contentInBytes);
		fop.flush();
		fop.close();
		
		String date_pattern="EEE, MMM dd yyy hh:mm a";
		SimpleDateFormat format2 = new SimpleDateFormat(date_pattern);
		List<BookingInformation> list = bookingInformationDAO.getByPNR(bd.getPnr());
		BookingInformation binfo;
		UUID uuid = UUID.randomUUID();
		if(list!=null && list.size()>0){
			binfo = list.get(0);
		}else{
			binfo = new BookingInformation();
			binfo.setUuid(uuid.toString());
		}
	    binfo.setAgentCode(bd.getAgent().getCode().toLowerCase());
	    binfo.setAgentEmail(bd.getAgent().getEmail());
	    binfo.setAgentName(bd.getAgent().getName());
	    binfo.setBookingDate(format2.parse(bd.getBookingDate()));
	    binfo.setPnr(bd.getPnr());
	    String only_date_pattern="EEE, MMM dd yyy";
	    SimpleDateFormat format3 = new SimpleDateFormat(only_date_pattern);
	    
	    User userObj = userDAO.getUserByOdyId(ts.getBooking().getUser().getID().toString());
	    if(userObj!=null){
	    	binfo.setStoreId(userObj.getStore().getStoreId());
	    	bd.getAgent().setAffiliate(userObj.getStore().getCompany().getCompanyName());
	    	bd.getAgent().setAddress(userObj.getStore().getAddress());
	    	bd.getAgent().setCity(userObj.getStore().getCity());
	    	bd.getAgent().setCountry(userObj.getStore().getCountry());
	    	bd.getAgent().setState(userObj.getStore().getState());
	    	bd.getAgent().setZipCode(userObj.getStore().getZip());
	    }else{
	    	System.out.println("**** No User found in UserTable for ID :"+ts.getBooking().getUser().getID().toString());
	    }
	    
	    StringWriter stringWriter = new StringWriter();
	    JAXBContext context = JAXBContext.newInstance("com.casa.jaxb.booking");
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(bd, stringWriter);
		String convertedXml = stringWriter.toString();
		
	    binfo.setRequestorId(ts.getRequestorID());
	    binfo.setUserId(ts.getBooking().getUser().getID().toString());
	    binfo.setCompanyCode(ts.getBooking().getAffiliate().getID().longValue());
	    if(bd.getFlights().getFlight()!=null && bd.getFlights().getFlight().size()==1){
	    	binfo.setFromLocation(bd.getFlights().getFlight().get(0).getDeparturePlace());
	    	binfo.setToLocation(bd.getFlights().getFlight().get(0).getArrivalPlace());
	    	binfo.setTravelDate(format3.parse(bd.getFlights().getFlight().get(0).getDepartureDate()));
	    }else if(bd.getFlights().getFlight()!=null && bd.getFlights().getFlight().size()>1){
	    	binfo.setFromLocation(bd.getFlights().getFlight().get(0).getDeparturePlace());
	    	binfo.setToLocation(bd.getFlights().getFlight().get(bd.getFlights().getFlight().size()-1).getDeparturePlace());
	    	binfo.setTravelDate(format3.parse(bd.getFlights().getFlight().get(0).getDepartureDate()));
	    }
	    if(bd.getFlights().getFlight()!=null){
	    	Set<String> airlinesSet=new HashSet<String>();
	    	for(com.casa.jaxb.booking.Flight flight : bd.getFlights().getFlight()){
	    		airlinesSet.add(flight.getAirlineName());
	    	}
	    	String airline=null;
	    	for(String al : airlinesSet){
	    		if(airline==null)
	    			airline=al;
	    		else
	    			airline=airline+", "+al;
	    	}
	    	binfo.setAirline(airline);
	    }
	    binfo.setTravellerName1(bd.getCustomers().getCustomer().size()>=1?getTravellerFullName(bd.getCustomers().getCustomer().get(0)):null);
	    binfo.setTravellerName2(bd.getCustomers().getCustomer().size()>=2?getTravellerFullName(bd.getCustomers().getCustomer().get(1)):null);
	    binfo.setTravellerName3(bd.getCustomers().getCustomer().size()>=3?getTravellerFullName(bd.getCustomers().getCustomer().get(2)):null);
	    binfo.setTravellerName4(bd.getCustomers().getCustomer().size()>=4?getTravellerFullName(bd.getCustomers().getCustomer().get(3)):null);
	    binfo.setTravellerName5(bd.getCustomers().getCustomer().size()>=5?getTravellerFullName(bd.getCustomers().getCustomer().get(4)):null);
	    binfo.setTravellerName6(bd.getCustomers().getCustomer().size()>=6?getTravellerFullName(bd.getCustomers().getCustomer().get(5)):null);
	    binfo.setTravellerName7(bd.getCustomers().getCustomer().size()>=7?getTravellerFullName(bd.getCustomers().getCustomer().get(6)):null);
	    binfo.setTravellerName8(bd.getCustomers().getCustomer().size()>=8?getTravellerFullName(bd.getCustomers().getCustomer().get(7)):null);
	    binfo.setAmount(Double.parseDouble(bd.getPayment().getTotal()));
	    binfo.setXmlContent(convertedXml);
	    binfo.setXmlFileName(fileName);
	    
	    PdfTemplate template = pdfTemplateDAO.getStoreTemplate(userObj.getStore().getStoreId(), userObj.getStore().getCompany().getCompanyId());
	    if(template==null)
	    	template = pdfTemplateDAO.getRegionTemplate(userObj.getStore().getRegion(), userObj.getStore().getCompany().getCompanyId());
	    if(template==null)
	    	template = pdfTemplateDAO.getAffiliateTemplate(userObj.getStore().getCompany().getCompanyId());
	    if(template!=null)
	    	binfo.setPdfTemplateName(template.getFileName());
	    else
	    	binfo.setPdfTemplateName(CasaConstants.DEFAULT_TEMPLATE);
	    /*
	    Offers offer = offersDAO.getStoreOffer(userObj.getStore().getStoreId(), userObj.getStore().getCompany().getCompanyId(), new Date());
	    if(offer==null)
	    	offer = offersDAO.getRegionOffer(userObj.getStore().getRegion(), userObj.getStore().getCompany().getCompanyId(), new Date());
	    if(offer==null)
	    	offer = offersDAO.getAffiliateOffer(userObj.getStore().getCompany().getCompanyId(), new Date());
	    if(offer!=null)
	    	binfo.set
	    */
	    bookingInformationDAO.save(binfo);
	    
	    //POS Starts from here
		
	    String posUrl = propsReader.getPropValue("transaction.acknowledge.url."+ts.getBooking().getAffiliate().getID().longValue());
	    if(posUrl!=null){
	    	Company c = companyDAO.getByCompanyCode(ts.getBooking().getAffiliate().getID().longValue());
	    	if(c!=null){
	    		TransactionAcknowledge tAck = convertXMLFormat.generateAcknowledgeXML(ts, c.getCompanyName());
	    		bookingNotification.send(fileName, c.getCompanyName(), posUrl, ts, tAck);
	    	}
	    }
	    
	    return "received xml succesfully";
	}
	
	private String getTravellerFullName(com.casa.jaxb.booking.Customer cust){
		String name="";
		if(cust.getFirstName()!=null && !cust.getFirstName().equalsIgnoreCase("")){
			if(name.equalsIgnoreCase(""))
				name=cust.getFirstName();
			else
				name=name+" "+cust.getFirstName();
		}
		if(cust.getLastName()!=null && !cust.getLastName().equalsIgnoreCase("")){
			if(name.equalsIgnoreCase(""))
				name=cust.getLastName();
			else
				name=name+" "+cust.getLastName();
		}
		if(cust.getMiddleName()!=null && !cust.getMiddleName().equalsIgnoreCase("")){
			if(name.equalsIgnoreCase(""))
				name=cust.getMiddleName();
			else
				name=name+" "+cust.getMiddleName();
		}
		return name;
	}
	public void generatePDF(String uuid, OutputStream pdfoutStream, String userId){
		//BookingInformation bInfo = bookingInformationDAO.getById(id);
		BookingInformation bInfo = bookingInformationDAO.getByUUID(uuid);
		//User userObj = userDAO.getUserById(userId);
		if(bInfo!=null){
			try {
				ByteArrayOutputStream xmlOs = convertStringtoStream(bInfo.getXmlContent());
				bookingPDFHandler.createPDFFile(xmlOs, pdfoutStream, bInfo.getPdfTemplateName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private BookingDetailsDTO convertBookingDetailsToDTO(BookingInformation bd){
		BookingDetailsDTO dto = new BookingDetailsDTO();
		SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
		dto.setAgaentId(bd.getUserId());
		dto.setAgent(bd.getAgentName());
		dto.setBookingDate(format1.format(bd.getBookingDate()));
		dto.setFromLocation(bd.getFromLocation());
		dto.setPnr(bd.getPnr());
		dto.setToLocation(bd.getToLocation());
		dto.setTravelDate(format1.format(bd.getTravelDate()));
		dto.setUniqueId(bd.getUuid());
		dto.setAmount(CasaConstants.CURRENCY+bd.getAmount().toString());
		if(bd.getAirline()!=null)
			dto.setAirline(bd.getAirline());
		else
			dto.setAirline("N/A");
		dto.setTravellers("");
		dto.setTravellers(bd.getTravellerName1());
		if(bd.getTravellerName2()!=null)
			dto.setTravellers(dto.getTravellers()+", "+bd.getTravellerName2());
		if(bd.getTravellerName3()!=null)
			dto.setTravellers(dto.getTravellers()+", "+bd.getTravellerName3());
		if(bd.getTravellerName4()!=null)
			dto.setTravellers(dto.getTravellers()+", "+bd.getTravellerName4());
		if(bd.getTravellerName5()!=null)
			dto.setTravellers(dto.getTravellers()+", "+bd.getTravellerName5());
		if(bd.getTravellerName6()!=null)
			dto.setTravellers(dto.getTravellers()+", "+bd.getTravellerName6());
		if(bd.getTravellerName7()!=null)
			dto.setTravellers(dto.getTravellers()+", "+bd.getTravellerName7());
		if(bd.getTravellerName8()!=null)
			dto.setTravellers(dto.getTravellers()+", "+bd.getTravellerName8());
		return dto;
	}
	
	private ByteArrayOutputStream convertStringtoStream(String string) throws IOException {
	    byte[] stringByte = string.getBytes("UTF-8");
	    ByteArrayOutputStream bos = new ByteArrayOutputStream(string.length());
	    bos.write(stringByte);
	    return bos;
	}

}
