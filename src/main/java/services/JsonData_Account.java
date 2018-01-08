package services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;

import com.checkpay.util.SecurityUtil;

public class JsonData_Account {
	private final String id, crypt_key;
	private String fnni_cd, acct_no, memb_nm, ptst_txt, verify_tp;
	private String trx_dt, trx_tm;
	
	public JsonData_Account(Builder builder) {
		this.id = builder.id;
		this.crypt_key = builder.crypt_key;
		this.fnni_cd = builder.fnni_cd;
		this.acct_no = builder.acct_no;
		this.memb_nm = builder.memb_nm;
		this.ptst_txt = builder.ptst_txt;
		this.verify_tp = builder.verify_tp;
	}
	
	@SuppressWarnings("unchecked")
	public String getUrlString() {
		String urlString = null;
		
		trx_dt = new SimpleDateFormat("yyyyMMdd").format(new Date()); 
		trx_tm = new SimpleDateFormat("HHmmss").format(new Date());
		
		JSONObject jsonData = new JSONObject();
		jsonData.put(InputType.fnni_cd, this.fnni_cd);
		jsonData.put(InputType.acct_no, this.acct_no);
		jsonData.put(InputType.memb_nm, this.memb_nm);
		jsonData.put(InputType.ptst_txt, this.ptst_txt);
		jsonData.put(InputType.verify_tp, this.verify_tp);
		
		String reqEV = null;
		String reqVV = null;
		try {
			reqEV = SecurityUtil.EncryptAesBase64(trx_dt + trx_tm + jsonData.toJSONString(), this.crypt_key, true); 
			reqVV = SecurityUtil.getHmacSha256(jsonData.toJSONString(), this.crypt_key, true);	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(reqEV == null || reqVV == null) {
			System.out.println("EV : " + reqEV + ", VV : " + reqVV);
			return null;
		}
		
		urlString = "ID=" + this.id
					+ "&RQ_DTIME=" + trx_dt + trx_tm
					+ "&TNO=" + trx_dt+trx_tm
					+ "&EV=" + reqEV
			        + "&VV=" + reqVV 
			        + "&EM=AES" 
			        + "&VM=HmacSHA256";
		
		return urlString;
	}
	
	public static class Builder {
		private final String id, crypt_key;
		private String fnni_cd, acct_no, memb_nm, ptst_txt, verify_tp;
		
		public Builder(String id, String crypt_key) {
			this.id = id;
			this.crypt_key = crypt_key;
		}
		
		public Builder setFnni_cd(String fnni_cd) {
			this.fnni_cd = fnni_cd;
			return this;
		}
		
		public Builder setAcct_no(String acct_no) {
			this.acct_no = acct_no;
			return this;
		}
		
		public Builder setMemb_nm(String memb_nm) {
			this.memb_nm = memb_nm;
			return this;
		}
		
		public Builder setPtst_txt(String ptst_txt) {
			this.ptst_txt = ptst_txt;
			return this;
		}
		
		public Builder setVerify_tp(String verify_tp) {
			this.verify_tp = verify_tp;
			return this;
		}
		
		public JsonData_Account build() {
			return new JsonData_Account(this);
		}
	}
	
	private enum InputType {
		fnni_cd, acct_no, memb_nm, ptst_txt, verify_tp,
		verify_tr_dt, verify_tr_no, verify_val;	//	계좌번호 검증확인(CPIF_AFFL_721.jct) 용도
		
	}
}
