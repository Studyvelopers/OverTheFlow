package com.studyveloper.overtheflow.bean;

import java.util.Date;
import java.util.List;

import com.studyveloper.overtheflow.vo.MusicVO;

public class MusicBean {
	private String id;
	private String title;
	private	Integer playtime;
	private Date registerDate;
	private String description;
	private Boolean visibility;
	private Boolean downloadable;
	private Integer playCount;
	private String categoryId;
	private String memberId;
	private List<String> tags;
	private String memberNickname;
	
	public MusicBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MusicBean(String id, String title, Integer playtime, Date registerDate, String description,
			Boolean visibility, Boolean downloadable, Integer playCount, String categoryId, String memberId,
			List<String> tags, String memberNickname) {
		super();
		this.id = id;
		this.title = title;
		this.playtime = playtime;
		this.registerDate = registerDate;
		this.description = description;
		this.visibility = visibility;
		this.downloadable = downloadable;
		this.playCount = playCount;
		this.categoryId = categoryId;
		this.memberId = memberId;
		this.tags = tags;
		this.memberNickname = memberNickname;
	}

	public MusicBean(MusicVO musicVO){
		this.id = musicVO.getId();
		this.title = musicVO.getTitle();
		this.playtime = musicVO.getPlaytime();
		this.registerDate = musicVO.getRegisterDate();
		this.description = musicVO.getDescription();
		this.visibility = musicVO.getVisibility();
		this.downloadable = musicVO.getDownloadable();
		this.playCount = musicVO.getPlayCount();
		this.categoryId = musicVO.getCategoryId();
		this.memberId = musicVO.getMemberId();
		this.tags = musicVO.getTags();
		this.memberNickname = musicVO.getMemberNickname();
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

	public Integer getPlaytime() {
		return playtime;
	}

	public void setPlaytime(Integer playtime) {
		this.playtime = playtime;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

	public Boolean getDownloadable() {
		return downloadable;
	}

	public void setDownloadable(Boolean downloadable) {
		this.downloadable = downloadable;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

	public MusicVO toVO(){
		MusicVO musicVO = new MusicVO(id, title, playtime, registerDate, description, 
				visibility, downloadable, playCount, categoryId, memberId, tags, memberNickname);
		return musicVO;
	}
	
	@Override
	public String toString() {
		return "MusicBean [id=" + id + ", title=" + title + ", playtime=" + playtime + ", registerDate=" + registerDate
				+ ", description=" + description + ", visibility=" + visibility + ", downloadable=" + downloadable
				+ ", playCount=" + playCount + ", categoryId=" + categoryId + ", memberId=" + memberId + ", tags="
				+ tags + ", memberNickname=" + memberNickname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((downloadable == null) ? 0 : downloadable.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((memberNickname == null) ? 0 : memberNickname.hashCode());
		result = prime * result + ((playCount == null) ? 0 : playCount.hashCode());
		result = prime * result + ((playtime == null) ? 0 : playtime.hashCode());
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
		MusicBean other = (MusicBean) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (downloadable == null) {
			if (other.downloadable != null)
				return false;
		} else if (!downloadable.equals(other.downloadable))
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
		if (playCount == null) {
			if (other.playCount != null)
				return false;
		} else if (!playCount.equals(other.playCount))
			return false;
		if (playtime == null) {
			if (other.playtime != null)
				return false;
		} else if (!playtime.equals(other.playtime))
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