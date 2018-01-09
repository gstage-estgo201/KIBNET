package jsonData;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RealName {
	
	@JsonProperty("RESP_DATA")
	private List<RealName.Resp_data> resp_data;

	@JsonProperty("RSLT_CD")
	private String rslt_cd;

	@JsonProperty("RSLT_MSG")
	private String rslt_msg;

	public List<RealName.Resp_data> getResp_data(){
		return this.resp_data;
	}

	public String getRslt_cd() {
		return this.rslt_cd;
	}
	
	public String getRslt_msg() {
		return this.rslt_msg;
	}

	
	public static class Resp_data{
	
		@JsonProperty("ACCT_NM")
		private String acct_nm;
		
		@JsonProperty("TRSC_SEQ_NO")
		private String trsc_seq_no;

		public String getAcct_nm() {
			return this.acct_nm;
		}
		
		public String getTrsc_seq_no() {
			return this.trsc_seq_no;
		}
	}
}