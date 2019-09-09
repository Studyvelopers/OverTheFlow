package com.studyveloper.overtheflow.test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studyveloper.overtheflow.mapper.MusicMapper;
import com.studyveloper.overtheflow.util.option.OptionIntent;
import com.studyveloper.overtheflow.vo.MusicVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:C:/studyveloper/overtheflow/OverTheFlowWorkSpace/OverTheFlow/src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MusicMapperTest {
	@Autowired
	private MusicMapper musicMapper;
	
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
	public void musicAddTest() {
		MusicVO musicVO = new MusicVO();
		
		musicVO.setCategoryId("10");
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
		
		int result = -1;
		try {
			result = this.musicMapper.addMusic(musicVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertThat(result, is(1));
	}
	
	//익셉션 케이스1. 중복 키값이 들어갈 경우
	@Test(expected=DuplicateKeyException.class)
	public void musicAddExceptionTest() throws Exception {
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
	

	
	//케이스2. 
	@Test
	public void UnmatchedNicknameAddTest(){
		
	}
}
