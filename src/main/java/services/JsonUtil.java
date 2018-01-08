package services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.checkpay.util.SecurityUtil;

public class JsonUtil {
	
	public static String getDataFromJsonObject(String response, String key) {
		String value = null;
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject object = (JSONObject)jsonParser.parse(response);
			value = object.get(key).toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return value;
	}
	
	public static String getDecryptDataFromJsonObject(String response, String key, JsonData_Account jsonData) {
		String decData = null;
		String value = null;
		try {
			decData = SecurityUtil.DecryptAesBase64(response, jsonData.get_crypt_key(), true);
			System.out.println("decData : " + decData);
			value = getDataFromJsonObject(decData.substring(14), key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static String getJsonObjectInJsonArray(String response, String key) {
		
		String jsonArrayString = null;
		String result = null;
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(response);
			jsonArrayString = jsonObject.get(key).toString();
			JSONArray jsonArray = (JSONArray)jsonParser.parse(jsonArrayString);
			
			result = jsonArray.get(0).toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
