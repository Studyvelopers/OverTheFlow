package com.studyveloper.overtheflow.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studyveloper.overtheflow.mapper.MusicMapper;
import com.studyveloper.overtheflow.vo.MusicVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:musicmapper-context.xml"})
public class MusicMapperTest {
	@Autowired
	private MusicMapper musicMapper;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addMusicTest() {
		MusicVO musicVO = new MusicVO();
		String id = Integer.toString(musicVO.hashCode());
		musicVO.setId(id);
		musicVO.setTitle("테스트용 타이틀1");
		musicVO.setPlayTime(0);
		musicVO.setVisibilityFlag(true);
		musicVO.setDownloadFlag(false);
		musicVO.setDescription("");
		musicVO.setCategoryId("0");
		musicVO.setMemberId("0");
		this.musicMapper.addMusic(musicVO);
	}

}
