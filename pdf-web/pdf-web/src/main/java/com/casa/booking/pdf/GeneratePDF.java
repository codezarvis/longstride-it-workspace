package com.casa.booking.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class GeneratePDF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			String templateFilePath ="/Users/prasank/Documents/workspace-sts-3.6.1.RELEASE/pdfGen/template/";
			 
			File initialFile = new File("/Users/prasank/Documents/workspace-sts-3.6.1.RELEASE/pdfGen/template/transaction.xml");
		    InputStream targetStream = new FileInputStream(initialFile);
		    //JAXBContext jaxc = JAXBContext.newInstance ("com.casa.booking.transaction");
		    //Unmarshaller un = jaxc.createUnmarshaller ();
		    //Transaction ts = (Transaction)un.unmarshal(initialFile);
		    //System.out.println(ts.getBooking().getCustomers().getCustomer());
		    
		    TransactionPDFHandler handler = new TransactionPDFHandler();
		    handler.createPDFFile(targetStream, templateFilePath);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
