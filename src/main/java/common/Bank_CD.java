package common;

public enum Bank_CD {
	산업은행("002"),
	기업은행("003"),
	국민은행("004"),
	외환은행("005"),
	수협중앙회("007"),
	농협은행("011"),
	지역농ㆍ축협("012"),
	우리은행("020"),
	제일은행("023"),	//	SC은행
	씨티은행("027"),
	대구은행("031"),
	부산은행("032"),
	광주은행("034"),
	제주은행("035"),
	전북은행("037"),
	경남은행("039"),
	새마을금고("045"),
	신협("048"),
	상호저축은행("050"),
	HSBC은행("054"),
	도이치은행("055"),
	제이피모간체이스은행("057"),
	BOA은행("060"),
	비엔피파리바은("061"),
	산림조합중앙회("064"),
	우체국("071"),
	KEB하나은행("081"),
	신한은행("088"),
	K뱅크("089"),
	카카오뱅크("090");
	
	private String cd;
	Bank_CD(String cd){
		this.cd = cd;
	}
	
	public String getCD() {
		return cd;
	}
}
