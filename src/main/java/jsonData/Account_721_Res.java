package jsonData;

import java.util.Map;

import lombok.Getter;

@Getter
public class Account_721_Res extends Account_Res {

	private String verify_tr_dt;
	private String verify_tr_no;
	private String verify_val;
	
	@Override
	public void unpackNestedEv(Map<String, String> ev) {
		this.verify_tr_dt = ev.get("verify_tr_dt");
		this.verify_tr_no = ev.get("verify_tr_no");
		this.verify_val = ev.get("verify_val");
	}
	
	@Getter
	private enum Key {
		verify_tr_dt(Constants.verify_tr_dt, true),
		verify_tr_no(Constants.verify_tr_no, true),
		verify_val(Constants.verify_val, true);
		
		private String value;
		private boolean mandatory;
		
		Key(String value, boolean mandatory) {
			this.value = value;
			this.mandatory = mandatory;
		}
		
		private static class Constants {
			private final static String verify_tr_dt = "verify_tr_dt";
			private final static String verify_tr_no = "verify_tr_no";
			private final static String verify_val = "verify_val";
		}
	}
}
