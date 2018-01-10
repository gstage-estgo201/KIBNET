package util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.checkpay.util.SecurityUtil;

public class Utils {
	
	private static Set<String> randomNumSet = new HashSet<String>();
	
	public static String getRandomNum() {
		Random random = new Random();
		int number = random.nextInt(1000000) + 100000;
		if(number > 1000000) {
			number = number - 100000;
		}
		
		String numberString = "0" + String.valueOf(number);
		if(randomNumSet.contains(numberString)) {
			return getRandomNum();
		}else {
			randomNumSet.add(numberString);
			return numberString;
		}
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
