package com.studyveloper.overtheflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studyveloper.overtheflow.bean.MemberBean;
import com.studyveloper.overtheflow.service.FollowService;
import com.studyveloper.overtheflow.service.MemberService;
import com.studyveloper.overtheflow.util.SearchInfo;
import com.studyveloper.overtheflow.vo.FollowVO;
import com.studyveloper.overtheflow.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberTest.class);
	@Autowired
	MemberService memberService;
	@Autowired
	FollowService followService;
	
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
	/*@Test
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
	}*/
	
	/*
	 * 회원 정보 수정 케이스
	 * 1. 회원은 정보 수정을 요청한다.
	 * 2. 시스템은 회원의 로그인 상태를 확인한다.
	 * 3. 로그인한 회원이라면 수정할 정보입력을 요청한다.(비밀번호, 닉네임, 소개글)
	 * 4. 사용자는 수정할 정보를 입력한다.
	 * 5. 시스템은 닉네임 중복검사를 한다.
	 * 6. 시스템은 비밀번호(8~16자 이내 영문, 숫자, 특수문자만 사용), 닉네임(2~10자 한글,영문,숫자,특문(-,_)만 사용), 소개글(1000자이내) 유효성을 검사한다.
	 * 7. 유효성 검사가 정상적으로 끝났다면 수정을 완료한다.
	 * 8. 수정이 완료된 후 메인페이지로 이동한다.
	 * 
	 * 실패 케이스
	 * case - 1.로그인한 회원이 아닌경우 '로그인을 먼저 해주세요.' 메세지를 제공한다.
	 * case - 2.닉네임이 중복된 경우 '사용중인 닉네임입니다.' 메세지를 제공한다.
	 * case - 3.비밀번호 유효성에 맞지 않는경우 '비밀번호는 8~16자 이내의 영문, 숫자, 특수문자를 이용해야 합니다.' 메세지를 제공한다.
	 * case - 4. 닉네임의 유효성에 맞지 않는 경우 '닉네임은 2~10자 이내의 한글, 영문, 숫자, 특수문자(-,_)를 이용해야 합니다.' 메세지를 제공한다.
	 * case - 5. 소개글의 유효성에 맞지 않는 경우 '소개글은 1000자 이내로 작성해야 합니다.' 메세지를 제공한다.
	 * case - 6. 기존 비밀번호가 일치하지 않는 경우 '비밀번호가 일치하지 않습니다.' 메세지를 제공한다.
	*/
	/*@Test
	public void modifyTest()throws Exception{
		Map<String,String> session = new HashMap<String,String>();
		session.put("loginId","f1c1278e8068b15023dac10b8cdb78e75fee231d");
		String memberId = session.get("loginId");
		
		MemberBean memberBean = new MemberBean();
		memberBean.setId(memberId);
		memberBean.setEmail("naber9065@naver.com");
		memberBean.setIntroduction("");
		memberBean.setNickname("가자");
		memberBean.setPassword("popo11111");
		memberBean.setTypeId("1");
		memberBean.setfollowerCount(0);
		memberBean.setfollowingCount(0);
		String oldPassword = "popo11112";
		
		if(memberId==null || memberId.trim().equals("")){
			logger.info("로그인을 먼저 해주세요.");
		}else if(!memberBean.getPassword().matches("^[a-zA-Z0-9!@#$%^*&()]{8,16}$")){
			logger.info("비밀번호는 8~16자 이내의 영문, 숫자, 특수문자를 이용해야 합니다.");
		}else if(!memberBean.getNickname().matches("^[a-zA-Z0-9가-힣-_]{2,10}$")){
			logger.info("닉네임은 2~10자 이내의 영문, 한글, 숫자, 특수문자(-,_)를 이용해야 합니다.");
		}else if(memberBean.getIntroduction().length() > 1000){
			logger.info("소개글은 1000자 이내로 작성해야합니다.");
		}else{
			memberService.modifyMember(memberBean.toVO(), oldPassword);
		}
	}*/
	
	/*
	 * 회원 정보 조회 테스트
	 * 1. 회원이 자신의 정보 조회를 요청한다.
	 * 2. 시스템은 회원이 로그인상태인지 검사한다.
	 * 3. 시스템은 회원의 정보를 제공한다.(이메일, 닉네임, 소개글, 가입날짜)
	 * 
	 * 실패 케이스
	 * case - 1.로그인한 회원이 아닌경우 '로그인을 먼저 해주세요.' 메세지를 제공한다.
	*/
	/*@Test
	public void memberinfoTest()throws Exception{
		Map<String, String> session = new HashMap<String,String>();
		session.put("loginId", "49d07abfc1397cc6952cd3e429d99ded66de285f");
		String memberId = session.get("loginId");
		if(memberId == null || memberId.trim().equals("")){
			logger.info("로그인을 먼저 해주세요.");
		}else{
			MemberVO memberVO = memberService.getMember(memberId);
			MemberBean member= new MemberBean(memberVO);
			logger.info("정보 조회 성공!");
			logger.info(member.toString());
		}
	}*/
	
	/*
	 * 회원 탈퇴 테스트
	 * 1. 사용자는 회원탈퇴를 요청한다.
	 * 2. 시스템은 사용자가 로그인 상태인지 검사한다.
	 * 3. 시스템은 사용자에게 비밀번호 입력을 요청한다.
	 * 4. 사용자는 비밀번호를 입력한다.
	 * 5. 시스템은 사용자를 탈퇴시키고 정보를 삭제한다.
	 * 6. 탈퇴 완료 후 메인페이지로 이동한다.
	 * 
	 * 실패 케이스
	 * case - 1.로그인한 회원이 아닌경우 '로그인을 먼저 해주세요.' 메세지를 제공한다.
	 * case - 2.비밀번호를 입력하지 않은경우 '비밀번호를 입력해 주세요.' 메세지를 제공한다.
	 * case - 3.비밀번호가 정확하지 않는경우 '비밀번호가 일치하지 않습니다.' 메세지를 제공한다.(메인으로 갈지 재입력받을지)
	*/
	/*@Test
	public void unregistTest()throws Exception{
		Map<String, String> session = new HashMap<String,String>();
		session.put("loginId", "49d07abfc1397cc6952cd3e429d99ded66de285f");
		String memberId = session.get("loginId");
		String password = "popo1111";
		if(memberId == null || memberId.trim().equals("")){
			logger.info("로그인을 먼저 해주세요.");
		}else if(password == null || password.trim().equals("") ){
			logger.info("비밀번호를 입력해 주세요");
		}else{
			MemberVO memberVO = memberService.getMember(memberId);
			if(!memberVO.getPassword().equals(password)){
				logger.info("비밀번호가 일치하지 않습니다.");
			}else{
				boolean result = memberService.unRegister(memberId, password);
				if(result){
					logger.info("탈퇴 성공!");
				}else{
					logger.info("탈퇴 실패!");
				}
			}
		}
	}*/
	
	/*
	 * 회원 검색 테스트
	 * 1. 사용자는 회원 검색을 요청한다.
	 * 2. 시스템은 검색할 키워드 입력을 요청한다.
	 * 3. 사용자는 검색할 키워드와 검색 정보를 입력한다.(키워드, 현재페이지(null이면 1), 페이지당 정보의 개수(null이면 10), 검색 기준(닉네임, 이메일)(null이면 이메일), 정렬 기준(null이면 이메일), 정렬 옵션(null이면 false) , 이중 검색 옵션?(null이면 AND))
	 * 4. 시스템은 검색결과를 사용자에게 제공한다.
	 * 
	 * 실패케이스
	 * case - 1.키워드를 입력하지 않은경우 '키워드를 입력해 주세요.' 메세지를 제공한다.
	*/
	/*@Test
	public void searchMemberTest()throws Exception{
		SearchInfo searchInfo = new SearchInfo();
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		searchInfo.setKeyword("1");
//		searchInfo.setConjunction("AND");
//		searchInfo.setCurrentPageNumber(1);
//		searchInfo.setOrdering(false);
//		searchInfo.setPerPageCount(1);
//		searchInfo.setSearchOption("LIKE");
//		searchInfo.setSortionOption("EMAIL");
		String searchRule = "EMAIL";
		
		if(searchInfo.getKeyword() == null || searchInfo.getKeyword().trim().equals("")){
			logger.info("키워드를 입력해 주세요.");
		}
		if(searchInfo.getConjunction() == null || searchInfo.getConjunction().trim().equals("")){
			searchInfo.setConjunction("AND");
			logger.info("conjunction 기본값 AND로 설정");
		}
		if(searchInfo.getCurrentPageNumber() == null || searchInfo.getCurrentPageNumber() == 0){
			searchInfo.setCurrentPageNumber(1);
			logger.info("currentPageNumber 기본값 1로 설정");
		}
		if(searchInfo.getOrdering() == null){
			searchInfo.setOrdering(false);
			logger.info("ordering 기본값 false로 설정");
		}
		if(searchInfo.getPerPageCount() == null || searchInfo.getPerPageCount() == 0){
			searchInfo.setPerPageCount(10);
			logger.info("perPageCount 기본값 10으로 설정");
		}
		if(searchInfo.getSearchOption() == null || searchInfo.getSearchOption().trim().equals("")){
			searchInfo.setSearchOption("LIKE");
			logger.info("searchOption 기본값 LIKE로 설정");
		}
		if(searchInfo.getSortionOption() == null || searchInfo.getSortionOption().trim().equals("")){
			searchInfo.setSortionOption("EMAIL");
			logger.info("sortingOption 기본값 EMAIL로 설정");
		}
		
		if(searchRule.equals("NICKNAME")){
			
			list = memberService.getMembersByNickName(searchInfo);
			logger.info(list.size() + "명 검색 성공."); 
		}else if(searchRule.equals("EMAIL")){
		
			list = memberService.getMembersByEmail(searchInfo);
			logger.info(list.size() + "명 검색 성공.");
		}
		List<MemberBean> members = new ArrayList<MemberBean>();
		for(int i=0; i<list.size(); i++){
			members.add(new MemberBean(list.get(i)));
		}
	}*/
	
	/*
	 * 팔로우 요청 테스트
	 * 1.회원은 다른 회원 팔로우를 요청한다.
	 * 2.시스템은 회원의 로그인 여부를 확인한다.
	 * 3.시스템은 회원에게 팔로우하려는 회원의 식별키 입력을 요청한다.
	 * 4.회원은 팔로우 하려는 회원의 식별키를 입력한다.
	 * 5.시스템은 팔로우를 완료한다.
	 * 
	 * 실패 케이스
	 * case - 1.로그인한 회원이 아닌경우 '로그인을 먼저 해주세요.' 메세지를 제공한다. 
	 * case - 2.존재하지 않는 회원의 식별키를 입력한 경우 '존재하지 않는 회원입니다.' 메세지를 제공한다.
	 * case - 3.이미 팔로우하고 있는 회원의 식별키를 입력한 경우 '이미 팔로우중 입니다.' 메세지를 제공한다.
	 * case - 4.자신을 팔로우 하려는 경우 '자기자신은 팔로우할 수 없습니다.' 메세지를 제공한다.
	*/
	/*@Test
	public void followTest()throws Exception{
		Map<String, String> session = new HashMap<String, String>();
		session.put("loginId", "10");
		String loginId = session.get("loginId");
		String followId = "5";
		
		FollowVO followVO = new FollowVO();
		followVO.setFollowingId(followId);
		followVO.setFollowerId(loginId);
		if(loginId == null){
			logger.info("로그인을 먼저 해주세요.");
		}else if(loginId.equals(followId)){
			logger.info("자기 자신은 팔로우할 수 없습니다.");
		}else if(memberService.getMember(followId) == null){
			logger.info("존재하지 않는 회원입니다.");
		}else{
			followService.follow(followVO);
		}
	}*/
	
	/*
	 * 팔로우 취소 테스트 
	 * 1.회원은 팔로우 취소를 요청한다.
	 * 2.시스템은 팔로우 취소하려는 회원의 식별키 입력을 요청한다.
	 * 3.회원은 팔로우 취소하려는 회원의 식별키를 입력한다.
	 * 4.시스템은 팔로우를 취소한다.
	 * 
	 * 실패 케이스
	 * case - 1.로그인한 회원이 아닌경우 '로그인을 먼저 해주세요.' 메세지를 제공한다.
	 * case - 2.존재하지 않는 회원의 식별키를 입력한 경우 '존재하지 않는 회원입니다.' 메세지를 제공한다.
	 * case - 3.팔로우하지 않고 있는 회원의 식별키를 입력한 경우 '이미 팔로우중 입니다.' 메세지를 제공한다.
	 * case - 4.자신을 팔로우 취소하려는 경우 '자기자신은 팔로우 취소할 수 없습니다.' 메세지를 제공한다.
	*/
	/*@Test
	public void unFollowTest()throws Exception{
		Map<String, String> session = new HashMap<String, String>();
		session.put("loginId", "1");
		String loginId = session.get("loginId");
		String followId = "2";
		
		FollowVO followVO = new FollowVO();
		followVO.setFollowingId(followId);
		followVO.setFollowerId(loginId);
		if(loginId == null || loginId.trim().equals("")){
			logger.info("로그인을 먼저 해주세요.");
		}else if(loginId.equals(followId)){
			logger.info("자기 자신은 팔로우 취소할 수 없습니다.");
		}else if(memberService.getMember(followId) == null){
			logger.info("존재하지 않는 회원입니다.");
		}else{
			followService.unFollow(followVO);
			logger.info("팔로우 취소 성공했습니다.");
		}
	}*/

	/*
	 * 팔로워 조회 테스트
	 * 1.회원은 팔로워 조회를 요청한다.
	 * 2.시스템은 조회하려는 회원의 식별키 입력을 요청한다.
	 * 3.회원은 팔로워를 조회하기 위한 식별키를 입력한다.
	 * 4.시스템은 팔로워 목록을 제공한다.
	 * 
	 * 실패 케이스
	 * case - 1.존재하지 않는 회원의 식별키를 입력한 경우 '존재하지 않는 회원입니다.' 메세지를 제공한다.	
	*/
	/*@Test
	public void searchFollower()throws Exception{
		Map<String, String> session = new HashMap<String, String>();
		session.put("loginId", "1");
		String loginId = session.get("loginId");
		
		SearchInfo searchInfo = new SearchInfo();
		searchInfo.setKeyword(loginId);
//		searchInfo.setConjunction("AND");
//		searchInfo.setCurrentPageNumber(1);
//		searchInfo.setOrdering(false);
//		searchInfo.setPerPageCount(1);
//		searchInfo.setSearchOption("LIKE");
//		searchInfo.setSortionOption("EMAIL");
		String searchRule = "EMAIL";
		
		if(searchInfo.getKeyword() == null || searchInfo.getKeyword().trim().equals("")){
			logger.info("키워드를 입력해 주세요.");
		}
		if(searchInfo.getConjunction() == null || searchInfo.getConjunction().trim().equals("")){
			searchInfo.setConjunction("AND");
			logger.info("conjunction 기본값 AND로 설정");
		}
		if(searchInfo.getCurrentPageNumber() == null || searchInfo.getCurrentPageNumber() == 0){
			searchInfo.setCurrentPageNumber(1);
			logger.info("currentPageNumber 기본값 1로 설정");
		}
		if(searchInfo.getOrdering() == null){
			searchInfo.setOrdering(false);
			logger.info("ordering 기본값 false로 설정");
		}
		if(searchInfo.getPerPageCount() == null || searchInfo.getPerPageCount() == 0){
			searchInfo.setPerPageCount(10);
			logger.info("perPageCount 기본값 10으로 설정");
		}
		if(searchInfo.getSearchOption() == null || searchInfo.getSearchOption().trim().equals("")){
			searchInfo.setSearchOption("LIKE");
			logger.info("searchOption 기본값 LIKE로 설정");
		}
		if(searchInfo.getSortionOption() == null || searchInfo.getSortionOption().trim().equals("")){
			searchInfo.setSortionOption("EMAIL");
			logger.info("sortingOption 기본값 EMAIL로 설정");
		}
		
		List<MemberVO> loginFollowVOs = followService.getFollows(searchInfo);
		Map<String, MemberVO> map = new HashMap<String, MemberVO>();
		for(int i=0; i<loginFollowVOs.size(); i++){
			map.put(loginFollowVOs.get(i).getId(), loginFollowVOs.get(i));
		}

		searchInfo.setKeyword("5");
		List<MemberVO> followerVOs = followService.getFollowers(searchInfo);
		List<MemberBean> members = new ArrayList<MemberBean>();
		MemberBean memberBean = null;
		
		for(int i=0; i<followerVOs.size(); i++){
			memberBean = new MemberBean(followerVOs.get(i));
			if(map.containsKey(followerVOs.get(i).getId())){
				logger.info(followerVOs.get(i).getId()+"팔로우 함.");
				memberBean.setFollow(true);
			}else{
				logger.info(followerVOs.get(i).getId()+"팔로우 안함.");
				memberBean.setFollow(false);
			}
			members.add(memberBean);
		}
		for(int i=0; i<members.size(); i++){
			logger.info(members.get(i).toString());
		}
	}*/
	
	/*
	 * 팔로우 조회 테스트
	 * 1.회원은 팔로우한 회원 조회를 요청한다.
	 * 2.시스템은 조회하려는 회원의 식별키 입력을 요청한다.
	 * 3.회원은 팔로우를 조회하기 위한 식별키를 입력한다.
	 * 4.시스템은 팔로우 목록을 제공한다.
	 * 
	 * 실패 케이스
	 * case - 1.존재하지 않는 회원의 식별키를 입력한 경우 '존재하지 않는 회원입니다.' 메세지를 제공한다.	
	*/
	/*@Test
	public void searchFollow()throws Exception{
		Map<String, String> session = new HashMap<String, String>();
		session.put("loginId", "16");
		String loginId = session.get("loginId");
		
		SearchInfo searchInfo = new SearchInfo();
		searchInfo.setKeyword(loginId);
//		searchInfo.setConjunction("AND");
//		searchInfo.setCurrentPageNumber(1);
//		searchInfo.setOrdering(false);
//		searchInfo.setPerPageCount(1);
//		searchInfo.setSearchOption("LIKE");
//		searchInfo.setSortionOption("EMAIL");
		String searchRule = "EMAIL";
		
		if(searchInfo.getKeyword() == null || searchInfo.getKeyword().trim().equals("")){
			logger.info("키워드를 입력해 주세요.");
		}
		if(searchInfo.getConjunction() == null || searchInfo.getConjunction().trim().equals("")){
			searchInfo.setConjunction("AND");
			logger.info("conjunction 기본값 AND로 설정");
		}
		if(searchInfo.getCurrentPageNumber() == null || searchInfo.getCurrentPageNumber() == 0){
			searchInfo.setCurrentPageNumber(1);
			logger.info("currentPageNumber 기본값 1로 설정");
		}
		if(searchInfo.getOrdering() == null){
			searchInfo.setOrdering(false);
			logger.info("ordering 기본값 false로 설정");
		}
		if(searchInfo.getPerPageCount() == null || searchInfo.getPerPageCount() == 0){
			searchInfo.setPerPageCount(10);
			logger.info("perPageCount 기본값 10으로 설정");
		}
		if(searchInfo.getSearchOption() == null || searchInfo.getSearchOption().trim().equals("")){
			searchInfo.setSearchOption("LIKE");
			logger.info("searchOption 기본값 LIKE로 설정");
		}
		if(searchInfo.getSortionOption() == null || searchInfo.getSortionOption().trim().equals("")){
			searchInfo.setSortionOption("EMAIL");
			logger.info("sortingOption 기본값 EMAIL로 설정");
		}
		
		List<MemberVO> loginFollowVOs = followService.getFollows(searchInfo);
		Map<String, MemberVO> map = new HashMap<String, MemberVO>();
		for(int i=0; i<loginFollowVOs.size(); i++){
			map.put(loginFollowVOs.get(i).getId(), loginFollowVOs.get(i));
		}

		searchInfo.setKeyword("1");
		List<MemberVO> followVOs = followService.getFollows(searchInfo);
		List<MemberBean> members = new ArrayList<MemberBean>();
		MemberBean memberBean = null;
		
		for(int i=0; i<followVOs.size(); i++){
			memberBean = new MemberBean(followVOs.get(i));
			if(map.containsKey(followVOs.get(i).getId())){
				logger.info(followVOs.get(i).getId()+"팔로우 함.");
				memberBean.setFollow(true);
			}else{
				logger.info(followVOs.get(i).getId()+"팔로우 안함.");
				memberBean.setFollow(false);
			}
			members.add(memberBean);
		}
		for(int i=0; i<members.size(); i++){
			logger.info(members.get(i).toString());
		}
	}*/
}
