package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import common.CommonVariables;

public class VerifyRealNameTests {
	private String[][] dataArray = {
			{"011", "14902597746", "860902", "0", "0000002"},
			{"002", "02026007408704", "640524", "0", "0000002"},
			{"088", "100020908927", "3148204065", "0", "0000002"}};
	@Test
	public void 정상동작확인_신한은행() {
		for(String[] data : dataArray) {
			JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
					.setBank_cd(data[0])
					.setSearch_acct_no(data[1])
					.setAcnm_no(data[2])
					.setIche_amt(data[3])
					.setTrsc_seq_no(data[4])
					.build();
			
			VerifyRealName verifyRealName = new VerifyRealName();
			String result = verifyRealName.verify(jsonData.toString());
			
			System.out.println("result : " + result.trim());
			
			assertNotNull(result);
			assertEquals("0000", getDataFromResponse(result, "RSLT_CD"));
		}
		
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
