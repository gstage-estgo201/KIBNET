package services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;

import com.checkpay.util.SecurityUtil;

public class JsonData_Account {
	private final String id, crypt_key;
	private String trx_dt, trx_tm;
	private String jsonDataString = null;
	
	public JsonData_Account(String id, String crypt_key) {
		this.id = id;
		this.crypt_key = crypt_key;
	}
	
	public String get_crypt_key() {
		return this.crypt_key;
	}
	
	public String getUrlString() {
		String urlString = null;

		trx_dt = new SimpleDateFormat("yyyyMMdd").format(new Date()); 
		trx_tm = new SimpleDateFormat("HHmmss").format(new Date());
		
		if(jsonDataString == null) {
			throw new RuntimeException("please set json data string");
		}
		
		String reqEV = null;
		String reqVV = null;
		try {
			reqEV = SecurityUtil.EncryptAesBase64(trx_dt + trx_tm + jsonDataString, this.crypt_key, true); 
			reqVV = SecurityUtil.getHmacSha256(jsonDataString, this.crypt_key, true);	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(reqEV == null || reqVV == null) {
			System.out.println("EV : " + reqEV + ", VV : " + reqVV);
			return null;
		}
		
		urlString = "ID=" + this.id
					+ "&RQ_DTIME=" + trx_dt + trx_tm
					+ "&TNO=" + trx_dt + trx_tm
					+ "&EV=" + reqEV
			        + "&VV=" + reqVV 
			        + "&EM=AES" 
			        + "&VM=HmacSHA256";
		
		jsonDataString = null;
		return urlString;
	}	

	@SuppressWarnings("unchecked")
	public void setJsonData_100(String fnni_cd) {
		JSONObject jsonData = new JSONObject();
		jsonData.put(InputType.fnni_cd, fnni_cd);
		
		jsonDataString = jsonData.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public void setJsonData_720(String fnni_cd, String acct_no, String memb_nm) {
		JSONObject jsonData = new JSONObject();
		jsonData.put(InputType.fnni_cd, fnni_cd);
		jsonData.put(InputType.acct_no, acct_no);
		jsonData.put(InputType.memb_nm, memb_nm);
		jsonData.put(InputType.verify_tp, "N");
		jsonData.put(InputType.ptst_txt, "체크");
		
		jsonDataString = jsonData.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public void setJsonData_721(String verify_tr_dt, String verify_tr_no, String verify_val) {
		JSONObject jsonData = new JSONObject();
		jsonData.put(InputType.verify_tr_dt, verify_tr_dt);
		jsonData.put(InputType.verify_tr_no, verify_tr_no);
		jsonData.put(InputType.verify_val, verify_val);
		
		jsonDataString = jsonData.toJSONString();
	}

	private enum InputType {
		fnni_cd, acct_no, memb_nm, verify_tp, ptst_txt,
		verify_tr_dt, verify_tr_no, verify_val;	//	계좌번호 검증확인(CPIF_AFFL_721.jct) 용도
	}
}
