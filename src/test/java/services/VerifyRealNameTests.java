package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import common.CommonVariables;

public class VerifyRealNameTests {
	
	@Test
	public void 정상동작확인_신한은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("011")
				.setSearch_acct_no("14902597746")
				.setAcnm_no("860902")
				.setIche_amt("0")
				.setTrsc_seq_no("0000002")
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("0000", getDataFromResponse(result, "RSLT_CD"));
		System.out.println("result : " + result.trim());
	}
	
	@Test
	public void 정상동작확인_신한은행2() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("002")
				.setSearch_acct_no("02026007408704")
				.setAcnm_no("640524")
				.setIche_amt("0")
				.setTrsc_seq_no("0000002")
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("0000", getDataFromResponse(result, "RSLT_CD"));
		System.out.println("result : " + result.trim());
	}
	
	private String getDataFromResponse(String response, String key) {
		String value = null;
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject)parser.parse(response);
			value = object.get(key).toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return value;
	}
	
}
