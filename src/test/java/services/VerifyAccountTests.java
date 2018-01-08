package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import common.CommonVariables;

public class VerifyAccountTests {
	private Set<String> randomNumSet = new HashSet<String>();
	
	@Test
	public void 계좌검증_정상동작_확인() {
//	기준		화면설계서 https://4akpgl.axshare.com/#g=1&p=%EB%B3%B8%EC%9D%B8_%EA%B3%84%EC%A2%8C_%EB%93%B1%EB%A1%9D
//	Step1			4. 본인 계좌 등록 신청 버튼 클릭 시 1.예금주, 2.예금은행, 3.계좌번호를 기준으로 아래순서로 서비스 실행
//	Step2	coocon 성명조회 -> 
//	Step3	kibnet 은행시간검증 -> 
//	Step4	kibnet 계좌검증 수행함
//	Step5	kibnet 회원이 통장에 찍힌 5.인증번호 입력 후 
//	Step6			6.인증번호 확인 버튼 클릭
//	Step7	kibnet 계좌검증확인 서비스 실행
		
//		Step1
		String randomNum = getRandomNum();
		String bank_CD = Bank_CD.농협은행.getCD();	//	2.예금은행
		String acct_no = "14902597746";			//	3.계좌번호
		
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd(bank_CD)
				.setSearch_acct_no(acct_no)
				.setAcnm_no("")
				.setIche_amt("0")
				.setTrsc_seq_no(randomNum)
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromJsonObject(result, "RSLT_CD"));
		
		String resp_data = getJsonObjectInJsonArray(result, "RESP_DATA");
		
		assertEquals(randomNum, getDataFromJsonObject(resp_data, "TRSC_SEQ_NO"));		//	거래일련번호
		//	TODO 본인 계좌 등록화면의 1.예금주와 동일한지 확인한다.
		assertEquals(randomNum, getDataFromJsonObject(resp_data, "ACCT_NM"));			//	1.예금주		
		
	}
	
	private String getDataFromJsonObject(String response, String key) {
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
	
	private String getJsonObjectInJsonArray(String response, String key) {
		JSONParser parser = new JSONParser();
		String jsonArrayString = null;
		String result = null;
		try {
			JSONObject jsonObject = (JSONObject)parser.parse(response);
			jsonArrayString = jsonObject.get(key).toString();
			JSONArray jsonArray = (JSONArray)parser.parse(jsonArrayString);
			
			result = jsonArray.get(0).toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private String getRandomNum() {
		Random random = new Random();
		int number = random.nextInt(1000000) + 100000;
		if(number > 1000000) {
			number = number - 100000;
		}
		
		String numberString = "0" + String.valueOf(number);
		if(randomNumSet.contains(numberString)) {
			return getRandomNum();
		}else {
			randomNumSet.add(numberString);
			return numberString;
		}
	}
}
