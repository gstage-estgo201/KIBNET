package services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class VerifyRealName {
	
	private final static String URL = "http://dev.coocon.co.kr/sol/gateway/acctnm_rcms_wapi.jsp";
//	private final static String URL = "https://gw.coocon.co.kr/sol/gateway/acctnm_rcms_wapi.jsp";
	
	private final static String SECR_KEY = "";
	private final static String KEY = "";
	
	HttpURLConnection connection;
	
	@SuppressWarnings("unchecked")
	private JSONObject getJsonData() {
		return null;
	}
	public void verify() {
		JSONObject jsonData = new JSONObject();
		jsonData.put(JsonDataType.SECR_KEY, "AhCv4embos1U4sDtF0gO");
		jsonData.put(JsonDataType.KEY, "ACCTNM_RCMS_WAPI");
		
		JSONArray reqData = new JSONArray();
		JSONObject reqDataValue = new JSONObject();
		reqDataValue.put("BANK_CD", "011");
		reqDataValue.put("SEARCH_ACCT_NO", "14902597746");
		reqDataValue.put("ACNM_NO", "860902");
		reqDataValue.put("ICHE_AMT", "0");
		reqDataValue.put("TRSC_SEQ_NO", "0000002");
		reqData.add(reqDataValue);
		
		jsonData.put("REQ_DATA", reqData);
		
		System.out.println("jsonData : " + jsonData);
		
		try {
			connection = (HttpURLConnection)new URL(URL + "?JSONData=" + jsonData).openConnection();
			
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			
			for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
	            for (String value : header.getValue()) {
	                System.out.println(header.getKey() + " : " + value);
	            }
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (InputStream input = connection.getInputStream();
	        	ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			String reponseData = null;
	        			byte[] buf = new byte[1024 * 2];
			int length = 0;
			while ((length = input.read(buf)) != -1) {
			    out.write(buf, 0, length);
			}
			reponseData = new String(out.toByteArray(), "euc-kr");
			System.out.println("response : " + reponseData);
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject)parser.parse(reponseData);
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private enum JsonDataType {
		SECR_KEY, KEY, BANK_CD, SEARCH_ACCT_NO, ACNM_NO, ICHE_AMT, TRSC_SEQ_NO
	}
	
//	은행	은행코드	테스트 고객계좌	생년월일/사업자번호
//	산업은행	02	02026007408704	640524
//	기업은행	03	21701322303023	711205
//			01904232902016	790924
//	국민은행	04	운영계좌 사용	운영계좌 사용
//	외환은행	05	620199910659	850101
//	수협	07	02602123171	710307
//	농협	11	운영계좌 사용	운영계좌 사용
//	우리은행	20	1006502226237	860914
//	제일은행	23	86010014628	110101
//	씨티은행	27	3040176126401	771112
//	대구은행	31	505102678658	991111
//	부산은행	32	1010000014002	500331
//	제주은행	35	3301000013	711111
//	전북은행	37	501219999997	700407
//			501220048478	700407
//	경남은행	39	502210229948	751105
//	금고	45	9003166420461	700101
//	신협	48	132548099293	850104
//			135000020248	1148202152
//	정통부	71	01001702000029	450429
//	신한은행	88	100020908927	3148204065
}
