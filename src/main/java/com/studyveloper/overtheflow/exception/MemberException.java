package com.studyveloper.overtheflow.exception;

public class MemberException extends Exception{
	private ErrorCode errorCode;
	
	
	private MemberException(){
		
	}
	public MemberException(String message, ErrorCode errorCode){
		super(message);
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public enum ErrorCode {
		// 오류 코드 목록 (추가 시 ',' 로 추가 후 마지막 리터럴에 ';'를 붙인다.)
		
		// 로그인하지 않고 로그인 전용 서비스를 요청한 경우.
		NOT_LOGIN("NOT_LOGIN"),
		
		// 이미 로그인한 회원이 로그인을 요청한 경우.
		ALREADY_LOGIN("ALREADY_LOGIN"),
		
		//존재하지 않는 정보를 요청한 경우.
		NOT_EXISTS("NOT_EXISTS"),
		
		//중복된 정보를 요청한 경우.
		EXISTS("EXISTS"),
		
		// 기존 비밀번호가 일치하지 않는 경우.
		NOT_EQAUL_PASSWORD("NOT_EQAUL_PASSWORD"),
		
		// 데이터가 유효성을 충족시키지 못한 경우.
		NOT_AVAILABLE("NOT_AVAILABLE"),
		
		// 자신을 팔로우 요청한 경우.
		IMPOSSIBLE_SELF_FOLLOW("IMPOSSIBLE_SELF_FOLLOW"),
		
		// 자신을 팔로우 취소 요청한 경우.
		IMPOSSIBLE_SELF_UNFOLLOW("IMPOSSIBLE_SELF_UNFOLLOW"),
		
		// 파라미터가 NULL인 경우.
		NULLPARAMETER("NULLPARAMETER"),
		;
		
		
		private String errorCode;
		ErrorCode(String errorCode){
			this.errorCode = errorCode;
		}
	}
}
