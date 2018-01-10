package util;

import com.checkpay.util.SecurityUtil;

import jsonData.Account_Req;

public class Utils {
	
	public static String getDecryptData(String response, Account_Req jsonData) {
		String decData = null;
		String value = null;
		try {
			decData = SecurityUtil.DecryptAesBase64(response, jsonData.get_crypt_key(), true);
			System.out.println("decData : " + decData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static String getDecryptData(String response, String key) {
		String decData = null;
		String value = null;
		try {
			decData = SecurityUtil.DecryptAesBase64(response, key, true);
			System.out.println("decData : " + decData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
