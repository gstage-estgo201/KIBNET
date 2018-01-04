package services;

import org.junit.Test;

public class VerifyRealNameTests {
	
	@Test
	public void 정상동작_확인() {
		VerifyRealName verifyRealName = new VerifyRealName();
		verifyRealName.verify();
		

	}
	
	
}
