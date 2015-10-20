package com.casa.booking.pdf.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casa.booking.dto.BookingDetailsArrayDTO;
import com.casa.booking.dto.BookingDetailsDTO;
import com.casa.booking.service.BookingService;
import com.casa.booking.util.CasaConstants;

@Component
@Path("/api")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@POST
    @Path("postxml")
    @Consumes("application/xml")
    @Produces("text/plain")
	public String postXML(String incomingXML) {
		//System.out.println(bookingService);
		String status;
		try {
			status=bookingService.processXML(incomingXML);
		} catch (Exception e) {
			status="xml status failed";
			e.printStackTrace();
		}
		return status;
	}
	
	@GET
    @Path("/bookingdetails/{type}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<BookingDetailsDTO> getDetails(@PathParam("type") String type,
			@DefaultValue("") @QueryParam("query") String query) throws ParseException {
		Subject subject = SecurityUtils.getSubject();
		String role=CasaConstants.ROLE_CASHIER;
		if(subject.hasRole(CasaConstants.ROLE_ADMIN)){
			role=CasaConstants.ROLE_ADMIN;
		}else if(subject.hasRole(CasaConstants.ROLE_AFFILIATE)){
			role=CasaConstants.ROLE_AFFILIATE;
		}else if(subject.hasRole(CasaConstants.ROLE_DISTRICT_MANAGER)){
			role=CasaConstants.ROLE_DISTRICT_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_STORE_MANAGER)){
			role=CasaConstants.ROLE_STORE_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_CASHIER)){
			role=CasaConstants.ROLE_CASHIER;
		}
		//List<BookingDetailsDTO> dtos = new ArrayList<BookingDetailsDTO>();
		//subject.hasRole("admin")
		return bookingService.getBookingDetails(type, query, subject.getPrincipal().toString(), role);
		/*
		BookingDetailsDTO dto = new BookingDetailsDTO();
		dto.setAgent("PRA");
		dto.setBookingDate("10/10/10");
		dto.setFromLocation("BLR");
		dto.setPnr("xyz");
		dto.setToLocation("MAS");
		dto.setTravellers("Prasanth");
		dtos.add(dto);
		return dtos;*/
	}
	
	@GET
    @Path("/bookingdetails/array/{type}")
    @Produces(MediaType.APPLICATION_JSON)
	public BookingDetailsArrayDTO getArrayDetails(@PathParam("type") String type, 
			@DefaultValue("") @QueryParam("query") String query) throws ParseException {
		Subject subject = SecurityUtils.getSubject();
		
		//List<BookingDetailsDTO> dtos = new ArrayList<BookingDetailsDTO>();
		//subject.hasRole("admin")
		String role=CasaConstants.ROLE_CASHIER;
		if(subject.hasRole(CasaConstants.ROLE_ADMIN)){
			role=CasaConstants.ROLE_ADMIN;
		}else if(subject.hasRole(CasaConstants.ROLE_AFFILIATE)){
			role=CasaConstants.ROLE_AFFILIATE;
		}else if(subject.hasRole(CasaConstants.ROLE_DISTRICT_MANAGER)){
			role=CasaConstants.ROLE_DISTRICT_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_STORE_MANAGER)){
			role=CasaConstants.ROLE_STORE_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_CASHIER)){
			role=CasaConstants.ROLE_CASHIER;
		}
		System.out.println("Role ::: "+role);
		List<BookingDetailsDTO> dtos = bookingService.getBookingDetails(type, query.trim(), subject.getPrincipal().toString(), role);
		BookingDetailsArrayDTO arrayDto = new BookingDetailsArrayDTO();
		List<List<String>> data = new ArrayList<List<String>>();
		for(BookingDetailsDTO dto : dtos){
			List<String> list = new ArrayList<String>();
			list.add(dto.getAgaentId());
			list.add(dto.getAgent());
			list.add(dto.getPnr());
			list.add(dto.getBookingDate());
			list.add(dto.getTravellers());
			list.add(dto.getTravelDate());
			list.add(dto.getFromLocation());
			list.add(dto.getToLocation());
			list.add(dto.getAirline());
			list.add(dto.getAmount());
			list.add(dto.getUniqueId());
			data.add(list);
		}
		arrayDto.setData(data);
		return arrayDto;
		/*
		BookingDetailsDTO dto = new BookingDetailsDTO();
		dto.setAgent("PRA");
		dto.setBookingDate("10/10/10");
		dto.setFromLocation("BLR");
		dto.setPnr("xyz");
		dto.setToLocation("MAS");
		dto.setTravellers("Prasanth");
		dtos.add(dto);
		return dtos;*/
	}
	
	@GET
    @Path("/bookingdetails/search")
    @Produces(MediaType.APPLICATION_JSON)
	public List<BookingDetailsDTO> searchDetails(@DefaultValue("") @QueryParam("type") String type,
			@DefaultValue("") @QueryParam("query") String query) throws ParseException {
		Subject subject = SecurityUtils.getSubject();
		String role=CasaConstants.ROLE_CASHIER;
		if(subject.hasRole(CasaConstants.ROLE_ADMIN)){
			role=CasaConstants.ROLE_ADMIN;
		}else if(subject.hasRole(CasaConstants.ROLE_AFFILIATE)){
			role=CasaConstants.ROLE_AFFILIATE;
		}else if(subject.hasRole(CasaConstants.ROLE_DISTRICT_MANAGER)){
			role=CasaConstants.ROLE_DISTRICT_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_STORE_MANAGER)){
			role=CasaConstants.ROLE_STORE_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_CASHIER)){
			role=CasaConstants.ROLE_CASHIER;
		}
		
		System.out.println("Search Role ::: "+role);
		//List<BookingDetailsDTO> dtos = new ArrayList<BookingDetailsDTO>();
		return bookingService.searchBookingDetails(type, query, subject.getPrincipal().toString(), role);
		/*
		BookingDetailsDTO dto = new BookingDetailsDTO();
		dto.setAgent("PRA");
		dto.setBookingDate("10/10/10");
		dto.setFromLocation("BLR");
		dto.setPnr("xyz");
		dto.setToLocation("MAS");
		dto.setTravellers("Prasanth");
		dtos.add(dto);
		return dtos;*/
	}

	@GET
    @Path("/bookingdetails/array/search")
    @Produces(MediaType.APPLICATION_JSON)
	public BookingDetailsArrayDTO searchArrayDetails(@DefaultValue("") @QueryParam("type") String type,
			@DefaultValue("") @QueryParam("query") String query) throws ParseException {
		Subject subject = SecurityUtils.getSubject();
		String role=CasaConstants.ROLE_CASHIER;
		if(subject.hasRole(CasaConstants.ROLE_ADMIN)){
			role=CasaConstants.ROLE_ADMIN;
		}else if(subject.hasRole(CasaConstants.ROLE_AFFILIATE)){
			role=CasaConstants.ROLE_AFFILIATE;
		}else if(subject.hasRole(CasaConstants.ROLE_DISTRICT_MANAGER)){
			role=CasaConstants.ROLE_DISTRICT_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_STORE_MANAGER)){
			role=CasaConstants.ROLE_STORE_MANAGER;
		}else if(subject.hasRole(CasaConstants.ROLE_CASHIER)){
			role=CasaConstants.ROLE_CASHIER;
		}
		List<BookingDetailsDTO> dtos =  bookingService.searchBookingDetails(type, query.trim(), subject.getPrincipal().toString(), role);
		BookingDetailsArrayDTO arrayDto = new BookingDetailsArrayDTO();
		List<List<String>> data = new ArrayList<List<String>>();
		for(BookingDetailsDTO dto : dtos){
			List<String> list = new ArrayList<String>();
			list.add(dto.getAgaentId());
			list.add(dto.getAgent());
			list.add(dto.getPnr());
			list.add(dto.getBookingDate());
			list.add(dto.getTravellers());
			list.add(dto.getTravelDate());
			list.add(dto.getFromLocation());
			list.add(dto.getToLocation());
			list.add(dto.getAirline());
			list.add(dto.getAmount());
			list.add(dto.getUniqueId());
			data.add(list);
		}
		arrayDto.setData(data);
		return arrayDto;
	}
	
	@Path("/bookingdetails/getpdf")
	@GET
	@Produces({"application/pdf"})
	public StreamingOutput getPDF(@DefaultValue("") @QueryParam("id") String id) throws Exception {
		final String uuid = id;
	    return new StreamingOutput() {
	        public void write(OutputStream output) throws IOException, WebApplicationException {
	            try {
	            	Subject subject = SecurityUtils.getSubject();
	            	bookingService.generatePDF(uuid, output, subject.getPrincipal().toString());
	                //generator.generatePDF(output);
	            } catch (Exception e) {
	                throw new WebApplicationException(e);
	            }
	        }
	    };
	}
	
	@GET
    @Path("/test/{type}")
    @Produces(MediaType.APPLICATION_JSON)
	public List<BookingDetailsDTO> test(@PathParam("type") String type) throws ParseException {
		//Subject subject = SecurityUtils.getSubject();
		System.out.println(bookingService);
		List<BookingDetailsDTO> dtos = new ArrayList<BookingDetailsDTO>();
		//return bookingService.getBookingDetails(type, subject.getPrincipal().toString().toLowerCase(), subject.hasRole("admin"));
		
		BookingDetailsDTO dto = new BookingDetailsDTO();
		dto.setAgent("PRA");
		dto.setBookingDate("10/10/10");
		dto.setFromLocation("BLR");
		dto.setPnr("xyz");
		dto.setToLocation("MAS");
		dto.setTravellers("Prasanth");
		dtos.add(dto);
		return dtos;
	}
	/*
	@GET
    @Path("ping")
	@Produces("text/plain")
	public String ping() throws ParseException {
		return "ping success";
	}
	
	@POST
    @Path("savejob")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<JobInfo> saveJob(List<JobInfo> jobs) throws ParseException {
		System.out.println("PRASANTH =====> "+jobs);
		return jobs;
	}
	
	
    @POST
    @Path("/bookingdetails/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String uploadFile(@FormDataParam("file") InputStream fis,
                    @FormDataParam("file") FormDataContentDisposition fdcd) {
    	String FOLDER_PATH="/Users/prasank/Documents/workspace-sts-3.6.1.RELEASE/pdf-web/";
        OutputStream outpuStream = null;
        String fileName = fdcd.getFileName();
        System.out.println("File Name: " + fdcd.getFileName());
        String filePath = FOLDER_PATH + fileName;
         
        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            outpuStream = new FileOutputStream(new File(filePath));
            while ((read = fis.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch(IOException iox){
            iox.printStackTrace();
        } finally {
            if(outpuStream != null){
                try{outpuStream.close();} catch(Exception ex){}
            }
        }
        return "File Upload Successfully !!";
    }*/
}
