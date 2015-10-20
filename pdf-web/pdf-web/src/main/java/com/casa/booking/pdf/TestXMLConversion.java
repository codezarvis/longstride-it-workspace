package com.casa.booking.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.casa.jaxb.booking.BookingDetails;
import com.casa.jaxb.transaction.Transaction;

public class TestXMLConversion {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File initialFile = new File("/Users/prasank/Documents/workspace-sts-3.6.1.RELEASE/pdfGen/template/transaction-2.xml");
		//String templateFilePath ="/Users/prasank/Desktop/";
		JAXBContext jaxc = JAXBContext.newInstance ("com.casa.jaxb.transaction");
	    Unmarshaller un = jaxc.createUnmarshaller ();
	    Transaction ts = (Transaction)un.unmarshal(initialFile);
	    System.out.println(ts.getBooking().getCustomers().getCustomer().get(0).getName().getFirst());
	    System.out.println(ts.getBooking().getPaymentSchedules().getItem().getAmount().toString());
	    ConvertXMLFormat xmlConvert = new ConvertXMLFormat();
	    BookingDetails bd =  xmlConvert.convertTransactionXML(ts);
	    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    JAXBContext context = JAXBContext.newInstance("com.casa.jaxb.booking");
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(bd, outStream);
		
		//BookingPDFHandler handler = new BookingPDFHandler();
		//handler.createPDFFile(outStream, templateFilePath);
		
		//File xmlFile = new File("/Users/prasank/Documents/workspace-sts-3.6.1.RELEASE/pdfGen/template/booking.xml");
	   // InputStream targetStream = new FileInputStream(xmlFile);
	    
	    //handler.createPDFFile(targetStream, templateFilePath);
	}

}
