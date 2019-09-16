package com.studyveloper.overtheflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studyveloper.overtheflow.service.MemberService;
import com.studyveloper.overtheflow.util.SearchInfo;
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
	@Test
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
	}
	
	
}
