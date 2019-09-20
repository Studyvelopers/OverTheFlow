package com.studyveloper.overtheflow.exception;

public class MemberException extends Exception{
	public enum ErrorCode {
		// 오류 코드 목록 (추가 시 ',' 로 추가 후 마지막 리터럴에 ';'를 붙인다.)
		
		// 로그인하지 않고 로그인 전용 서비스를 요청한 경우.
		NOT_LOGIN("NOT_LOGIN"),
		
		// 로그인을 요청한 이메일이 존재하지 않는경우
		NOT_EXISTS_EMAIL("NOT_EXISTS_EMAIL"),
		
		// 존재하지 않는 회원의 식별키를 입력한 경우.
		NOT_EXISTS_KEY("NOT_EXISTS_KEY"),
		
		// 팔로우하고 있지 않은 회원을 팔로우 취소 요청한 경우.
		NOT_EXISTS_FOLLOW("NOT_EXISTS_FOLLOW"),
		
		// 이미 팔로우하고 있는 회원을 팔로우 요청한 경우.
		EXISTS_FOLLOW("EXISTS_FOLLOW"),
		
		// 회원가입 요청시 이미 가입되어 있는 이메일 주소를 입력한 경우.
		EXISTS_EMAIL("EXISTS_EMAIL"),

		// 이미생성된 식별키를 생성한 경우.
		EXISTS_KEY("EXISTS_KEY"),
		
		// 회원가입 요청시 이미 가입되어 있는 닉네임을 입력한 경우.
		EXISTS_NICKNAME("EXISTS_NICKNAME"),
		
		// 기존 비밀번호가 일치하지 않는 경우.
		NOT_EQAUL_PASSWORD("NOT_EQAUL_PASSWORD"),
		
		// 이미 로그인한 회원이 로그인을 요청한 경우.
		ALREADY_LOGIN("ALREADY_LOGIN"),
		
		// 회원가입 요청시 이메일을 작성하지 않고 요청한 경우.
		NULLPARAMETER_EMAIL("NULLPARAMETER_EMAIL"),
		
		// 회원가입 요청시 비밀번호를 작성하지 않고 요청한 경우.
		NULLPARAMETER_PASSWORD("NULLPARAMETER_PASSWORD"),
		
		// 회원가입 요청시 닉네임을 작성하지 않고 요청한 경우.
		NULPARAMETER_NICKNAME("NULPARAMETER_NICKNAME"),
		
		// 회원가입 요청시 이메일 형식에 맞지 않는 경우.
		NOT_AVAILABLE_EMAIL("NOT_AVAILABLE_EMAIL"),
		
		// 회원가입 요청시 비밀번호 유효성에 맞지 않는 경우.
		NOT_AVAILABLE_PASSWORD("NOT_AVAILABLE_PASSWORD"),
		
		// 회원가입 요청시 닉네임의 유효성에 맞지 않는 경우.
		NOT_AVAILABLE_NICKNAME("NOT_AVAILABLE_NICKNAME"),
		
		// 회원가입 요청시 소개글의 유효성에 맞지 않는 경우.
		NOT_AVAILABLE_INTRODUCTION("NOT_AVAILABLE_INTRODUCTION"),
		
		// 회원 검색 요청시 키워드를 입력하지 않은 경우.
		NULLPARAMETER_KEYWORD("NULLPARAMETER_KEYWORD"),
		
		// 자신을 팔로우 요청한 경우.
		IMPOSSIBLE_SELF_FOLLOW("IMPOSSIBLE_SELF_FOLLOW"),
		
		// 자신을 팔로우 취소 요청한 경우.
		IMPOSSIBLE_SELF_UNFOLLOW("IMPOSSIBLE_SELF_UNFOLLOW"),
		;
		
		private String errorCode;
		ErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		public String getErrorCode() {
			return errorCode;
		}
	}
}
