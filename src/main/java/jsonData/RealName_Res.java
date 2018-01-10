package jsonData;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealName_Res {
	
	@JsonProperty("RESP_DATA")
	private List<RealName_Res.Resp_data> resp_data;

	@JsonProperty("RSLT_CD")
	private String rslt_cd;

	@JsonProperty("RSLT_MSG")
	private String rslt_msg;

	@Getter
	public static class Resp_data{
	
		@JsonProperty("ACCT_NM")
		private String acct_nm;
		
		@JsonProperty("TRSC_SEQ_NO")
		private String trsc_seq_no;

	}
}