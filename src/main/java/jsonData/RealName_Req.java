package jsonData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class RealName_Req {
	private final String secr_key;
	private final String key;
	private String bank_cd;
	private String search_acct_no;
	private String acnm_no;
	private String iche_amt = "";
	private String trsc_seq_no;
	
	private RealName_Req(Builder builder) {
		this.secr_key = builder.secr_key;
		this.key = builder.key;
		this.bank_cd = builder.bank_cd;
		this.search_acct_no = builder.search_acct_no;
		this.acnm_no = builder.acnm_no;
		this.iche_amt = builder.iche_amt;
		this.trsc_seq_no = builder.trsc_seq_no;
	}
	
	@SuppressWarnings("unchecked")
	public String getJsonData() {
		JSONObject jsonData = new JSONObject();
		jsonData.put(JsonData_InputType.SECR_KEY, this.secr_key);
		jsonData.put(JsonData_InputType.KEY, this.key);
		
		JSONArray reqData = new JSONArray();
		JSONObject reqDataValue = new JSONObject();
		reqDataValue.put(JsonData_InputType.BANK_CD, this.bank_cd);
		reqDataValue.put(JsonData_InputType.SEARCH_ACCT_NO, this.search_acct_no);
		reqDataValue.put(JsonData_InputType.ACNM_NO, this.acnm_no);
		reqDataValue.put(JsonData_InputType.ICHE_AMT, this.iche_amt);
		reqDataValue.put(JsonData_InputType.TRSC_SEQ_NO, this.trsc_seq_no);
		reqData.add(reqDataValue);
		
		jsonData.put(JsonData_InputType.REQ_DATA, reqData);
		
		System.out.println("RealName_Req jsonData : " + jsonData);
		
		return jsonData.toJSONString();
	}

	public static class Builder{
		private final String secr_key;
		private final String key;
		private String bank_cd;
		private String search_acct_no;
		private String acnm_no;
		private String iche_amt = "";
		private String trsc_seq_no;
		
		public Builder(String secr_key, String key) {
			this.secr_key = secr_key;
			this.key = key;
		}
		
		public Builder setBank_cd(String bank_cd) {
			this.bank_cd = bank_cd;
			return this;
		}
		
		public Builder setSearch_acct_no(String search_acct_no) {
			this.search_acct_no = search_acct_no;
			return this;
		}
		
		public Builder setAcnm_no(String acnm_no) {
			this.acnm_no = acnm_no;
			return this;
		}
		
		public Builder setIche_amt(String iche_amt) {
			this.iche_amt = iche_amt;
			return this;
		}
		
		public Builder setTrsc_seq_no(String trsc_seq_no) {
			this.trsc_seq_no = trsc_seq_no;
			return this;
		}
		
		public RealName_Req build() {
			return new RealName_Req(this);
		}
	}

	private enum JsonData_InputType {
		SECR_KEY, KEY, REQ_DATA, BANK_CD, SEARCH_ACCT_NO, ACNM_NO, ICHE_AMT, TRSC_SEQ_NO
	}
	
	enum JsonData_OutputType{
		RSLT_CD, RSLT_MG, RESP_DATA, ACCT_NM, TRSC_SEQ_NO
	}
	
}
