package services_veirfy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.CommonVariables;

public class VerifyAccount {
	
	private HttpURLConnection connection;
	private String responseData;
	
	public String verify(String service, String urlString) {
		
		if(service == null || urlString == null) {
			return null;
		}
		
		try {
			connection = (HttpURLConnection)new URL(CommonVariables.VERIFY_ACCOUNT_URL + service + urlString).openConnection();
			
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			
//			header 보기
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
	
	public boolean isBankSvcTime(String current_dtime, String svc_stop_sdtime, String svc_stop_edtime) {
		Date curDTime = null;
		Date svcSdTime = null;
		Date svcEdTime = null;

		try {
			curDTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(current_dtime);
			svcSdTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(svc_stop_sdtime);
			svcEdTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(svc_stop_edtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("curDate   : " + curDTime);
		System.out.println("svcSdTime : " + svcSdTime);
		System.out.println("svcEdTime : " + svcEdTime);
		
		if(curDTime.compareTo(svcSdTime) >= 0 &&
				curDTime.compareTo(svcEdTime) <= 0) {
			return true;
		}
		return false;
	}
}
