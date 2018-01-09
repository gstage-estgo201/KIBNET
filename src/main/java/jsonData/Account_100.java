package jsonData;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account_100 {
	@JsonProperty("RESP_DATA")
	private List<Account_100.Res_ev_100> resp_data;
	
	public static class Res_ev_100 {
		@JsonProperty("fnni_cd")
		private String fnni_cd;
		@JsonProperty("current_dtime")
		private String current_dtime;
		@JsonProperty("svc_stop_sdtime")
		private String svc_stop_sdtime;
		@JsonProperty("svc_stop_edtime")
		private String svc_stop_edtime;
	}
	
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
