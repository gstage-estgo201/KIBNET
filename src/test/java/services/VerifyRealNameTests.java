package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import common.CommonVariables;
import jsonData.RealName_Req;

public class VerifyRealNameTests {
	private String[][] dataArray = {
			{"011", "", "860902", "0"}};
	
	@Test
	public void 정상동작확인_all() {
		for(String[] data : dataArray) {
			RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
					.setBank_cd(data[0])
					.setSearch_acct_no(data[1])
					.setAcnm_no(data[2])
					.setIche_amt(data[3])
					.setTrsc_seq_no(getRandomNum())
					.build();
			
			VerifyRealName verifyRealName = new VerifyRealName();
			String result = verifyRealName.verify(jsonData.getJsonData());
			
			System.out.println("result : " + result.trim());
			
			assertNotNull(result);
			assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
		}
	}
	
	@Test
	public void 정상동작확인_농협은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("011")
				.setSearch_acct_no("")
				.setAcnm_no("860902")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_산업은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("002")
				.setSearch_acct_no("")
				.setAcnm_no("640524")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_기업은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("003")
				.setSearch_acct_no("")
				.setAcnm_no("711205")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_기업은행2() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("003")
				.setSearch_acct_no("")
				.setAcnm_no("790924")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_국민은행() {
		// TODO 국민은행은 운영계좌로 확인 필요
	}
	
	@Test
	public void 정상동작확인_외환은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("005")
				.setSearch_acct_no("")
				.setAcnm_no("850101")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_수협() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("007")
				.setSearch_acct_no("")
				.setAcnm_no("710307")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}	
	
	@Test
	public void 정상동작확인_우리은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("020")
				.setSearch_acct_no("")
				.setAcnm_no("860914")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_제일은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("023")
				.setSearch_acct_no("")
				.setAcnm_no("110101")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_씨티은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("027")
				.setSearch_acct_no("")
				.setAcnm_no("771112")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_대구은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("031")
				.setSearch_acct_no("")
				.setAcnm_no("991111")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_부산은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("032")
				.setSearch_acct_no("")
				.setAcnm_no("500331")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_제주은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("035")
				.setSearch_acct_no("")
				.setAcnm_no("711111")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_전북은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("037")
				.setSearch_acct_no("")
				.setAcnm_no("700407")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_전북은행2() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("037")
				.setSearch_acct_no("")
				.setAcnm_no("700407")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_경남은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("039")
				.setSearch_acct_no("")
				.setAcnm_no("751105")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_새마을금고() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("045")
				.setSearch_acct_no("")
				.setAcnm_no("700101")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}

	@Test
	public void 정상동작확인_신협() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("048")
				.setSearch_acct_no("")
				.setAcnm_no("850104")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_신협2() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("048")
				.setSearch_acct_no("")
				.setAcnm_no("1148202152")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_우체국() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("071")
				.setSearch_acct_no("")
				.setAcnm_no("450429")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_신한은행() {
		RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("088")
				.setSearch_acct_no("")
				.setAcnm_no("3148204065")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.getJsonData());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
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
	
	private String getRandomNum() {
		Random random = new Random();
		int number = random.nextInt(1000000) + 100000;
		if(number > 1000000) {
			number = number - 100000;
		}
		return "0" + String.valueOf(number);
	}
}
