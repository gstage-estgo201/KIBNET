package jsonData;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

public class TotalJsonDataTests {
	
	private ObjectMapper mapper;
	
	@Before
	public void before() {
		mapper = new ObjectMapper();
	}
	
	@Test
	public void account_100_res_test() {
		String result = "{\"VV\":\"3Kgv5ZkECaj7FCUbmgbZfZniXOIx1IWDTaqgykY1L0g%3D\",\"COMMON_HEAD\":{\"ERROR\":false,\"MESSAGE\":\"\",\"CODE\":\"\"},\"RM\":\"정상\",\"VM\":\"HmacSHA256\",\"TNO\":\"20180109184511\",\"EV\":\"64jLj%2FSk%2FaCcA71FbZEqd59iY5hwdkMYtb6GtEYcuum5wkEeaCTlGv9WMNhE4cI53DOISz9OWHekkBM1SaM2n7Oos48fUWXLC6DZnOqwg1Zv%2F8oKy%2BR8Ni6gToS%2B27Go%2B2o0vtCDCClMLSJAsVvuyWctnC80ewbtux%2BU%2FBc2x3LBhWpEHEvlPTKJ2eKQ0I8B\",\"ID\":\"03420001\",\"RS_DTIME\":\"20180109184512\",\"EM\":\"AES\",\"RC\":\"0000\"}";

		mapper = new ObjectMapper();
		Account_100_Res account_100_res = new Account_100_Res();

		try {
			account_100_res = mapper.readValue(result, Account_100_Res.class);
			System.out.println("Account_100 pretty response : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(account_100_res));
			
			// assert Common data
			assertNotNull(account_100_res.getVv());
			assertNotNull(account_100_res.getRm());
			assertNotNull(account_100_res.getVm());
			assertNotNull(account_100_res.getTno());
			assertNotNull(account_100_res.getId());
			assertNotNull(account_100_res.getRs_dtime());
			assertNotNull(account_100_res.getEm());
			assertNotNull(account_100_res.getRc());
			
			// assert Common_Head data
			assertNotNull(account_100_res.getError());
			assertNotNull(account_100_res.getMessage());
			assertNotNull(account_100_res.getCode());
			
			// assert output data in EV
			assertNotNull(account_100_res.getFnni_cd());
			assertNotNull(account_100_res.getSvc_stop_sdtime());
			assertNotNull(account_100_res.getCurrent_dtime());
			assertNotNull(account_100_res.getSvc_stop_edtime());
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void accunt_720_res_test() {
		String result = "{\"VV\":\"%2B%2FhxacyW%2B1Bs2%2FicP7JtcuSUsFe0bGsc55QbWCTQbos%3D\",\"COMMON_HEAD\":{\"ERROR\":false,\"MESSAGE\":\"\",\"CODE\":\"\"},\"RM\":\"정상\",\"VM\":\"HmacSHA256\",\"TNO\":\"20180109184512\",\"EV\":\"64jLj%2FSk%2FaCcA71FbZEqd8Uf8rRRR1ItT15LRHIYvo%2BFKghtKlRseRLi7iFzM%2B6cKYjnSZCEk%2BHq%2BKNdJf2PzPsB2TGo3RE8v5svlP5BqR%2BX3rDZgsIq7ynbMrkK8q6Q4mMBAbJjvOpk4uwB7iUkmRh0w4Cy30JCkT%2FQLGWNlpI%3D\",\"ID\":\"03420001\",\"RS_DTIME\":\"20180109184512\",\"EM\":\"AES\",\"RC\":\"0000\"}";
		
		mapper = new ObjectMapper();
		Account_720_Res account_720_res = new Account_720_Res();
		
		try {
			account_720_res = mapper.readValue(result, Account_720_Res.class);
			System.out.println("Account_720_res pretty : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(account_720_res));
			
			// assert Common data
			assertNotNull(account_720_res.getVv());
			assertNotNull(account_720_res.getRm());
			assertNotNull(account_720_res.getVm());
			assertNotNull(account_720_res.getTno());
			assertNotNull(account_720_res.getId());
			assertNotNull(account_720_res.getRs_dtime());
			assertNotNull(account_720_res.getEm());
			assertNotNull(account_720_res.getRc());
			
			// assert Common_Head data
			assertNotNull(account_720_res.getError());
			assertNotNull(account_720_res.getMessage());
			assertNotNull(account_720_res.getCode());
			
			// assert output data in EV
			assertNotNull(account_720_res.getVerify_tr_dt());
			assertNotNull(account_720_res.getVerify_tr_tm());
			assertNotNull(account_720_res.getVerify_tr_no());
			assertNotNull(account_720_res.getVerify_len());
			assertNotNull(account_720_res.getVerify_txt());

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void account_721_res_Test() {
		String result = "{\"VV\":\"4O5nAc6JpviOGMyN9WfTwxZJPAeRwXocg2mgNvZ0wsc%3D\",\"COMMON_HEAD\":{\"ERROR\":false,\"MESSAGE\":\"\",\"CODE\":\"\"},\"RM\":\"정상\",\"VM\":\"HmacSHA256\",\"TNO\":\"20180109184512\",\"EV\":\"t0e4oD%2Fo9vDV%2BpFH4Qx0rOmpvYDR8j9OJdBIm1SqOjN8M4A8ZLcVm%2F7Dn5JVKOhUkFJ1CI8sqN54IMTTOxnc1VxaqyRPKaeRbxSPeKlGQXKNXPERLY1jogxUkV%2BQO9Pp\",\"ID\":\"03420001\",\"RS_DTIME\":\"20180109184513\",\"EM\":\"AES\",\"RC\":\"0000\"}";
		
		mapper = new ObjectMapper();
		Account_721_Res account_721_res = new Account_721_Res();
		
		try {
			account_721_res = mapper.readValue(result,  Account_721_Res.class);
			System.out.println("account_721_res pretty : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(account_721_res));
			
			// assert Common data
			assertNotNull(account_721_res.getVv());
			assertNotNull(account_721_res.getRm());
			assertNotNull(account_721_res.getVm());
			assertNotNull(account_721_res.getTno());
			assertNotNull(account_721_res.getId());
			assertNotNull(account_721_res.getRs_dtime());
			assertNotNull(account_721_res.getEm());
			assertNotNull(account_721_res.getRc());
			
			// assert Common_Head data
			assertNotNull(account_721_res.getError());
			assertNotNull(account_721_res.getMessage());
			assertNotNull(account_721_res.getCode());
			
			// assert output data in EV
			assertNotNull(account_721_res.getVerify_tr_dt());
			assertNotNull(account_721_res.getVerify_tr_no());
			assertNotNull(account_721_res.getVerify_val());
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void realName_Res_Test() {
		String result = "{\"RESP_DATA\":[{\"ACCT_NM\":\"달나라가자\",\"TRSC_SEQ_NO\":\"0451677\"}],\"RSLT_MSG\":\"정상처리\",\"RSLT_CD\":\"000\"}";
		
		mapper = new ObjectMapper();
		RealName_Res realName_res = new RealName_Res();
		
		try {
			realName_res = mapper.readValue(result, RealName_Res.class);
			System.out.println("pretty responseData : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(realName_res));
			
			assertNotNull(realName_res.getRslt_cd());
			assertNotNull(realName_res.getRslt_msg());
			assertNotNull(realName_res.getResp_data());
			assertNotNull(realName_res.getResp_data().get(0).getAcct_nm());
			assertNotNull(realName_res.getResp_data().get(0).getTrsc_seq_no());
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
