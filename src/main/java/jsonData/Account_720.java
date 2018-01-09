package jsonData;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account_720 {

	public static class Res_ev_720 {
		@JsonProperty("verify_tr_dt")
		private String verify_tr_dt;
		@JsonProperty("verify_tr_tm")
		private String verify_tr_tm;
		@JsonProperty("verify_tr_no")
		private String verify_tr_no;
		@JsonProperty("verify_len")
		private String verify_len;
		@JsonProperty("verify_txt")
		private String verify_txt;
	}
}
