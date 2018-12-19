package com.spice.utility;

import com.spice.response.IvrResponse;
import com.spice.response.Response;
import com.spice.service.IvrSimplificationImpl;

import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketUrl {
	@Autowired
	ChannelParamConfig param;

	public static void main(String[] args) {
	}

	public String urlHandle(long uniqueId, String msisdn, String requestId, String circle, String channel,String is_chhota_credit) {
		String url="";
		if(is_chhota_credit == null) {
			url = "GET /?index=" + indexHandle() + "&msisdn=91" + msisdn + "&req_type="
					+ param.getProperty(channel.trim()) + "&sessionid=" + requestId + "&circleid=" + circle + "&channel="
					+ channel + "&userType=0";
		}
		else if(is_chhota_credit.equalsIgnoreCase("y")) {
			url = "GET /?index=" + indexHandle() + "&msisdn=91" + msisdn + "&req_type=899"
 + "&sessionid=" + requestId + "&circleid=" + circle + "&channel="
				+ channel + "&userType=0";
		}else {
			url = "GET /?index=" + indexHandle() + "&msisdn=91" + msisdn + "&req_type="
					+ param.getProperty(channel.trim()) + "&sessionid=" + requestId + "&circleid=" + circle + "&channel="
					+ channel + "&userType=0";
		}
		String resp = "";
		
		try {
			resp = hitUrl(uniqueId, url);
		} catch (Exception e) {
			resp = "";
			e.printStackTrace();
		}
		return resp;
	}

	public static int indexHandle() {
		int index = 0;
		/*
		 * index++; if(index>52000) index =51000;
		 */ return index;
	}

	public String hitUrl(long uniqueId, String url) {
		// Logger logger = Logger.getLogger(SocketUrl.class);
		DataOutputStream os = null;
		DataInputStream is = null;

		IvrResponse DataOutputStream = new IvrResponse();
		String responseLine = "";

		BufferedReader in = null;
		Socket socket = null;
		String ipport = param.getProperty("ipport");
		String soTime = param.getProperty("sotimeout");
		String readTime = param.getProperty("readtimeout");
		try {
			System.out.println("Url="+url);
			socket = new Socket();
			socket.connect(new InetSocketAddress(ipport.split(":")[0], Integer.parseInt(ipport.split(":")[1])),
					Integer.parseInt(readTime));

			socket.setSoTimeout(Integer.parseInt(soTime));

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(url);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String userInput;
			while ((userInput = in.readLine()) != null) {
				responseLine += userInput;
			}
		}

		catch (SocketException e) {
			responseLine = "2001";

		} catch (SocketTimeoutException e) {
			responseLine = "2002";
		}

		catch (Exception e) {
			responseLine = "";
		} finally {
			try {
				if (in != null)
					in.close();
				if (socket != null)
					socket.close();

			} catch (Exception e) {
				responseLine = "2002";
				e.printStackTrace();
			}
		}
		// logger.info("url response " + uniqueId + "," + ipport + "," + url +
		// ",response = " + responseLine);
		return responseLine;
	}

}
