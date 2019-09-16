package com.studyveloper.overtheflow;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studyveloper.overtheflow.bean.MemberBean;
import com.studyveloper.overtheflow.service.MemberService;
import com.studyveloper.overtheflow.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberTest {

	
	private static final Logger logger = LoggerFactory.getLogger(MemberTest.class);
	@Autowired
	MemberService memberService;
	
	
	/*	
	 * 로그인 테스트
	 * 1. 사용자가 로그인을 요청한다.
	 * 2. 시스템은 사용자에게 이메일과 비밀번호 입력을 요청한다.
	 * 3. 사용자는 이메일과 비밀번호를 입력하고 시스템에 제공한다.
	 * 4. 시스템은 이메일과 일치하는 회원을 검색한 뒤 비밀번호를 비교한다.
	 * 5. 일치한다면 로그인 성공 후 메인페이지로 이동한다.
	 * 
	 * 실패케이스
	 * case - 1. 이메일과 일치하는 회원이 없는 경우 '회원가입이 안된 이메일 입니다.' 메세지를 제공한다.
	 * 
	 * case - 2. 비밀번호가 일치하지 않는 경우 '정확한 비밀번호를 입력해 주세요.' 메세지를 제공한다.
	 * 
	 * case - 3. 이미 로그인한 회원의 경우 '이미 로그인한 회원입니다.' 메세지를 제공한다.
	*/	
	/*@Test
	public void loginTest()throws Exception {
		Map<String, String> session = new HashMap<String, String>();
		String password;
		
		SearchInfo searchInfo = new SearchInfo();
		searchInfo.setConjunction("AND");
		searchInfo.setCurrentPageNumber(1);
		searchInfo.setKeyword("naber9065@naver.com");
		searchInfo.setOrdering(false);
		searchInfo.setPerPageCount(1);
		searchInfo.setSearchOption("EQUAL");
		searchInfo.setSortionOption("EMAIL");
		password = "popo1111";
		
		List<MemberVO> members = memberService.getMembersByEmail(searchInfo);
		if(members.size() == 0){
			logger.info("회원가입이 안된 이메일 입니다.");
		}else if(!members.get(0).getPassword().equals(password)){
			logger.info("정확한 비밀번호를 입력해 주세요.");
		}else if(session.get("loginId") != null){
			logger.info("이미 로그인한 회원입니다.");
		}else{
			session.put("loginId", members.get(0).getId());
			logger.info("로그인 성공!");
		}
	}*/
	
	/*
	 * 로그아웃 테스트
	 * 1. 사용자는 로그아웃을 요청한다.
	 * 2. 시스템은 로그인 되어 있는 회원의 요청인지 확인한다.
	 * 3. 로그인된 회원이라면 로그아웃 시킨다.
	 * 
	 * 실패 케이스
	 * case - 1. 로그인 안된 회원의 경우 '로그인한 회원만 로그아웃할 수 있습니다.' 메세지를 제공한다.
	*/
	/*@Test
	public void logoutTest()throws Exception{
		Map<String, String> session = new HashMap<String, String>();
		session.put("loginId", "");
		if(session.get("loginId") != null){
			logger.info("로그아웃 성공!");
		}else{
			logger.info("로그인한 회원만 로그아웃할 수 있습니다.");
		}
	}*/
	
	/*
	 * 회원가입 테스트
	 * 1. 사용자는 회원가입을 요청한다.
	 * 2. 시스템은 회원가입에 필요한 정보를 사용자에게 요청한다 ( 이메일, 비밀번호, 닉네임, 소개글)
	 * 3. 사용자는 정보를 입력한다.
	 * 4. 시스템은 이메일 중복, 닉네임 중복검사를 한다.
	 * 5. 이메일 형식, 비밀번호(8~16자 이내 영문, 숫자, 특수문자만 사용), 닉네임(2~10자 한글,영문,숫자,특문(-,_)만 사용), 소개글(1000자이내) 유효성을 검사한다.
	 * 6. 유효성검사가 정상적으로 종료되면 회원 식별키를 생성한 후 식별키의 중복 검사를 한다.
	 * 7. 식별키가 중복되지 않았다면 회원가입을 완료한다.
	 * 8. 회원가입 완료후 메인페이지로 이동.
	 * 
	 * 실패 케이스
	 * case - 1. 이메일을 작성하지 않고 요청한 경우 '이메일을 입력해 주세요' 메세지를 제공한다.
	 * case - 2. 비밀번호를 작성하지 않고 요청한 경우 '비밀번호를 입력해 주세요.' 메세지를 제공한다.
	 * case - 3. 닉네임을 작성하지 않고 요청한 경우 '닉네임을 입력해 주세요.' 메세지를 제공한다.
	 * case - 4. 이메일이 중복된 경우 '이미 회원가입 되어있는 이메일입니다.' 메세지를 제공한다.
	 * case - 5. 닉네임이 중복된 경우 '사용중인 닉네임입니다.' 메세지를 제공한다.
	 * case - 6. 이메일 형식에 맞지 않는경우 ' 이메일 아이디 @ 주소 형식을 지켜서 입력해주세요.' 메세지를 제공한다.
	 * case - 7. 비밀번호 유효성에 맞지 않는경우 '비밀번호는 8~16자 이내의 영문, 숫자, 특수문자를 이용해야 합니다.' 메세지를 제공한다.
	 * case - 8. 닉네임의 유효성에 맞지 않는 경우 '닉네임은 2~10자 이내의 한글, 영문, 숫자, 특수문자(-,_)를 이용해야 합니다.' 메세지를 제공한다.
	 * case - 9. 소개글의 유효성에 맞지 않는 경우 '소개글은 1000자 이내로 작성해야 합니다.' 메세지를 제공한다.
	 * case - 10. 이미 생성된 식별키를 생성한 경우 식별키를 재생성 한다.
	*/
	@Test
	public void registTest()throws Exception{
		MemberBean memberBean = new MemberBean();
		memberBean.setEmail("naber9065@naver.com");
		memberBean.setIntroduction("");
		memberBean.setNickname("가_");
		memberBean.setPassword("popo1111");
		memberBean.setRegisterDate(new Date());
		memberBean.setTypeId("1");
		memberBean.setfollowerCount(0);
		memberBean.setfollowingCount(0);
		if(memberBean.getEmail() == null || memberBean.getEmail().trim().equals("")){
			logger.info("아이디를 입력해주세요.");
		}else if(memberBean.getPassword() == null || memberBean.getPassword().trim().equals("")){
			logger.info("비밀번호를 입력해주세요.");
		}else if(memberBean.getNickname() == null || memberBean.getNickname().trim().equals("")){
			logger.info("닉네임을 입력해주세요.");
		}
		else if(!memberBean.getEmail().matches("^[a-zA-Z0-9]+@[a-z]+\\.[a-z]*$")){
			logger.info("이메일 아이디의 형식을 지켜서 입력해주세요.");
		}else if(!memberBean.getPassword().matches("^[a-zA-Z0-9!@#$%^*&()]{8,16}$")){
			logger.info("비밀번호는 8~16자 이내의 영문, 숫자, 특수문자를 이용해야 합니다.");
		}else if(!memberBean.getNickname().matches("^[a-zA-Z0-9가-힣-_]{2,10}$")){
			logger.info("닉네임은 2~10자 이내의 영문, 한글, 숫자, 특수문자(-,_)를 이용해야 합니다.");
		}else if(memberBean.getIntroduction().length() > 1000){
			logger.info("소개글은 1000자 이내로 작성해야합니다.");
		}else{
			MemberVO memberVO = memberService.register(memberBean.toVO());
			if(memberVO != null){
				logger.info("회원가입 성공!");
			}else{
				logger.info("회원가입 실패!");
			}
		}
	}
	
	
}
