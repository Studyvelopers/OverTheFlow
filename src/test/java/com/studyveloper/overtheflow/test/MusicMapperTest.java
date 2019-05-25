package com.studyveloper.overtheflow.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		this.musicMapper.deleteAllMusic();
		MusicVO musicVO = new MusicVO();
		musicVO.setId("1");
		musicVO.setTitle("테스트용 타이틀1");
		musicVO.setPlayTime(0);
		musicVO.setDescription("테스트용 타이틀1 내용");
		musicVO.setCategoryId("0");
		musicVO.setMemberId("0");
		
		this.musicMapper.addMusic(musicVO);
		
		musicVO.setId("2");
		musicVO.setTitle("테스트용 타이틀2");
		musicVO.setDescription("테스트용 타이틀2 내용");
		
		this.musicMapper.addMusic(musicVO);
		
		musicVO.setId("3");
		musicVO.setTitle("테스트용 타이틀3");
		musicVO.setDescription("테스트용 타이틀3 내용");
		
		this.musicMapper.addMusic(musicVO);
		
		musicVO.setId("4");
		musicVO.setTitle("테스트용 타이틀4");
		musicVO.setDescription("테스트용 타이틀4 내용");
		
		this.musicMapper.addMusic(musicVO);
		
		musicVO.setId("5");
		musicVO.setTitle("테스트용 타이틀5");
		musicVO.setDescription("테스트용 타이틀5 내용");
		
		this.musicMapper.addMusic(musicVO);
	}

	@Test
	public void addMusicTest() {
		MusicVO musicVO = new MusicVO();
		musicVO.setId("6");
		musicVO.setTitle("테스트용 타이틀6");
		musicVO.setPlayTime(0);
		musicVO.setVisibilityFlag(true);
		musicVO.setDownloadFlag(false);
		musicVO.setDescription("테스트용 타이틀6 내용");
		musicVO.setCategoryId("0");
		musicVO.setMemberId("0");
		
		int result = this.musicMapper.addMusic(musicVO);
		
		assertThat(result, is(1));
	}
	
	@Test
	public void updateMusicTest() {
		MusicVO musicVO = new MusicVO();
		musicVO.setId("1");
		musicVO.setTitle("테스트용 타이틀1 수정");
		musicVO.setPlayTime(100);
		musicVO.setVisibilityFlag(true);
		musicVO.setDownloadFlag(true);
		musicVO.setDescription("테스트용 타이틀1 내용 수정");
		musicVO.setCategoryId("0");
		musicVO.setMemberId("0");
		
		int result = this.musicMapper.modifyMusic(musicVO);
		
		assertThat(result, is(1));
	}
	
	@Test
	public void deleteMusicTest(){
		String musicId = "1";
		
		boolean result = this.musicMapper.deleteMusic(musicId);
		
		assertThat(result, is(true));
	}
	
	@Test
	public void getAllMusicListTest(){
		List<MusicVO> musicList = this.musicMapper.getAllMusicList();
		
		assertThat(musicList.size(), is(5));
		
		for(int i = 0; i < 5; ++i){
			assertThat(musicList.get(i).getId(), is(Integer.toString(i+1)));
		}
	}
	
	@Test
	public void searchMusicByMusicIdTest(){
		MusicVO musicVO = this.musicMapper.searchMusicByMusicId("1");
		
		assertThat(musicVO.getId(), is("1"));
		assertThat(musicVO.getTitle(), is("테스트용 타이틀1"));
	}
	
	@Test
	public void searchMusicByMusicTitleTest(){
		int size = this.musicMapper.maxSearchMusicByMusicTitle("테스트용");
		
		assertThat(size, is(5));
		
		Map<String, Object> searchCondition = new HashMap<String, Object>();
		searchCondition.put("pageNumber", 0);
		searchCondition.put("perPageCount", 10);
		searchCondition.put("title", "테스트용");
		
		List<MusicVO> result = this.musicMapper.searchMusicByMusicTitle(searchCondition);
		
		assertThat(result.size(), is(5));
	}
}
