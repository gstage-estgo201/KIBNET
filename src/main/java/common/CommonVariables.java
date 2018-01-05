package common;

public class CommonVariables {
	// 계좌 검증(1원 인증) 서비스 Url
	public final static String ACCOUNT_VALIDATION_URL_DEV = "https://dev.checkpay.co.kr/";
	public final static String ACCOUNT_VALIDATION_URL_REAL = "https://www.checkpay.co.kr/";

	public final static String SERVICE_CONTENT_100 = "CPIF_AFFL_100.jct?";
	public final static String SERVICE_CONTENT_710 = "CPIF_AFFL_710.jct?";
	public final static String SERVICE_CONTENT_720 = "CPIF_AFFL_720.jct?";
	public final static String SERVICE_CONTENT_721 = "CPIF_AFFL_721.jct?";
	
	// 성명 조회
	public final static String VERIFY_NAME_URL = "http://dev.coocon.co.kr/sol/gateway/acctnm_rcms_wapi.jsp";
//	public final static String VERIFY_NAME_URL = "https://gw.coocon.co.kr/sol/gateway/acctnm_rcms_wapi.jsp";
	
	public final static String SECR_KEY = "AhCv4embos1U4sDtF0gO";
	public final static String KEY = "ACCTNM_RCMS_WAPI";
}
