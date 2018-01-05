package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import common.CommonVariables;

public class VerifyRealNameTests {
	private String[][] dataArray = {
			{"011", "14902597746", "860902", "0"}};
	
	@Test
	public void 정상동작확인_all() {
		for(String[] data : dataArray) {
			JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
					.setBank_cd(data[0])
					.setSearch_acct_no(data[1])
					.setAcnm_no(data[2])
					.setIche_amt(data[3])
					.setTrsc_seq_no(getRandomNum())
					.build();
			
			VerifyRealName verifyRealName = new VerifyRealName();
			String result = verifyRealName.verify(jsonData.toString());
			
			System.out.println("result : " + result.trim());
			
			assertNotNull(result);
			assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
		}
	}
	
	@Test
	public void 정상동작확인_농협은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("011")
				.setSearch_acct_no("14902597746")
				.setAcnm_no("860902")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_산업은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("002")
				.setSearch_acct_no("02026007408704")
				.setAcnm_no("640524")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_기업은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("003")
				.setSearch_acct_no("21701322303023")
				.setAcnm_no("711205")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_기업은행2() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("003")
				.setSearch_acct_no("01904232902016")
				.setAcnm_no("790924")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
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
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("005")
				.setSearch_acct_no("620199910659")
				.setAcnm_no("850101")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_수협() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("007")
				.setSearch_acct_no("02602123171")
				.setAcnm_no("710307")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}	
	
	@Test
	public void 정상동작확인_우리은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("020")
				.setSearch_acct_no("1006502226237")
				.setAcnm_no("860914")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_제일은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("023")
				.setSearch_acct_no("86010014628")
				.setAcnm_no("110101")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_씨티은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("027")
				.setSearch_acct_no("3040176126401")
				.setAcnm_no("771112")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_대구은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("031")
				.setSearch_acct_no("505102678658")
				.setAcnm_no("991111")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_부산은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("032")
				.setSearch_acct_no("1010000014002")
				.setAcnm_no("500331")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_제주은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("035")
				.setSearch_acct_no("3301000013")
				.setAcnm_no("711111")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_전북은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("037")
				.setSearch_acct_no("501219999997")
				.setAcnm_no("700407")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_전북은행2() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("037")
				.setSearch_acct_no("501220048478")
				.setAcnm_no("700407")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_경남은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("039")
				.setSearch_acct_no("502210229948")
				.setAcnm_no("751105")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_새마을금고() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("045")
				.setSearch_acct_no("9003166420461")
				.setAcnm_no("700101")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}

	@Test
	public void 정상동작확인_신협() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("048")
				.setSearch_acct_no("132548099293")
				.setAcnm_no("850104")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_신협2() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("048")
				.setSearch_acct_no("135000020248")
				.setAcnm_no("1148202152")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_우체국() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("071")
				.setSearch_acct_no("01001702000029")
				.setAcnm_no("450429")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
		System.out.println("result : " + result.trim());
		
		assertNotNull(result);
		assertEquals("000", getDataFromResponse(result, "RSLT_CD"));
	}
	
	@Test
	public void 정상동작확인_신한은행() {
		JsonData jsonData = new JsonData.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
				.setBank_cd("088")
				.setSearch_acct_no("100020908927")
				.setAcnm_no("3148204065")
				.setIche_amt("0")
				.setTrsc_seq_no(getRandomNum())
				.build();
		
		VerifyRealName verifyRealName = new VerifyRealName();
		String result = verifyRealName.verify(jsonData.toString());
		
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
			number -= number - 100000;
		}
		return "0" + String.valueOf(number);
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
