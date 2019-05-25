package com.studyveloper.overtheflow.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.studyveloper.overtheflow.mapper.MemberLikesMusicMapper;
import com.studyveloper.overtheflow.vo.MemberLikesMusicVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:memberlikesmusic-context.xml"})

public class MemberLikesMusicMapperTest {
	@Autowired
	private MemberLikesMusicMapper memberLikesMusicMapper;
	
	@Before
	public void setUp() throws Exception {
		this.memberLikesMusicMapper.deleteAllMemberLikesMusic();
		
		MemberLikesMusicVO memberLikesMusicVO = new MemberLikesMusicVO();
		
		memberLikesMusicVO.setMemberId("1");
		memberLikesMusicVO.setMusicId("1");
		
		this.memberLikesMusicMapper.addMemberLikesMusic(memberLikesMusicVO);
		
		memberLikesMusicVO.setMemberId("2");
		
		this.memberLikesMusicMapper.addMemberLikesMusic(memberLikesMusicVO);
		
		memberLikesMusicVO.setMemberId("3");
		
		this.memberLikesMusicMapper.addMemberLikesMusic(memberLikesMusicVO);
	}

	
	@Test
	public void addMemberLikesMusicTest() {
		MemberLikesMusicVO memberLikesMusicVO = new MemberLikesMusicVO();
		
		memberLikesMusicVO.setMemberId("4");
		memberLikesMusicVO.setMusicId("1");
		
		int result = this.memberLikesMusicMapper.addMemberLikesMusic(memberLikesMusicVO);
		
		assertThat(result, is(1));
	}
	
	@Test
	public void deleteMemberLikesMusicTest() {
		MemberLikesMusicVO memberLikesMusicVO = new MemberLikesMusicVO();
		
		memberLikesMusicVO.setMemberId("1");
		memberLikesMusicVO.setMusicId("1");
		
		boolean result = this.memberLikesMusicMapper.deleteMemberLikesMusic(memberLikesMusicVO);
		
		assertThat(result, is(true));
	}
}
