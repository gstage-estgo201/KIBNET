package common;

public class CommonVariables {
	/* 계좌검증(1원인증) -KIBNET	 */
	public final static String VERIFY_ACCOUNT_URL = "https://dev.checkpay.co.kr/";	//	개발
//	public final static String VERIFY_ACCOUNT_URL = "https://www.checkpay.co.kr/";	//	운영
	
	public final static String ID = "03420001";
	public final static String CRYPT_KEY = "checkpa58Kid##79**#ekIhkd#1ssi*l";

	public final static String SERVICE_CONTENT_100 = "CPIF_AFFL_100.jct?";	//	은행점검시간
	public final static String SERVICE_CONTENT_720 = "CPIF_AFFL_720.jct?";	//	계좌번호 검증
	public final static String SERVICE_CONTENT_721 = "CPIF_AFFL_721.jct?";	//	계좌번호 검증확인
	
	
	/* 예금주 조회 - coocon */
	public final static String VERIFY_NAME_URL = "http://dev.coocon.co.kr/sol/gateway/acctnm_rcms_wapi.jsp";	//	개발
//	public final static String VERIFY_NAME_URL = "https://gw.coocon.co.kr/sol/gateway/acctnm_rcms_wapi.jsp";	//	운영
	
	public final static String SECR_KEY = "AhCv4embos1U4sDtF0gO";
	public final static String KEY = "ACCTNM_RCMS_WAPI";
	
//	Error Code
	public final static String CODE_000 = "000";		//	정상처리(예금주조회) - coocon
	public final static String CODE_101 = "101";		//	1일 이체가능 건수 초과
	
	public final static String CODE_0000 = "0000";	//	정상처리(계좌인증) - kibnet
	// 등등등
}
