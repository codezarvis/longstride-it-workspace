package com.casa.booking.acknowledge;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;

import com.casa.booking.dao.TransactionAckLogDAO;
import com.casa.booking.model.TransactionAckLog;
import com.casa.jaxb.acknowledge.TransactionAcknowledge;
import com.casa.jaxb.transaction.Transaction;

public class BookingNotification {
	
	@Autowired
	private TransactionAckLogDAO transactionAckLogDAO;
	
	public void send(String fileName, String companyName, String posUrl, Transaction ts, TransactionAcknowledge tAck){
		try{
			String date_format = "dd-MM-yyyy HH:mm:ss";
			SimpleDateFormat formatter = new SimpleDateFormat(date_format);
	    		//TransactionAcknowledge tAck = convertXMLFormat.generateAcknowledgeXML(ts);
	    		
	    	StringWriter stringWriter2 = new StringWriter();
	    	JAXBContext context2 = JAXBContext.newInstance("com.casa.jaxb.acknowledge");
	    	Marshaller m2 = context2.createMarshaller();
	    	m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    	m2.marshal(tAck, stringWriter2);
	    	String notifyXml = stringWriter2.toString();
	    	//System.out.println("POS XML ==>> "+notifyXml);
	    	
	    	TransactionAckLog ack = postXML(posUrl, notifyXml);
	    	ack.setXmlFileName(fileName);
	    	ack.setCasherCode(ts.getBooking().getUser().getID().toString());
	    	ack.setCasherName(ts.getBooking().getAgent().getName());
	    	ack.setCompanyCode(ts.getBooking().getAffiliate().getID().longValue());
	    	ack.setTransactionTimestamp(formatter.parse(ts.getTimeStamp()));
	    	ack.setCompanyName(companyName);
	    	transactionAckLogDAO.save(ack);
	    	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private TransactionAckLog postXML(String url, String xml) throws Exception{
		int responseCode=0;
		StringBuffer response = new StringBuffer();
		try {
			URL obj = new URL(url);
			//HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			sun.net.www.protocol.http.HttpURLConnection con = (sun.net.www.protocol.http.HttpURLConnection)obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/xml");
			con.setRequestProperty("Content-Length", "" + Integer.toString(xml.getBytes().length));
			
			con.setUseCaches (false);
			con.setDoInput(true);
			con.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(xml);
			wr.flush();
			wr.close();
			
			responseCode = con.getResponseCode();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			response.append(e.toString());
			e.printStackTrace();
		}
		
		TransactionAckLog transactionAckLog = new TransactionAckLog();
		transactionAckLog.setPostXmlRespStatus(new Long(responseCode));
		if(response.toString().length()<100)
			transactionAckLog.setPostXmlRespMesg(response.toString());
		else
			transactionAckLog.setPostXmlRespMesg(response.toString().substring(0, 99));
		
		return transactionAckLog;
	}
}
