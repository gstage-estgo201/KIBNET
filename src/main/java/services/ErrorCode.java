package services;

public enum ErrorCode {
	CODE_000("정상처리");
	
	private String message;
	ErrorCode(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
