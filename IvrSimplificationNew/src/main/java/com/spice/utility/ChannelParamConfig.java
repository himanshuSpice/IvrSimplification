package com.spice.utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spice.utility.OsType;

@Component
public class ChannelParamConfig {

	@Autowired
	ServletContext context;
	@Autowired
	OsType osType;   

	private HashMap<String, String> appValue = new HashMap<String, String>();

	public String getProperty(String key) {
		//System.out.println("--   " + ",  = " + key + " =  ," + appValue.get(key));

		return appValue.get(key);
	}

	public boolean checkProperty(String key) {
		if (appValue.containsKey(key))
			return true;
		else
			return false;
	}

	public String propertyFileName() {
		String fName;
		if (osType.isWindows()) {
			fName = "/home/ussdaps/IvrSimplificationNew/appConfig.properties";
		} else {
			fName = "/home/ussdaps/appConfig.properties";
		}
		return fName;

	}

	@PostConstruct
	public void loadMap() {
		String strStingArray[] = null;
		try {
			InputStream is = new FileInputStream(propertyFileName());
			BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(is));

			String sCurrentLine;
			while ((sCurrentLine = objBufferedReader.readLine()) != null) {
				System.out.println("sdlsf  " + sCurrentLine);

				strStingArray = sCurrentLine.split("=");
				if (strStingArray.length == 2) {

					String key = strStingArray[0];// .trim().toLowerCase();
					// if(key.contains("_")){

					appValue.put(key, strStingArray[1]);
					// }

					System.out.println(" GET KEY " + appValue.get(key));
				}
			}

			// objBufferedReader.close();
		} catch (Exception e) {
			System.out.println("Error in credential file read");
			e.printStackTrace();

		}

		// return appValue;

	}
}