package com.studyveloper.overtheflow.bean;

import java.util.Date;
import java.util.List;

import com.studyveloper.overtheflow.vo.PlaylistVO;
import com.studyveloper.overtheflow.vo.TagVO;

public class PlaylistBean {
	private String id;
	private String title;
	private String description;
	private Date registerDate;
	private Boolean visibility;
	private String memberId;
	private List<String> tags;
	private String memberNickname;
	
	public PlaylistBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlaylistBean(String id, String title, String description, Date registerDate, Boolean visibility,
			String memberId, List<String> tags, String memberNickname) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.registerDate = registerDate;
		this.visibility = visibility;
		this.memberId = memberId;
		this.tags = tags;
		this.memberNickname = memberNickname;
	}
	
	public PlaylistBean(PlaylistVO playlistVO){
		this.id = playlistVO.getId();
		this.title = playlistVO.getTitle();
		this.description =  playlistVO.getDescription();
		this.registerDate = playlistVO.getRegisterDate();
		this.visibility = playlistVO.getVisibility();
		this.memberId = playlistVO.getMemberId();
		this.tags = playlistVO.getTags();
		this.memberNickname = playlistVO.getMemberNickname();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public PlaylistVO toVO(){
		PlaylistVO playlistVO = new PlaylistVO(id, title, description, registerDate, visibility,
				memberId, tags, memberNickname);
		
		return playlistVO;
	}
	
	@Override
	public String toString() {
		return "PlaylistBean [id=" + id + ", title=" + title + ", description=" + description + ", registerDate="
				+ registerDate + ", visibility=" + visibility + ", memberId=" + memberId + ", tags=" + tags
				+ ", memberNickname=" + memberNickname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((memberNickname == null) ? 0 : memberNickname.hashCode());
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((visibility == null) ? 0 : visibility.hashCode());
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
		PlaylistBean other = (PlaylistBean) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (memberNickname == null) {
			if (other.memberNickname != null)
				return false;
		} else if (!memberNickname.equals(other.memberNickname))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (visibility == null) {
			if (other.visibility != null)
				return false;
		} else if (!visibility.equals(other.visibility))
			return false;
		return true;
	}
}
