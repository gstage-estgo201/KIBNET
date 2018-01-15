package jsonData;

import java.util.Map;

import lombok.Getter;

@Getter
public class Account_720_Res extends Account_Res {
	
	private String verify_tr_dt;
	private String verify_tr_tm;
	private String verify_tr_no;
	private String verify_len;
	private String verify_txt;
	
	@Override
	public void unpackNestedDecEv(Map<String, String> ev) {
		this.verify_tr_dt = ev.get("verify_tr_dt");
		this.verify_tr_tm = ev.get("verify_tr_tm");
		this.verify_tr_no = ev.get("verify_tr_no");
		this.verify_len = ev.get("verify_len");
		this.verify_txt = ev.get("verify_txt");
	}
	
	@Getter
	private enum Key {
		verify_tr_dt(Constants.verify_tr_dt, true),
		verify_tr_tm(Constants.verify_tr_tm, true),
		verify_tr_no(Constants.verify_tr_no, true),
		verify_len(Constants.verify_len, false),
		verify_txt(Constants.verify_txt, false);
		
		private String value;
		private boolean mandatory;
		
		Key(String value, boolean mandatory) {
			this.value = value;
			this.mandatory = mandatory;
		}
		
		private static class Constants {
			private final static String verify_tr_dt = "verify_tr_dt";
			private final static String verify_tr_tm = "verify_tr_tm";
			private final static String verify_tr_no = "verify_tr_no";
			private final static String verify_len = "verify_len";
			private final static String verify_txt = "verify_txt";
		}
	}
}
