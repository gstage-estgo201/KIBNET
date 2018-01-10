package jsonData;

import java.util.Map;

import lombok.Getter;

@Getter
public class Account_100_Res extends Account_Res {
	
	private String fnni_cd;
	private String svc_stop_sdtime;
	private String current_dtime;
	private String svc_stop_edtime;
	
	@Override
	public void unpackNestedEv(Map<String, String> ev) {
		this.fnni_cd = ev.get(Key.Constants.fnni_cd);
		this.svc_stop_sdtime = ev.get(Key.Constants.svc_stop_sdtime);
		this.current_dtime = ev.get(Key.Constants.current_dtime);
		this.svc_stop_edtime = ev.get(Key.Constants.svc_stop_edtime);
	}	
	
	@Getter
	private enum Key {
		fnni_cd(Constants.fnni_cd, true),
		svc_stop_sdtime(Constants.svc_stop_sdtime, true),
		current_dtime(Constants.current_dtime, true),
		svc_stop_edtime(Constants.svc_stop_edtime, true);
		
		private String value;
		private boolean mandatory;
		
		Key(String value, boolean mandatory) {
			this.value = value;
			this.mandatory = mandatory;
		}
		
		private static class Constants {
			private final static String fnni_cd = "fnni_cd";
			private final static String svc_stop_sdtime = "svc_stop_sdtime";
			private final static String current_dtime = "current_dtime";
			private final static String svc_stop_edtime = "svc_stop_edtime";
		}
	}
}
