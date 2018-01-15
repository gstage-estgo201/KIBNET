package jsonData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.checkpay.util.SecurityUtil;

import common.CommonVariables;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Account_Res {
	@JsonProperty(Key.Constants.VV) private String vv;
	@JsonProperty(Key.Constants.RM) private String rm;
	@JsonProperty(Key.Constants.VM) private String vm;
	@JsonProperty(Key.Constants.TNO) private String tno;
	@JsonProperty(Key.Constants.ID) private String id;
	@JsonProperty(Key.Constants.RS_DTIME) private String rs_dtime;
	@JsonProperty(Key.Constants.EM) private String em;
	@JsonProperty(Key.Constants.RC) private String rc;
	
	private String error;
	private String message;
	private String code;
	
	@JsonProperty(Key.Constants.COMMON_HEAD) 
	public void unpackNestedCommon_Head(Map<String, String> common_head) {
		this.error = common_head.get(Key.Constants.ERROR);
		this.message = common_head.get(Key.Constants.MESSAGE);
		this.code = common_head.get(Key.Constants.CODE);
	}
	
	@JsonProperty("EV")
	public void unpackNestedEncEv(String Ev) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> evMap = new HashMap<>();
		String decEv = null;
		
		try {
			decEv = SecurityUtil.DecryptAesBase64(Ev, CommonVariables.CRYPT_KEY, true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			evMap = mapper.readValue(decEv.substring(14), new TypeReference<Map<String, String>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		unpackNestedDecEv(evMap);
	}
	
	public abstract void unpackNestedDecEv(Map<String, String> ev);
	
	@Getter
	private enum Key {
		VV(Constants.VV, true),
		VM(Constants.VM, true),
		TNO(Constants.TNO, true),
		ID(Constants.ID, true),
		RS_DTIME(Constants.RS_DTIME, true),
		EM(Constants.EM, true),
		RC(Constants.RC, true),
		RM(Constants.RM, true);
		
		private String value;
		private boolean mandatory;
		
		Key(String value, boolean mandatory){
			this.value = value;
			this.mandatory = mandatory;
		}
		
		private static class Constants {
			
			private final static String VV = "VV";
			private final static String VM = "VM";
			private final static String TNO = "TNO";
			private final static String ID = "ID";
			private final static String RS_DTIME = "RS_DTIME";
			private final static String EM = "EM";
			private final static String RC = "RC";
			private final static String RM = "RM";
			
			private final static String COMMON_HEAD = "COMMON_HEAD";
			private final static String ERROR = "ERROR";
			private final static String MESSAGE = "MESSAGE";
			private final static String CODE = "CODE";
		}
		
	}
}
