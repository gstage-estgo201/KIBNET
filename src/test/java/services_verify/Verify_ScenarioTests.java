package services_verify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import common.Bank_CD;
import common.CommonVariables;
import jsonData.Account_100_Res;
import jsonData.Account_720_Res;
import jsonData.Account_721_Res;
import jsonData.Account_Req;
import jsonData.RealName_Req;
import jsonData.RealName_Res;
import services_veirfy.VerifyAccount;
import services_veirfy.VerifyRealName;
import util.Utils;

public class Verify_ScenarioTests {
	
	private ObjectMapper mapper;
	
	@Before
	public void before() {
		mapper = new ObjectMapper();
	}
	
	@Test
	public void 본인계좌등록_정상동작_확인() {
//	기준		화면설계서 https://4akpgl.axshare.com/#g=1&p=%EB%B3%B8%EC%9D%B8_%EA%B3%84%EC%A2%8C_%EB%93%B1%EB%A1%9D			
//			1.예금주, 2.예금은행, 3.계좌번호를 입력 후 4. 본인 계좌 등록 신청 버튼 클릭 시
//	Step1	coocon 성명조회
//	Step2	kibnet 은행시간검증 
//	Step3	kibnet 계좌검증 수행함
//			회원이 통장에 찍힌 5.인증번호 입력 후 6.인증번호 확인 버튼 클릭
//	Step4	kibnet 계좌검증확인 서비스 실행
				
		String bank_cd = "";			//	2.예금은행 TODO FRONT
		String acct_no = "";			//	3.계좌번호 TODO FRONT
		String name = "";
		
		String[][] inputDatas = {
				{Bank_CD.농협은행.getCD(), "14902597746", "홍길동"},
				{Bank_CD.농협은행.getCD(), "3021076539731", "홍길동"},
				{Bank_CD.국민은행.getCD(), "65270104237567", "홍길동"},
				{Bank_CD.국민은행.getCD(), "772210258507", "홍길동"},
				{Bank_CD.수협중앙회.getCD(), "02602123171", "홍길동"}};
		
		for(String[] inputData : inputDatas) {
			bank_cd = inputData[0];
			acct_no = inputData[1];
			name = inputData[2];
			
			AccountRegistration_Scenario(bank_cd, acct_no, name);
		}
		
		
	}
	
	// https://4akpgl.axshare.com/#g=1&p=%EB%B3%B8%EC%9D%B8_%EA%B3%84%EC%A2%8C_%EB%93%B1%EB%A1%9D
	private void AccountRegistration_Scenario(String bank_cd, String acct_no, String name) {
		System.out.println("===== Scenario Test Start ====");
		System.out.println("banj_cd : " + bank_cd + ", acct_no : " + acct_no + ", name : " + name);
		
//		Step1 - 성명조회
		String randomNum = Utils.getRandomNum();	//	거래일련번호 TODO BACK
		
		RealName_Req realName_req = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd(bank_cd)
				.setSearch_acct_no(acct_no)
				.setAcnm_no("")
				.setIche_amt("0")
				.setTrsc_seq_no(randomNum)
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(realName_req.getJsonData());
		
		assertNotNull(result);
		mapper = new ObjectMapper();
		RealName_Res responseData = new RealName_Res();

		try {
			responseData = mapper.readValue(result, RealName_Res.class);
			System.out.println("pretty responseData realName : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseData));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(CommonVariables.CODE_000, responseData.getRslt_cd());
		// TODO 예금주명 일치 시 1원 입금 정보 popup 노출
		assertEquals(randomNum, responseData.getResp_data().get(0).getTrsc_seq_no());		//	거래일련번호 확인
		
		//	TODO 본인 계좌 등록화면의 1.예금주와 동일한지 확인한다.
//		assertEquals(name, responseData.getResp_data().get(0).getAcct_nm());			//	1.예금주 TODO FRONT
		
//		아래 테스트 과정에서 노출된 Error 및 운영시 Error에 대한 처리 시나리오 확인 필요
/*		User Issue : (203 Error : 계좌번호오류), (247 Error : 기타처리불가), (344 Error : 해당계좌없음)
		Bank Iuuse : (X11 Error : 해당제휴사 확인요망), (525 Error : 거래불가)*/
		
//		Step2 - 은행시간 검증
		Account_Req account_req = new Account_Req(CommonVariables.ID, CommonVariables.CRYPT_KEY);
		account_req.setJsonData_100(bank_cd);	//	2.예금은행 TODO FRONT
		
		VerifyAccount verifyAccount = new VerifyAccount();
		String result_100 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_100, account_req.getUrlString());
			
		assertNotNull(result_100);
		
		Account_100_Res account_100_res = new Account_100_Res();

		try {
			account_100_res = mapper.readValue(result_100, Account_100_Res.class);
			System.out.println("pretty responseData account_100 : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(account_100_res));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(CommonVariables.CODE_0000, account_100_res.getRc());
		
		String current_dtime = account_100_res.getCurrent_dtime();
		String svc_stop_sdtime = account_100_res.getSvc_stop_sdtime();
		String svc_stop_edtime = account_100_res.getSvc_stop_edtime();
				
		assertFalse(verifyAccount.isBankSvcTime(current_dtime, svc_stop_sdtime, svc_stop_edtime));	//	은행점검시간이 아님을 확임한다
		// TODO 은행점검시간일 경우 elert popup을 노출 시킨다.
		
//		Step3 - 계좌검증
		account_req.setJsonData_720(bank_cd, acct_no, name);	//	1.예금주2.예금은행3계좌번호 TODO FRONT
		String result_720 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_720, account_req.getUrlString());
		
		assertNotNull(result_720);
		
		Account_720_Res account_720_res = new Account_720_Res();
		
		try {
			account_720_res = mapper.readValue(result_720, Account_720_Res.class);
			System.out.println("pretty responseData account_720 : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(account_720_res));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(CommonVariables.CODE_0000, account_720_res.getRc());
		// TODO 유효시간(10분 초과 시) alert popup 노출
		
		String verify_tr_dt = account_720_res.getVerify_tr_dt();
		String verify_tr_no = account_720_res.getVerify_tr_no();
		
//		Step4 - 계좌확인
		// TODO 인증번호 미입력 후 버틀 클릭 시 "인증번호를 입력해 주세요" 팝업 노출
		String verify_val = "123";	// Test server에서는 123입력 시 성공, 운영에서는 실제 고객 통장에 찍힌 검증번호를 입력하여야 함(5인증번호)
		account_req.setJsonData_721(verify_tr_dt, verify_tr_no, verify_val);
		String result_721 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_721, account_req.getUrlString());
		
		assertNotNull(result_721);
		
		Account_721_Res account_721_res = new Account_721_Res();
		try {
			account_721_res = mapper.readValue(result_721, Account_721_Res.class);
			System.out.println("pretty responseData account_721 : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(account_721_res));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(CommonVariables.CODE_0000, account_721_res.getRc());
		
		assertEquals(verify_tr_dt, account_721_res.getVerify_tr_dt());
		assertEquals(verify_tr_no, account_721_res.getVerify_tr_no());
		assertEquals(verify_val, account_721_res.getVerify_val());
		// TODO 인증번호가 일치하지 않을 시 "" alert popup 노출
		
		System.out.println("===== Scenario Test End ====");
	}
}
