package com.casa.booking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsReader {

	private static Properties prop;
	private static PropsReader instance;
	
	static public PropsReader getInstance(){
		if(instance==null){
			instance=new PropsReader();
		}
		return instance;
	}
	private PropsReader(){
		prop = new Properties();
		String filename = "casa-travel.properties";
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream input = classLoader.getResourceAsStream(filename);
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPropValue(String key){
		return prop.getProperty(key);
	}
}
