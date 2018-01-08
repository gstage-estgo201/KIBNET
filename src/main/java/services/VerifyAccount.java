package services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import common.CommonVariables;

public class VerifyAccount {
	
	private HttpURLConnection connection;
	private String responseData;
	
	public String verify(String service, String urlString) {
		
		try {
			connection = (HttpURLConnection)new URL(CommonVariables.VERIFY_ACCOUNT_URL + service + urlString).openConnection();
			
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			
//			for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
//	            for (String value : header.getValue()) {
//	                System.out.println(header.getKey() + " : " + value);
//	            }
//	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (InputStream input = connection.getInputStream();
	        	ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = input.read(buf)) != -1) {
			    out.write(buf, 0, length);
			}
			responseData = new String(out.toByteArray(), "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseData;
		
	}
	
	private void checkBankServiceTime(String urlString) {
		try {
			HttpURLConnection connection = (HttpURLConnection)new URL(CommonVariables.VERIFY_ACCOUNT_URL + CommonVariables.SERVICE_CONTENT_720 + urlString).openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
