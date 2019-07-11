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
	Integer followCnt;
	Integer followerCnt;
	public MemberBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberBean(String id, String email, String password, String nickname, String introduction, Date registerDate,
			String typeId, Integer followCnt, Integer followerCnt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.introduction = introduction;
		this.registerDate = registerDate;
		this.typeId = typeId;
		this.followCnt = followCnt;
		this.followerCnt = followerCnt;
	}
	
	public MemberBean(MemberVO memberVO){
		this.id = memberVO.getId();
		this.email = memberVO.getEmail();
		this.password = memberVO.getPassword();
		this.nickname = memberVO.getNickname();
		this.introduction = memberVO.getIntroduction();
		this.registerDate = memberVO.getRegisterDate();
		this.typeId = memberVO.getTypeId();
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
	public Integer getFollowCnt() {
		return followCnt;
	}
	public void setFollowCnt(Integer followCnt) {
		this.followCnt = followCnt;
	}
	public Integer getFollowerCnt() {
		return followerCnt;
	}
	public void setFollowerCnt(Integer followerCnt) {
		this.followerCnt = followerCnt;
	}
	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", email=" + email + ", password=" + password + "\n, nickname=" + nickname
				+ ", introduction=" + introduction + "\n, registerDate=" + registerDate + ", typeId=" + typeId
				+ ", followCnt=" + followCnt + ", followerCnt=" + followerCnt + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((followCnt == null) ? 0 : followCnt.hashCode());
		result = prime * result + ((followerCnt == null) ? 0 : followerCnt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((introduction == null) ? 0 : introduction.hashCode());
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
		if (followCnt == null) {
			if (other.followCnt != null)
				return false;
		} else if (!followCnt.equals(other.followCnt))
			return false;
		if (followerCnt == null) {
			if (other.followerCnt != null)
				return false;
		} else if (!followerCnt.equals(other.followerCnt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (introduction == null) {
			if (other.introduction != null)
				return false;
		} else if (!introduction.equals(other.introduction))
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
	
	
	
}
