package com.casa.booking.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
 
public class BookingPDFHandler {
 
	public void createPDFFile(ByteArrayOutputStream xmlSource, OutputStream pdfoutStream, String pdfTemplate) throws IOException {
		//File file = File.createTempFile("" + System.currentTimeMillis(), EXTENSION, new File(templateFilePath));
		//String PRESCRIPTION_URL = "templates/booking_template.xsl";
		String PRESCRIPTION_URL = "templates/"+pdfTemplate;
		ClassLoader classLoader = getClass().getClassLoader();
		/*
		if(userObj.getTemplatePref()!=null && userObj.getTemplatePref().equalsIgnoreCase(CasaConstants.PREF_STORE)){
			PRESCRIPTION_URL="templates/"+userObj.getStore().getCompany().getCompanyId()+"_"+userObj.getStore().getStoreId()+".xsl";
		}else if(userObj.getTemplatePref()!=null && userObj.getTemplatePref().equalsIgnoreCase(CasaConstants.PREF_REGION)){
			PRESCRIPTION_URL="templates/"+userObj.getStore().getCompany().getCompanyId()+"_"+userObj.getStore().getRegion()+".xsl";
		}else if(userObj.getTemplatePref()!=null && userObj.getTemplatePref().equalsIgnoreCase(CasaConstants.PREF_AFFILIATE)){
			PRESCRIPTION_URL="templates/"+userObj.getStore().getCompany().getCompanyId()+".xsl";
		}
		*/
		URL url = new File(classLoader.getResource(PRESCRIPTION_URL).getFile()).toURI().toURL();
		// creation of transform source
		StreamSource transformSource = new StreamSource(url.openStream());
		// create an instance of fop factory
		FopFactory fopFactory = FopFactory.newInstance();
		// a user agent is needed for transformation
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		System.out.println("file://" + url.toExternalForm());
		foUserAgent.setBaseURL(url.toExternalForm());
		// to store output
		//ByteArrayOutputStream pdfoutStream = new ByteArrayOutputStream();
		//StreamSource source = new StreamSource(xmlSource);
		StreamSource source = new StreamSource(new ByteArrayInputStream(xmlSource.toByteArray()));
		Transformer xslfoTransformer;
		try {
			TransformerFactory transfact = TransformerFactory.newInstance();
 
			xslfoTransformer = transfact.newTransformer(transformSource);
			// Construct fop with desired output format
			Fop fop;
			try {
				fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, pdfoutStream);
				// Resulting SAX events (the generated FO)
				// must be piped through to FOP
				Result res = new SAXResult(fop.getDefaultHandler());
 
				// Start XSLT transformation and FOP processing
				try {
					// everything will happen here..
					xslfoTransformer.transform(source, res);
 
					// if you want to save PDF file use the following code
					/*OutputStream out = new java.io.FileOutputStream(file);
					out = new java.io.BufferedOutputStream(out);
					FileOutputStream str = new FileOutputStream(file);
					str.write(pdfoutStream.toByteArray());
					str.close();
					out.close();*/
 
				} catch (TransformerException e) {
					e.printStackTrace();
				}
			} catch (FOPException e) {
				e.printStackTrace();
			}
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		//return file.getPath();
	}
 
	/*
	public ByteArrayOutputStream getXMLSource(EmployeeData data) throws Exception {
		JAXBContext context;
 
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
 
		try {
			context = JAXBContext.newInstance(EmployeeData.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(data, outStream);
		} catch (JAXBException e) {
 
			e.printStackTrace();
		}
		return outStream;
 
	}
	*/
	/*
	is = con.getInputStream();
	jaxc = JAXBContext.newInstance ("cisco.params.xml.ns.yang.msi_modules");
	Unmarshaller un = jaxc.createUnmarshaller ();
	msiModules = (Modules) un.unmarshal (is);
	*/
}
