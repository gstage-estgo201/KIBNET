package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import common.CommonVariables;

public class VerifyAccountTests {
	private Set<String> randomNumSet = new HashSet<String>();
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void 계좌검증_정상동작_확인() {
//	기준		화면설계서 https://4akpgl.axshare.com/#g=1&p=%EB%B3%B8%EC%9D%B8_%EA%B3%84%EC%A2%8C_%EB%93%B1%EB%A1%9D			
//	Step1	coocon 성명조회 : 4. 본인 계좌 등록 신청 버튼 클릭 시  1.예금주, 2.예금은행, 3.계좌번호를 기준으로 아래순서로 서비스 실행
//	Step2	kibnet 은행시간검증 -> 
//	Step3	kibnet 계좌검증 수행함
//	Step4	kibnet 회원이 통장에 찍힌 5.인증번호 입력 후 
//	Step5			6.인증번호 확인 버튼 클릭
//	Step6	kibnet 계좌검증확인 서비스 실행
		
//		Step1
		String randomNum = getRandomNum();
		String bank_CD = Bank_CD.농협은행.getCD();	//	2.예금은행 from user input
		String acct_no = "14902597746";			//	3.계좌번호 from user input
		
		JsonData_RealName jsonData = new JsonData_RealName.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd(bank_CD)
				.setSearch_acct_no(acct_no)
				.setAcnm_no("")
				.setIche_amt("0")
				.setTrsc_seq_no(randomNum)
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", JsonUtil.getDataFromJsonObject(result, "RSLT_CD"));
		
		String resp_data = JsonUtil.getJsonObjectInJsonArray(result, "RESP_DATA");
		
		assertEquals(randomNum, JsonUtil.getDataFromJsonObject(resp_data, "TRSC_SEQ_NO"));		//	거래일련번호
		
		//	TODO 본인 계좌 등록화면의 1.예금주와 동일한지 확인한다.
//		assertEquals(randomNum, getDataFromJsonObject(resp_data, "ACCT_NM"));			//	1.예금주
				
		JsonData_Account jsonData_720 = new JsonData_Account(CommonVariables.ID, CommonVariables.CRYPT_KEY);
		jsonData_720.setJsonData_100("004");
		
//		Step2
//		은행시간 검증
		VerifyAccount verifyAccount = new VerifyAccount();
		String result_100 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_100, jsonData_720.getUrlString());
		System.out.println("result_100 : " + result_100);
		assertEquals("0000", JsonUtil.getDataFromJsonObject(result_100, "RC"));
		
		String encEV_100 = JsonUtil.getDataFromJsonObject(result_100, "EV");
		
		String ret_fnni_cd = JsonUtil.getDecryptDataFromJsonObject(encEV_100, "fnni_cd", jsonData_720);
		String current_dtime = JsonUtil.getDecryptDataFromJsonObject(encEV_100, "current_dtime", jsonData_720);
		String svc_stop_sdtime = JsonUtil.getDecryptDataFromJsonObject(encEV_100, "svc_stop_sdtime", jsonData_720);
		String svc_stop_edtime = JsonUtil.getDecryptDataFromJsonObject(encEV_100, "svc_stop_edtime", jsonData_720);
		
		assertFalse(verifyAccount.isBankSvcTime(current_dtime, svc_stop_sdtime, svc_stop_edtime));	//	은행점검시간이 아님을 확임한다
		// TODO 은행점검시간일 경우 elert popup을 노출 시킨다.
		
//		Step3
		
		jsonData_720.setJsonData_720("004", "772210258507", "홍길동");
		String result_720 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_720, jsonData_720.getUrlString());
		System.out.println("result_720 : " + result_720);
		assertEquals("0000", JsonUtil.getDataFromJsonObject(result_720, "RC"));
		
		String encEV_720 = JsonUtil.getDataFromJsonObject(result_720, "EV");
		
		String verify_tr_dt = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_tr_dt", jsonData_720);
		String verify_tr_tm = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_tr_tm", jsonData_720);
		String verify_tr_no = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_tr_no", jsonData_720);
		String verify_len = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_len", jsonData_720);
		String verify_txt = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_txt", jsonData_720);
		
		System.out.println("verify_tr_dt : " + verify_tr_dt);
		System.out.println("verify_tr_tm : " + verify_tr_tm);
		System.out.println("verify_tr_no : " + verify_tr_no);
		System.out.println("verify_len : " + verify_len);
		System.out.println("verify_txt : " + verify_txt);
		
		String verify_val = "123";	// Test server에서는 123입력 시 성공, 운영에서는 실제 고객 통장에 찍힌 검증번호를 입력하여야 함
		jsonData_720.setJsonData_721(verify_tr_dt, verify_tr_no, verify_val);
		String result_721 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_721, jsonData_720.getUrlString());
		System.out.println("result_721 : " + result_721);
		assertEquals("0000", JsonUtil.getDataFromJsonObject(result_721, "RC"));
		
		String encEV_721 = JsonUtil.getDataFromJsonObject(result_720, "EV");
		assertEquals(verify_tr_dt, JsonUtil.getDecryptDataFromJsonObject(encEV_721, "verify_tr_dt", jsonData_720));
		assertEquals(verify_tr_no, JsonUtil.getDecryptDataFromJsonObject(encEV_721, "verify_tr_no", jsonData_720));
		assertEquals(verify_val, JsonUtil.getDecryptDataFromJsonObject(encEV_721, "verify_val", jsonData_720));
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
