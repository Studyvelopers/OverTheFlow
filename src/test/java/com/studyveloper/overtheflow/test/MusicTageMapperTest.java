package com.studyveloper.overtheflow.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studyveloper.overtheflow.mapper.MusicTagMapper;
import com.studyveloper.overtheflow.vo.MusicTagVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:musictagmapper-context.xml"})
public class MusicTageMapperTest {
	@Autowired
	private MusicTagMapper musicTagMapper;
	
	@Before
	public void setUp() throws Exception {
		this.musicTagMapper.deleteAllMusicTag();
		
		MusicTagVO musicTagVO = new MusicTagVO();
		
		musicTagVO.setContents("음악1 태그1");
		musicTagVO.setMusicId("1");
		
		this.musicTagMapper.addMusicTag(musicTagVO);
		
		musicTagVO.setContents("음악1 태그2");
		
		this.musicTagMapper.addMusicTag(musicTagVO);
		
		musicTagVO.setContents("음악1 태그3");
		
		this.musicTagMapper.addMusicTag(musicTagVO);
	}

	@Test
	public void addMusicTagTest() {
		MusicTagVO musicTagVO = new MusicTagVO();
		
		musicTagVO.setContents("음악1 태그4");
		musicTagVO.setMusicId("1");
		
		int result = this.musicTagMapper.addMusicTag(musicTagVO);
		
		assertThat(result, is(1));
		
		musicTagVO.setContents("음악1 태그5");
		musicTagVO.setMusicId("1");
		
		result = this.musicTagMapper.addMusicTag(musicTagVO);
		
		assertThat(result, is(1));
	}
	
	@Test
	public void deleteMusicTagTest(){
		MusicTagVO musicTagVO = new MusicTagVO();
		
		musicTagVO.setContents("음악1 태그1");
		musicTagVO.setMusicId("1");
		
		boolean result = this.musicTagMapper.deleteMusicTag(musicTagVO);
		
		assertThat(result, is(true));
	}
}
