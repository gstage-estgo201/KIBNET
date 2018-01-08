package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import common.CommonVariables;
import jsonData.ResponseData;

public class VerifyAccountTests {
	private Set<String> randomNumSet = new HashSet<String>();
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void 계좌검증_정상동작_확인() {
//	기준		화면설계서 https://4akpgl.axshare.com/#g=1&p=%EB%B3%B8%EC%9D%B8_%EA%B3%84%EC%A2%8C_%EB%93%B1%EB%A1%9D			
//			1.예금주, 2.예금은행, 3.계좌번호를 입력 후 4. 본인 계좌 등록 신청 버튼 클릭 시
//	Step1	coocon 성명조회
//	Step2	kibnet 은행시간검증 
//	Step3	kibnet 계좌검증 수행함
//			회원이 통장에 찍힌 5.인증번호 입력 후 6.인증번호 확인 버튼 클릭
//	Step4	kibnet 계좌검증확인 서비스 실행
		
//		Step1 - 성명조회
		String randomNum = getRandomNum();		//	거래일련번호 TODO BACK
		String bank_cd = Bank_CD.농협은행.getCD();	//	2.예금은행 TODO FRONT
		String acct_no = "14902597746";			//	3.계좌번호 TODO FRONT
		
		JsonData_RealName jsonData_realName = new JsonData_RealName.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd(bank_cd)
				.setSearch_acct_no(acct_no)
				.setAcnm_no("")
				.setIche_amt("0")
				.setTrsc_seq_no(randomNum)
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData_realName.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("mapper : " + mapper.convertValue(result, ResponseData.class));
		
		assertEquals("000", JsonUtil.getDataFromJsonObject(result, "RSLT_CD"));
		// TODO 예금주명 일치 시 1원 입금 정보 popup 노출
		String resp_data = JsonUtil.getJsonObjectInJsonArray(result, "RESP_DATA");
		assertEquals(randomNum, JsonUtil.getDataFromJsonObject(resp_data, "TRSC_SEQ_NO"));		//	거래일련번호 확인
		
		//	TODO 본인 계좌 등록화면의 1.예금주와 동일한지 확인한다.
//		assertEquals("예금주", getDataFromJsonObject(resp_data, "ACCT_NM"));			//	1.예금주 TODO FRONT
				
//		Step2 - 은행시간 검증
		JsonData_Account jsonData_account = new JsonData_Account(CommonVariables.ID, CommonVariables.CRYPT_KEY);
		jsonData_account.setJsonData_100("004");	//	2.예금은행 TODO FRONT
		
		VerifyAccount verifyAccount = new VerifyAccount();
		String result_100 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_100, jsonData_account.getUrlString());
		System.out.println("result_100 : " + result_100);
		assertEquals(CommonVariables.CODE_0000, JsonUtil.getDataFromJsonObject(result_100, "RC"));
		
		String encEV_100 = JsonUtil.getDataFromJsonObject(result_100, "EV");
		
		String current_dtime = JsonUtil.getDecryptDataFromJsonObject(encEV_100, "current_dtime", jsonData_account);
		String svc_stop_sdtime = JsonUtil.getDecryptDataFromJsonObject(encEV_100, "svc_stop_sdtime", jsonData_account);
		String svc_stop_edtime = JsonUtil.getDecryptDataFromJsonObject(encEV_100, "svc_stop_edtime", jsonData_account);
		
		assertFalse(verifyAccount.isBankSvcTime(current_dtime, svc_stop_sdtime, svc_stop_edtime));	//	은행점검시간이 아님을 확임한다
		// TODO 은행점검시간일 경우 elert popup을 노출 시킨다.
		
//		Step3 - 계좌검증
		jsonData_account.setJsonData_720(Bank_CD.국민은행.getCD(), "772210258507", "홍길동");	//	1.예금주2.예금은행3계좌번호 TODO FRONT
		String result_720 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_720, jsonData_account.getUrlString());
		System.out.println("result_720 : " + result_720);
		assertEquals(CommonVariables.CODE_0000, JsonUtil.getDataFromJsonObject(result_720, "RC"));
		// TODO 유효시간(10분 초과 시) alert popup 노출
		String encEV_720 = JsonUtil.getDataFromJsonObject(result_720, "EV");
		
		String verify_tr_dt = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_tr_dt", jsonData_account);
		String verify_tr_tm = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_tr_tm", jsonData_account);
		String verify_tr_no = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_tr_no", jsonData_account);
		String verify_len = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_len", jsonData_account);
		String verify_txt = JsonUtil.getDecryptDataFromJsonObject(encEV_720, "verify_txt", jsonData_account);
		
		System.out.println("verify_tr_dt : " + verify_tr_dt);
		System.out.println("verify_tr_tm : " + verify_tr_tm);
		System.out.println("verify_tr_no : " + verify_tr_no);
		System.out.println("verify_len : " + verify_len);
		System.out.println("verify_txt : " + verify_txt);
		
//		Step4 - 계좌확인
		// TODO 인증번호 미입력 후 버틀 클릭 시 "인증번호를 입력해 주세요" 팝업 노출
		String verify_val = "123";	// Test server에서는 123입력 시 성공, 운영에서는 실제 고객 통장에 찍힌 검증번호를 입력하여야 함(5인증번호)
		jsonData_account.setJsonData_721(verify_tr_dt, verify_tr_no, verify_val);
		String result_721 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_721, jsonData_account.getUrlString());
		System.out.println("result_721 : " + result_721);
		assertEquals(CommonVariables.CODE_0000, JsonUtil.getDataFromJsonObject(result_721, "RC"));
		
		String encEV_721 = JsonUtil.getDataFromJsonObject(result_721, "EV");
		assertEquals(verify_tr_dt, JsonUtil.getDecryptDataFromJsonObject(encEV_721, "verify_tr_dt", jsonData_account));
		assertEquals(verify_tr_no, JsonUtil.getDecryptDataFromJsonObject(encEV_721, "verify_tr_no", jsonData_account));
		assertEquals(verify_val, JsonUtil.getDecryptDataFromJsonObject(encEV_721, "verify_val", jsonData_account));
		// TODO 인증번호가 일치하지 않을 시 "" alert popup 노출
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
