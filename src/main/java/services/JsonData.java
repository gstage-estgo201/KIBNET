package services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JsonData {
	private String secr_key;
	private String key;
	private String bank_cd;
	private String search_acct_no;
	private String acnm_no;
	private String iche_amt;
	private String trsc_seq_no;
	
	private JsonData(Builder builder) {
		this.secr_key = builder.secr_key;
		this.key = builder.key;
		this.bank_cd = builder.bank_cd;
		this.search_acct_no = builder.search_acct_no;
		this.acnm_no = builder.acnm_no;
		this.iche_amt = builder.iche_amt;
		this.trsc_seq_no = builder.trsc_seq_no;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		JSONObject jsonData = new JSONObject();
		jsonData.put(JsonDataType.SECR_KEY, this.secr_key);
		jsonData.put(JsonDataType.KEY, this.key);
		
		JSONArray reqData = new JSONArray();
		JSONObject reqDataValue = new JSONObject();
		reqDataValue.put(JsonDataType.BANK_CD, this.bank_cd);
		reqDataValue.put(JsonDataType.SEARCH_ACCT_NO, this.search_acct_no);
		reqDataValue.put(JsonDataType.ACNM_NO, this.acnm_no);
		reqDataValue.put(JsonDataType.ICHE_AMT, this.iche_amt);
		reqDataValue.put(JsonDataType.TRSC_SEQ_NO, this.trsc_seq_no);
		reqData.add(reqDataValue);
		
		jsonData.put("REQ_DATA", reqData);
		
		System.out.println("jsonData : " + jsonData);
		
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
		
		public JsonData build() {
			return new JsonData(this);
		}
	}

	private enum JsonDataType {
		SECR_KEY, KEY, BANK_CD, SEARCH_ACCT_NO, ACNM_NO, ICHE_AMT, TRSC_SEQ_NO
	}
	
	
}
