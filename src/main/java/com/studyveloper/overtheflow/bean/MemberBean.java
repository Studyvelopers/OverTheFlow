package com.studyveloper.overtheflow.bean;

import java.util.Date;

import com.studyveloper.overtheflow.vo.MemberVO;

public class MemberBean {
	String id;
	String email;
	String password;
	String nickname;
	String introduction;
	Date registerDate;
	String typeId;
	Integer followingCount;
	Integer followerCount;
	boolean isFollow;
	public MemberBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberBean(String id, String email, String password, String nickname, String introduction, Date registerDate,
			String typeId, Integer followingCount, Integer followerCount, boolean isFollow) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.introduction = introduction;
		this.registerDate = registerDate;
		this.typeId = typeId;
		this.followingCount = followingCount;
		this.followerCount = followerCount;
		this.isFollow = isFollow;
	}


	public MemberBean(MemberVO memberVO){
		this.id = memberVO.getId();
		this.email = memberVO.getEmail();
		this.password = memberVO.getPassword();
		this.nickname = memberVO.getNickname();
		this.introduction = memberVO.getIntroduction();
		this.registerDate = memberVO.getRegisterDate();
		this.typeId = memberVO.getTypeId();
		this.followingCount = memberVO.getFollowingCount();
		this.followerCount = memberVO.getFollowerCount();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public Integer getfollowingCount() {
		return followingCount;
	}
	public void setfollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}
	public Integer getfollowerCount() {
		return followerCount;
	}
	public void setfollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}
	
	public Integer getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}

	public Integer getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}

	public boolean isFollow() {
		return isFollow;
	}

	public void setFollow(boolean isFollow) {
		this.isFollow = isFollow;
	}

	public MemberVO toVO(){
		MemberVO memberVO = new MemberVO(id, email, password, nickname, introduction, registerDate, typeId, followingCount, followerCount);
		return memberVO;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
