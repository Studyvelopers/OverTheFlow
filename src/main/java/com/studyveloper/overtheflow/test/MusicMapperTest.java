package com.studyveloper.overtheflow.test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studyveloper.overtheflow.mapper.MusicMapper;
import com.studyveloper.overtheflow.util.option.MusicUnit;
import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.vo.MusicVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:C:/studyveloper/overtheflow/OverTheFlowWorkSpace/OverTheFlow/src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MusicMapperTest {
	@Autowired
	private MusicMapper musicMapper;
	
	//CannotGetJdbcConnectionException => db에 접속 불가할때 발생하는 익셉션
	
	@Before
	public void setUp() throws Exception {
		OptionIntent.Builder builder = new OptionIntent.Builder();
		this.musicMapper.deleteMusics(builder.build());
		
		MusicVO musicVO = new MusicVO();
		musicVO.setCategoryId("0");
		musicVO.setDescription("뮤직 테스트 케이스1");
		musicVO.setDownloadable(true);
		musicVO.setId("TESTID0");
		musicVO.setMemberId("0");
		musicVO.setMemberNickname("nickname0");
		musicVO.setPlayCount(0);
		musicVO.setPlaytime(100);
		musicVO.setRegisterDate(new Date());
		
		List<String> tags = new ArrayList<String>();
		tags.add("태그1");tags.add("태그2");tags.add("태그3");
		
		musicVO.setTags(tags);
		musicVO.setTitle("테스트케이스1");
		musicVO.setVisibility(true);
		
		this.musicMapper.addMusic(musicVO);
	}

	@After
	public void tearDown() throws Exception {
		OptionIntent.Builder builder = new OptionIntent.Builder();
		this.musicMapper.deleteMusics(builder.build());
	}
	
	//케이스1. 성공적으로 추가가 완료될 경우
	@Test
	public void musicAddTest() {
		MusicVO musicVO = new MusicVO();
		
		musicVO.setCategoryId("0");
		musicVO.setDescription("뮤직 테스트 케이스1");
		musicVO.setDownloadable(true);
		musicVO.setId("TESTID10");
		musicVO.setMemberId("0");
		musicVO.setMemberNickname("nickname0");
		musicVO.setPlayCount(0);
		musicVO.setPlaytime(100);
		musicVO.setRegisterDate(new Date());
		
		List<String> tags = new ArrayList<String>();
		tags.add("태그1");tags.add("태그2");tags.add("태그3");
		
		musicVO.setTags(tags);
		musicVO.setTitle("테스트케이스1");
		musicVO.setVisibility(true);
		
		int result = -1;
		try {
			result = this.musicMapper.addMusic(musicVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertThat(result, is(1));
	}
	
	//익셉션 케이스1-1. 중복 키값이 들어갈 경우
	@Test(expected=DuplicateKeyException.class)
	public void musicAddDuplicateKeyTest() throws Exception {
		MusicVO musicVO = new MusicVO();
	
			musicVO.setCategoryId("0");
			musicVO.setDescription("뮤직 테스트 케이스1");
			musicVO.setDownloadable(true);
			musicVO.setId("TESTID0");
			musicVO.setMemberId("0");
			musicVO.setMemberNickname("nickname0");
			musicVO.setPlayCount(0);
			musicVO.setPlaytime(100);
			musicVO.setRegisterDate(new Date());
			
			List<String> tags = new ArrayList<String>();
			tags.add("태그1");tags.add("태그2");tags.add("태그3");
			
			musicVO.setTags(tags);
			musicVO.setTitle("테스트케이스1");
			musicVO.setVisibility(true);
			
			this.musicMapper.addMusic(musicVO);
	}
	

	//DuplicateKeyException이 DataIntegrityViolationException을 상속받음
	//익셉션 케이스1-2. 필요한 데이터들이 다 제공되지 않았을 경우
	@Test(expected=DataIntegrityViolationException.class)
	public void musicAddUnsatisfiedDataTest() throws Exception{
		MusicVO musicVO = new MusicVO();
		
		musicVO.setCategoryId("0");
		musicVO.setDescription("뮤직 테스트 케이스1");
		musicVO.setDownloadable(true);
		musicVO.setId("TESTID10");
		//musicVO.setMemberId("0");
		musicVO.setMemberNickname("nickname0");
		musicVO.setPlayCount(0);
		musicVO.setPlaytime(100);
		//날짜는 default값이 걸려있어서 상관없음
		//musicVO.setRegisterDate(new Date());
		
		List<String> tags = new ArrayList<String>();
		tags.add("태그1");tags.add("태그2");tags.add("태그3");
		
		musicVO.setTags(tags);
		musicVO.setTitle("테스트케이스1");
		musicVO.setVisibility(true);
		
		this.musicMapper.addMusic(musicVO);
	}
	
	//따로 음수처리에대한 예외처리 사항이 존재하지 않는다
	//익셉션 케이스1-3. 음수값을 허용하지 않는 변수에 음수값이 들어갈 경우
	@Test
	public void musidAddUnmatchedValueTest(){
		MusicVO musicVO = new MusicVO();
		
		musicVO.setCategoryId("0");
		musicVO.setDescription("뮤직 테스트 케이스1");
		musicVO.setDownloadable(true);
		musicVO.setId("TESTID10");
		musicVO.setMemberId("0");
		musicVO.setMemberNickname("nickname0");
		musicVO.setPlayCount(-100);
		musicVO.setPlaytime(-100);
		musicVO.setRegisterDate(new Date());
		
		List<String> tags = new ArrayList<String>();
		tags.add("태그1");tags.add("태그2");tags.add("태그3");
		
		musicVO.setTags(tags);
		musicVO.setTitle("테스트케이스1");
		musicVO.setVisibility(true);
		
		try {
			this.musicMapper.addMusic(musicVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//현재 registerDate 수정 불가능
	//케이스2. 정상적으로 음악 정보가 수정되는 경우
	@Test
	public void modifyMusicTest() {
		MusicVO musicVO = new MusicVO();
		musicVO.setCategoryId("0");
		musicVO.setTitle("뮤직 테스트 케이스1 수정 제목");
		musicVO.setDescription("뮤직 테스트 케이스1 수정");
		musicVO.setDownloadable(true);
		musicVO.setId("TESTID0");
		musicVO.setMemberId("0");
		musicVO.setMemberNickname("nickname0");
		musicVO.setPlayCount(10000);
		musicVO.setPlaytime(100000);
		musicVO.setVisibility(true);
		
		Date now = new Date();
		
		musicVO.setRegisterDate(now);
		
		
		MusicVO result = null;
		
		try {
			this.musicMapper.modifyMusic(musicVO);
			result = this.musicMapper.searchMusic(musicVO.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(now);
		
		assertThat(result.getCategoryId(), is(musicVO.getCategoryId()));
		assertThat(result.getDescription(), is(musicVO.getDescription()));
		assertThat(result.getDownloadable(), is(musicVO.getDownloadable()));
		assertThat(result.getMemberId(), is(musicVO.getMemberId()));
		assertThat(result.getMemberNickname(), is(musicVO.getMemberNickname()));
		assertThat(result.getPlayCount(), is(musicVO.getPlayCount()));
		assertThat(result.getPlaytime(), is(musicVO.getPlaytime()));
		//assertThat(result.getRegisterDate(), is(now));
	}
	
	//현재 매퍼에서는 회원 아이디의 체크나 닉네임 체크등이 이루어지지 않음 => 서비스 로직 단계에서 체크해야 할것으로 보임
	//익셉션 케이스 2-1. 등록자가 아닌 회원이 수정을 요청할 경우
	@Test
	public void modifyMusicDifferentMemberTest(){
		MusicVO musicVO = new MusicVO();
		musicVO.setCategoryId("0");
		musicVO.setTitle("뮤직 테스트 케이스1 수정 제목");
		musicVO.setDescription("뮤직 테스트 케이스1 수정");
		musicVO.setDownloadable(true);
		musicVO.setId("TESTID0");
		musicVO.setMemberId("1");
		musicVO.setMemberNickname("nickname1");
		musicVO.setPlayCount(10000);
		musicVO.setPlaytime(100000);
		musicVO.setVisibility(true);
		
		Date now = new Date();
		
		musicVO.setRegisterDate(now);
		
		
		MusicVO result = null;
		
		try {
			this.musicMapper.modifyMusic(musicVO);
			result = this.musicMapper.searchMusic(musicVO.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(now);
		
		assertThat(result.getCategoryId(), is(musicVO.getCategoryId()));
		assertThat(result.getDescription(), is(musicVO.getDescription()));
		assertThat(result.getDownloadable(), is(musicVO.getDownloadable()));
		assertThat(result.getMemberId(), is(musicVO.getMemberId()));
		assertThat(result.getMemberNickname(), is(musicVO.getMemberNickname()));
		assertThat(result.getPlayCount(), is(musicVO.getPlayCount()));
		assertThat(result.getPlaytime(), is(musicVO.getPlaytime()));
	}
	
	//케이스3. 정상적으로 음악정보를 삭제할 경우
	@Test
	public void deleteMusicTest() {
		try {
			this.musicMapper.deleteMusic("TESTID0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MusicVO result = new MusicVO();
		
		try {
			result = this.musicMapper.searchMusic("TESTID0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertThat(result , nullValue());
	}
	
	//케이스4. 정상적으로 음악정보를 조회할 경우
	@Test
	public void searchMusicTest() {
		OptionIntent.Builder builder = new OptionIntent.Builder();
		
		OptionIntent optionIntent = builder
				.appendEqualSearchOption(MusicUnit.ID, "TESTID0", true)
				.build();
		
		List<MusicVO> result = null;
		
		try {
			result = this.musicMapper.searchMusics(optionIntent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertThat(result.size(), is(1));
	}
}
