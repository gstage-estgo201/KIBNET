package services;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.checkpay.util.SecurityUtil;

import common.CommonVariables;



public class AccountValidationServices {
	private final static String ID = "03420001";
	private final static String CRYPT_KEY = "checkpa58Kid##79**#ekIhkd#1ssi*l";
	
	public void checkAccount() {
		System.out.println("hello");
	}
	
	@SuppressWarnings("unchecked")
	public void method() {

		String trx_dt = new SimpleDateFormat("yyyyMMdd").format(new Date()); 
		String trx_tm = new SimpleDateFormat("HHmmss").format(new Date());
		
		JSONObject inputData = new JSONObject();
		inputData.put("fnni_cd", "004");
		inputData.put("acct_no", "772210258507");
		inputData.put("memb_nm", "홍길동");
		inputData.put("ptst_txt", "체크");
		inputData.put("verify_tp", "N");

		String reqEV = null;
		String reqVV = null;
		try {
			reqEV = SecurityUtil.EncryptAesBase64(trx_dt + trx_tm + inputData.toJSONString(), CRYPT_KEY, true); 
			reqVV = SecurityUtil.getHmacSha256(inputData.toJSONString(), CRYPT_KEY, true);	
		} catch(Exception e) {
			e.printStackTrace();
//			System.out.println("Exception occurred e " + e);
		}
		
		if(reqEV == null || reqVV == null) {
			System.out.println("EV : " + reqEV + ", VV : " + reqVV);
			return;
		}
		
		String urlString = CommonVariables.ACCOUNT_VALIDATION_URL_DEV + CommonVariables.SERVICE_CONTENT_720
				+ "ID=" + ID
				+ "&RQ_DTIME=" + trx_dt + trx_tm
				+ "&TNO=" + trx_dt+trx_tm
				+ "&EV=" + reqEV
                + "&VV=" + reqVV 
                + "&EM=AES" 
                + "&VM=HmacSHA256";
		
		HttpURLConnection connection = null;
		BufferedWriter bwriter = null; 
		DataInputStream in = null; 
		ByteArrayOutputStream bout = null; 
		
		try {
			URL url = new URL(urlString);
			System.out.println("url : " + urlString);
			
			connection = (HttpURLConnection)url.openConnection(); 
			connection.setConnectTimeout(2 * 60 * 1000); // 2 분
			connection.setReadTimeout(2 * 60 * 1000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
	        for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
	            for (String value : header.getValue()) {
	                System.out.println(header.getKey() + " : " + value);
	            }
	        }
		
	     // 응답 내용(BODY) 구하기 
	        String reponseData = null;
	        try (InputStream input = connection.getInputStream();
	        	ByteArrayOutputStream out = new ByteArrayOutputStream()) {

			byte[] buf = new byte[1024 * 8];
			int length = 0;
			while ((length = input.read(buf)) != -1) {
			    out.write(buf, 0, length);
			}
				reponseData = new String(out.toByteArray(), "UTF-8");
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject)parser.parse(reponseData);
				
				System.out.println("response : " + reponseData);
				
				String RC = jsonObject.get("RC").toString();
				String RM = jsonObject.get("RM").toString();
				String resEV = jsonObject.get("EV").toString();
				String resVV = jsonObject.get("VV").toString();
				
				System.out.println("RC : " + RC);
				System.out.println("RM : " + RM);
				System.out.println("resEV : " + resEV);
				System.out.println("resVV : " + resVV);
				
				if(RC.equals("0000")) {
					String decEV = SecurityUtil.DecryptAesBase64(resEV, CRYPT_KEY, true);
					System.out.println("decEV : " + decEV);
					
					JSONParser jsonParser = new JSONParser();
					JSONObject resJsonObject = (JSONObject)jsonParser.parse(decEV.substring(14));
					
					String verify_tr_dt = resJsonObject.get("verify_tr_dt").toString();
					String verify_tr_no = resJsonObject.get("verify_tr_no").toString();
					String verify_tr_tm = resJsonObject.get("verify_tr_tm").toString();
					String verify_txt = resJsonObject.get("verify_txt").toString();
					String verify_len = resJsonObject.get("verify_len").toString();
					
					System.out.println("verify_tr_dt : " + verify_tr_dt);
					System.out.println("verify_tr_no : " + verify_tr_no);
					System.out.println("verify_tr_tm : " + verify_tr_tm);
					System.out.println("verify_txt : " + verify_txt);
					System.out.println("verify_len : " + verify_len);
					
					JSONObject m = new JSONObject();
					m.put("verify_tr_dt", verify_tr_dt);
					m.put("verify_tr_no", verify_tr_no);
					m.put("verify_val", "123");	// Test server에서는 123입력 시 성공, 운영에서는 실제 고객 통장에 찍힌 검증번호를 입력하여야 함
					
					
					String reqEV2 = null;
					String reqVV2 = null;
					try {
						reqEV2 = SecurityUtil.EncryptAesBase64(trx_dt + trx_tm + m.toJSONString(), CRYPT_KEY, true); 
						reqVV2 = SecurityUtil.getHmacSha256(m.toJSONString(), CRYPT_KEY, true);	
					} catch(Exception e) {
						e.printStackTrace();
//						System.out.println("Exception occurred e " + e);
					}
					
					if(reqEV2 == null || reqVV2 == null) {
						System.out.println("EV2 : " + reqEV2 + ", VV2 : " + reqVV2);
						return;
					}
					
					String urlStringto721 = CommonVariables.ACCOUNT_VALIDATION_URL_DEV + CommonVariables.SERVICE_CONTENT_721
							+ "ID=" + ID
							+ "&RQ_DTIME=" + trx_dt + trx_tm
							+ "&TNO=" + trx_dt+trx_tm
							+ "&EV=" + reqEV2
			                + "&VV=" + reqVV2
			                + "&EM=AES" 
			                + "&VM=HmacSHA256";;
					
					URL urlto721 = new URL(urlStringto721);
					System.out.println("url to 721 : " + urlStringto721);
					
					connection = (HttpURLConnection)urlto721.openConnection(); 
					connection.setConnectTimeout(2 * 60 * 1000); // 2 분
					connection.setReadTimeout(2 * 60 * 1000);
					connection.setDoOutput(true);
					connection.setDoInput(true);
					
			        for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
			            for (String value : header.getValue()) {
			                System.out.println(header.getKey() + " : " + value);
			            }
			        }
			        String responseData2 = null;
			        try (InputStream input2 = connection.getInputStream();
			        	ByteArrayOutputStream out2 = new ByteArrayOutputStream()) {

					byte[] buf2 = new byte[1024 * 8];
					int length2 = 0;
					while ((length2 = input2.read(buf2)) != -1) {
					    out2.write(buf2, 0, length2);
					}
						responseData2 = new String(out2.toByteArray(), "UTF-8");
						System.out.println("responseData2 : " + responseData2);
						
						
			        } catch(Exception e) {
						e.printStackTrace();
						return;
					}
				}
	        }

		} catch(Exception e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				if ( bwriter != null ) bwriter.close();
				if ( in != null ) in.close();
				if ( bout != null ) bout.close();
				if ( connection != null ) connection.disconnect();
			} catch(Exception se) {
				// do nothing
			}
		}
	}
}
