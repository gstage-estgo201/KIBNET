package services_verify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import common.Bank_CD;
import common.CommonVariables;
import jsonData.RealName_Req;
import jsonData.RealName_Res;
import services_veirfy.VerifyRealName;
import util.Utils;

public class VerifyRealNameTests {
	ObjectMapper mapper;
	
	@Before
	public void before() {
		mapper = new ObjectMapper();
	}
	
	private String[][] dataArray = {
			{Bank_CD.농협은행.getCD(), "14902597746", "", "0"},			//	농협은행
			{Bank_CD.산업은행.getCD(), "02026007408704", "", "0"},		//	산업은행
			{Bank_CD.기업은행.getCD(), "21701322303023", "", "0"},		//	기업은행1
			{Bank_CD.기업은행.getCD(), "01904232902016", "", "0"},		//	기업은행2
			{Bank_CD.외환은행.getCD(), "620199910659", "", "0"},		//	외환은행
			{Bank_CD.수협중앙회.getCD(), "02602123171", "", "0"},		//	수협은행
			{Bank_CD.부산은행.getCD(), "1010000014002", "", "0"},		//	부산은행
			{Bank_CD.경남은행.getCD(), "502210229948", "", "0"},		//	경남은행
//			{Bank_CD.신협.getCD(), "132548099293", "", "0"},		//	신협1 (344 Error : 해당계좌없음)
			{Bank_CD.신협.getCD(), "135000020248", "", "0"},		//	신협2
			{Bank_CD.우체국.getCD(), "01001702000029", "", "0"},		//	우체국
			{Bank_CD.신한은행.getCD(), "100020908927", "", "0"},		//	신한은행
			
//			{Bank_CD.제주은행.getCD(), "3301000013", "", "0"},			//	제주은행	(X42 Error : 해당제휴사 확인요망)
//			{Bank_CD.우리은행.getCD(), "1006502226237", "", "0"},		//	우리은행	(203 Error : 계좌번호오류)
//			{Bank_CD.제일은행.getCD(), "86010014628", "", "0"},			//	제일은행	(247 Error : 기타처리불가)
//			{Bank_CD.씨티은행.getCD(), "3040176126401", "", "0"},		//	씨티은행	(344 Error : 해당계좌없음)
//			{Bank_CD.대구은행.getCD(), "505102678658", "", "0"},		//	대구은행	(344 Error : 해당계좌없음)
//			{Bank_CD.전북은행.getCD(), "501219999997", "", "0"},		//	전북은행1	(X11 Error : 해당제휴사 확인요망)
//			{Bank_CD.전북은행.getCD(), "501220048478", "", "0"},		//	전북은행2	(X11 Error : 해당제휴사 확인요망)
//			{Bank_CD.새마을금고중앙회.getCD(), "9003166420461", "", "0"}	//	새마을금고	(525 Error : 거래불가)
			};
	
	@Test
	public void 정상동작확인_모든은행() {
		for(String[] data : dataArray) {
			RealName_Req jsonData = new RealName_Req.Builder(CommonVariables.SECR_KEY, CommonVariables.KEY)
					.setBank_cd(data[0])
					.setSearch_acct_no(data[1])
					.setAcnm_no(data[2])
					.setIche_amt(data[3])
					.setTrsc_seq_no(Utils.getRandomNum())
					.build();
			
			VerifyRealName verifyRealName = new VerifyRealName();
			String result = verifyRealName.verify(jsonData.getJsonData());
			
			System.out.println("result : " + result.trim());
			
			assertNotNull(result);
			RealName_Res responseData = new RealName_Res();

			try {
				responseData = mapper.readValue(result, RealName_Res.class);
				System.out.println("pretty responseData realName : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseData));
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
						
			assertEquals(CommonVariables.CODE_000, responseData.getRslt_cd());
		}
	}
}
