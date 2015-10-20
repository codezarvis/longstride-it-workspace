package com.casa.booking.pdf;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.casa.booking.dao.AirlinesDAO;
import com.casa.booking.model.Airlines;
import com.casa.jaxb.acknowledge.Passenger;
import com.casa.jaxb.acknowledge.TransactionAcknowledge;
import com.casa.jaxb.booking.BookingDetails;
import com.casa.jaxb.transaction.Customer;
import com.casa.jaxb.transaction.Transaction;

public class ConvertXMLFormat {
	@Autowired
	private AirlinesDAO airlinesDAO;
	
	public BookingDetails convertTransactionXML(Transaction transaction) throws Exception{
		String date_format = "dd-MM-yyyy HH:mm:ss";
		String date_pattern="EEE, MMM dd yyy hh:mm a";
		SimpleDateFormat formatter = new SimpleDateFormat(date_format);
		SimpleDateFormat dateFormat = new SimpleDateFormat(date_pattern);
		Date bookingDate = formatter.parse(transaction.getTimeStamp());
		BookingDetails booking = new BookingDetails();
		booking.setPnr(transaction.getBooking().getConfirmation().getPNR());
		booking.setBookingDate(dateFormat.format(bookingDate));
		booking.setBookingStatus(transaction.getBooking().getStatus().getValue());
		booking.setAgent(prepareAgent(transaction));
		booking.setPayment(preparePayment(transaction));
		booking.setCustomers(prepareCustomers(transaction));
		booking.setFlights(prepareFlights(transaction));
		return booking;
	}
	
	public TransactionAcknowledge generateAcknowledgeXML(Transaction transaction, String affiliateName){
		TransactionAcknowledge tAck = new TransactionAcknowledge();
		tAck.setHost("CasaTravel");
		tAck.setAmount(transaction.getBooking().getPaymentSchedules().getItem().getAmount());
		tAck.setDateAndTime(transaction.getTimeStamp());
		tAck.setCashierId(transaction.getBooking().getUser().getID().toString());
		tAck.setCashier(transaction.getBooking().getAgent().getName());
		tAck.setPnr(transaction.getBooking().getConfirmation().getPNR());
		List<Customer> customers = transaction.getBooking().getCustomers().getCustomer();
		Passenger passenger = new Passenger();
		for(Customer cus : customers){
			com.casa.jaxb.acknowledge.Name name = new com.casa.jaxb.acknowledge.Name();
			name.setFirst(cus.getName().getFirst());
			name.setLast(cus.getName().getLast());
			name.setMiddle(cus.getName().getMiddle());
			name.setTitle(cus.getName().getTitle());
			passenger.getName().add(name);
		}
		tAck.setPassenger(passenger);
		com.casa.jaxb.acknowledge.Flights ackFlights = new com.casa.jaxb.acknowledge.Flights();
		List<com.casa.jaxb.acknowledge.Flight> ackFlyList = ackFlights.getFlight();
		
		List<com.casa.jaxb.transaction.Flight> transFlights = transaction.getBooking().getAir().getFlights().getFlight();
		for(com.casa.jaxb.transaction.Flight f : transFlights){
			com.casa.jaxb.acknowledge.Flight ackFly = new com.casa.jaxb.acknowledge.Flight();
			List<com.casa.jaxb.transaction.Segment> segs = f.getSegment();
			for(com.casa.jaxb.transaction.Segment seg : segs){
				com.casa.jaxb.acknowledge.Segment ackSeg = new com.casa.jaxb.acknowledge.Segment();
				ackSeg.setFrom(seg.getFrom());
				ackSeg.setTo(seg.getTo());
				ackFly.getSegment().add(ackSeg);
			}
			ackFlyList.add(ackFly);
		}
		tAck.setFlights(ackFlights);
		tAck.setCompany(affiliateName);
		return tAck;
	}
	
	private com.casa.jaxb.booking.Agent prepareAgent(Transaction transaction){
		com.casa.jaxb.booking.Agent agent = new com.casa.jaxb.booking.Agent();
		//agent.setAddress(value);
		agent.setName(transaction.getBooking().getAgent().getName());
		agent.setEmail(transaction.getBooking().getAgent().getEMail());
		agent.setCode(transaction.getBooking().getAgent().getCode());
		List<com.casa.jaxb.transaction.Flight> tFlight = transaction.getBooking().getAir().getFlights().getFlight();
		boolean flag=false;
		try {
			for(com.casa.jaxb.transaction.Flight f : tFlight){
				List<com.casa.jaxb.transaction.Segment> segs = f.getSegment();
				for(com.casa.jaxb.transaction.Segment seg : segs){
					if(seg.getConfNum()!=null){
						agent.setLocationCode(seg.getConfNum());
						flag=true;
						break;
					}
				}
				if(flag)
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(agent.getLocationCode()==null)
			agent.setLocationCode("");
		return agent;
	}
	
	private com.casa.jaxb.booking.Payment preparePayment(Transaction transaction){
		com.casa.jaxb.booking.Payment payment = new com.casa.jaxb.booking.Payment();
		//payment.setDiscount(value);
		
		BigDecimal tax = new BigDecimal(0);
		for(com.casa.jaxb.transaction.Item itm :transaction.getBooking().getFinancials().getItem()){
			  if(itm.getCode()!=null && itm.getCode().equalsIgnoreCase("TotalTax")){
				  tax=itm.getAmount();
				  payment.setTax(itm.getAmount().toString());
			  } 
			  /*else if(itm.getID()!=null && itm.getID().intValue()==7){
				  sum.add(itm.getAmount());
			  } else if(itm.getID()!=null && itm.getID().intValue()==49){
				  sum.add(itm.getAmount());
			  }*/
		}
		BigDecimal total = transaction.getBooking().getPaymentSchedules().getItem().getAmount();
		BigDecimal fare = total.subtract(tax);
		payment.setFare(fare.toString());
		payment.setTotal(transaction.getBooking().getPaymentSchedules().getItem().getAmount().toString());
		payment.setType(transaction.getBooking().getPaymentSchedules().getItem().getPaymentType());
		if(transaction.getBooking().getPaymentSchedules().getItem().getPayment()!=null && transaction.getBooking().getPaymentSchedules().getItem().getPayment().getCard()!=null){
			payment.setType(transaction.getBooking().getPaymentSchedules().getItem().getPayment().getCard().getNumber());
			payment.setCreditCardNo(transaction.getBooking().getPaymentSchedules().getItem().getPayment().getCard().getNumber());
			payment.setCardHolderName(transaction.getBooking().getPaymentSchedules().getItem().getPayment().getCard().getOwner().getName().getContent());
			payment.setCardHolderAddress(transaction.getBooking().getPaymentSchedules().getItem().getPayment().getCard().getOwner().getAddress());
		}
		return payment;
	}

	private com.casa.jaxb.booking.Customers prepareCustomers(Transaction transaction){
		com.casa.jaxb.booking.Customers customers = new com.casa.jaxb.booking.Customers();
		List<com.casa.jaxb.transaction.Customer> tCustomer = transaction.getBooking().getCustomers().getCustomer();
		for(com.casa.jaxb.transaction.Customer c : tCustomer){
			com.casa.jaxb.booking.Customer bCustomer = new com.casa.jaxb.booking.Customer();
			bCustomer.setTitle(c.getName().getTitle());
			bCustomer.setFirstName(c.getName().getFirst()!=null?c.getName().getFirst().toUpperCase():null);
			bCustomer.setLastName(c.getName().getLast()!=null?c.getName().getLast().toUpperCase():null);
			bCustomer.setMiddleName(c.getName().getMiddle()!=null?c.getName().getMiddle().toUpperCase():null);
			bCustomer.setGender(c.getGender());
			bCustomer.setBookingStatus(transaction.getBooking().getStatus().getValue());
			bCustomer.setDob(c.getDoB());
			bCustomer.setEmail(c.getEMail());
			bCustomer.setPhone(c.getHomePhone());
			bCustomer.setCode(c.getCode());
			//bCustomer.setSeat();
			customers.getCustomer().add(bCustomer);
		}
		return customers;
	}
	
	private com.casa.jaxb.booking.Flights prepareFlights(Transaction transaction) throws ParseException{
		String only_date_pattern="EEE, MMM dd yyy";
		String only_time_pattern="hh:mm a";
		String date_format = "dd-MM-yyyy HH:mm:ss"; //27-12-2012 18:50:00
		SimpleDateFormat formatter = new SimpleDateFormat(date_format);
		SimpleDateFormat dateFormat = new SimpleDateFormat(only_date_pattern);
		SimpleDateFormat timeFormat = new SimpleDateFormat(only_time_pattern);
		com.casa.jaxb.booking.Flights flights = new com.casa.jaxb.booking.Flights();
		List<com.casa.jaxb.transaction.Flight> tFlight = transaction.getBooking().getAir().getFlights().getFlight();
		for(com.casa.jaxb.transaction.Flight f : tFlight){
			List<com.casa.jaxb.transaction.Segment> segs = f.getSegment();
			for(com.casa.jaxb.transaction.Segment seg : segs){
				com.casa.jaxb.booking.Flight bFlight = new com.casa.jaxb.booking.Flight();
				bFlight.setNumber(seg.getNumber().toString());
				
				Date departDate = formatter.parse(seg.getDepart());
				bFlight.setDepartureDate(dateFormat.format(departDate));
				bFlight.setDepartureTime(timeFormat.format(departDate));
				bFlight.setDeparturePlace(seg.getFrom()+", "+seg.getFromCountry());
				
				Date arriveDate = formatter.parse(seg.getArrive());
				bFlight.setArrivalDate(dateFormat.format(arriveDate));
				bFlight.setArrivalTime(timeFormat.format(arriveDate));
				bFlight.setArrivalPlace(seg.getTo()+", "+seg.getToCountry());
				Airlines al = airlinesDAO.getByAirlineCode(seg.getAirline());
				if(al!=null)
					bFlight.setAirlineName(al.getAirlineFullName());
				else
					bFlight.setAirlineName(seg.getAirline());
				bFlight.setAirlineConfNum(seg.getConfNum());
				bFlight.setSeats(getBookedSeats(seg.getNumber(),transaction));
				flights.getFlight().add(bFlight);
			}
		}
		return flights;
	}
	
	private String getBookedSeats(BigInteger segId, Transaction transaction){
		String s=null;
		List<com.casa.jaxb.transaction.Customer> tCustomer = transaction.getBooking().getCustomers().getCustomer();
		for(com.casa.jaxb.transaction.Customer c : tCustomer){
			List<com.casa.jaxb.transaction.Seat> seats = c.getSeat();
			for(com.casa.jaxb.transaction.Seat seat : seats){
				if(segId.equals(seat.getSegment())){
					if(s==null)
						s=seat.getValue();
					else
						s=s+", "+seat.getValue();
				}
			}
		}
		return s;
	}
}
