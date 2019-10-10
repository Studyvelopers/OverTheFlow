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
	String image;
	public MemberBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public MemberBean(String id, String email, String password, String nickname, String introduction, Date registerDate,
			String typeId, Integer followingCount, Integer followerCount, boolean isFollow, String image) {
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
		this.image = image;
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
	

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public MemberVO toVO(){
		MemberVO memberVO = new MemberVO(id, email, password, nickname, introduction, registerDate, typeId, followingCount, followerCount);
		return memberVO;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((followerCount == null) ? 0 : followerCount.hashCode());
		result = prime * result + ((followingCount == null) ? 0 : followingCount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((introduction == null) ? 0 : introduction.hashCode());
		result = prime * result + (isFollow ? 1231 : 1237);
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberBean other = (MemberBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (followerCount == null) {
			if (other.followerCount != null)
				return false;
		} else if (!followerCount.equals(other.followerCount))
			return false;
		if (followingCount == null) {
			if (other.followingCount != null)
				return false;
		} else if (!followingCount.equals(other.followingCount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (introduction == null) {
			if (other.introduction != null)
				return false;
		} else if (!introduction.equals(other.introduction))
			return false;
		if (isFollow != other.isFollow)
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", email=" + email + ", password=" + password + ", nickname=" + nickname
				+ ", introduction=" + introduction + ", registerDate=" + registerDate + ", typeId=" + typeId
				+ ", followingCount=" + followingCount + ", followerCount=" + followerCount + ", isFollow=" + isFollow
				+ ", image=" + image + "]";
	}
	
	
	
	
	
}
