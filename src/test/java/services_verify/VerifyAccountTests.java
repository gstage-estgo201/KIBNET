package services_verify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import common.Bank_CD;
import common.CommonVariables;
import jsonData.Account_100_Res;
import jsonData.Account_720_Res;
import jsonData.Account_721_Res;
import jsonData.Account_Req;
import services_veirfy.VerifyAccount;

public class VerifyAccountTests {
	
	@Test
	public void test() {
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
			
			Service100(bank_cd);						//	은행점검시간 검증
			Service720_721(bank_cd, acct_no, name);	//	계좌검증
		}		
	}
	
	private void Service100(String bank_cd) {
		Account_Req account_req = new Account_Req(CommonVariables.ID, CommonVariables.CRYPT_KEY);
		account_req.setJsonData_100(bank_cd);
		
		VerifyAccount verifyAccount = new VerifyAccount();
		String result_100 = verifyAccount.verify(CommonVariables.SERVICE_CONTENT_100, account_req.getUrlString());
			
		assertNotNull(result_100);
		
		Account_100_Res account_100_res = new Account_100_Res();
		ObjectMapper mapper = new ObjectMapper();
		
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
	}
	
	private void Service720_721(String bank_cd, String acct_no, String name){
		ObjectMapper mapper = new ObjectMapper();		

		Account_Req account_req = new Account_Req(CommonVariables.ID, CommonVariables.CRYPT_KEY);
		VerifyAccount verifyAccount = new VerifyAccount();
		
		account_req.setJsonData_720(bank_cd, acct_no, name);
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
		
		String verify_tr_dt = account_720_res.getVerify_tr_dt();
		String verify_tr_no = account_720_res.getVerify_tr_no();
		
		String verify_val = "123";
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
	}
	
	@Test
	public void 은행점검시간일_경우_반환값_확인() {
		VerifyAccount vAccount = new VerifyAccount();
		
		String current_dtime = null;
		String svc_stop_sdtime = null;
		String svc_stop_edtime = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();

		current_dtime = simpleDateFormat.format(cal.getTime());

		//	초 단위 확인
		cal = (Calendar.getInstance());
		cal.add(Calendar.SECOND, -1);
		svc_stop_sdtime = simpleDateFormat.format(cal.getTime());		//	1초 전
		
		cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 1);
		svc_stop_edtime = simpleDateFormat.format(cal.getTime());		//	1초 후
		
		assertTrue(vAccount.isBankSvcTime(current_dtime, svc_stop_sdtime, svc_stop_edtime));
		
		// 분 단위 확인
		cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1);
		svc_stop_sdtime = simpleDateFormat.format(cal.getTime());		//	1분 전
		
		cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 1);
		svc_stop_edtime = simpleDateFormat.format(cal.getTime());		//	1분 후
		
		assertTrue(vAccount.isBankSvcTime(current_dtime, svc_stop_sdtime, svc_stop_edtime));
		
		// 시간 단위 확인
		cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -1);
		svc_stop_sdtime = simpleDateFormat.format(cal.getTime());		//	1시간 전
		
		cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 1);
		svc_stop_edtime = simpleDateFormat.format(cal.getTime());		//	1시간 후
		
		assertTrue(vAccount.isBankSvcTime(current_dtime, svc_stop_sdtime, svc_stop_edtime));
		
		// Date 단위 확인
		cal = (Calendar.getInstance());
		cal.add(Calendar.DATE, -1);
		svc_stop_sdtime = simpleDateFormat.format(cal.getTime());		//	1일 전
		
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		svc_stop_edtime = simpleDateFormat.format(cal.getTime());		//	1일 후
		
		assertTrue(vAccount.isBankSvcTime(current_dtime, svc_stop_sdtime, svc_stop_edtime));
		
		// Month 단위 확인
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		svc_stop_sdtime = simpleDateFormat.format(cal.getTime());		//	1달 전
		
		cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		svc_stop_edtime = simpleDateFormat.format(cal.getTime());		//	1달 후
		
		assertTrue(vAccount.isBankSvcTime(current_dtime, svc_stop_sdtime, svc_stop_edtime));
		
		//	Year 단위 확인
		cal = (Calendar.getInstance());
		cal.add(Calendar.YEAR, -1);
		svc_stop_sdtime = simpleDateFormat.format(cal.getTime());		//	1년 전
		
		cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		svc_stop_edtime = simpleDateFormat.format(cal.getTime());		//	1분 후
		
		assertTrue(vAccount.isBankSvcTime(current_dtime, svc_stop_sdtime, svc_stop_edtime));
	}
}
