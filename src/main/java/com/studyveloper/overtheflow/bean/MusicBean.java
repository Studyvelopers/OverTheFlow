package com.studyveloper.overtheflow.bean;

import java.util.Date;
import java.util.List;

public class MusicBean {
	private String id;
	private String title;
	private	Integer playTime;
	private Date registerDate;
	private String description;
	private Boolean visibilityFlag;
	private Boolean downloadFlag;
	private Integer playCount;
	private String categoryId;
	private String memberId;
	private List<String> musicTags;
	
	public MusicBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MusicBean(String id, String title, Integer playTime, Date registerDate, String description,
			Boolean visibilityFlag, Boolean downloadFlag, Integer playCount, String categoryId, String memberId,
			List<String> musicTags) {
		super();
		this.id = id;
		this.title = title;
		this.playTime = playTime;
		this.registerDate = registerDate;
		this.description = description;
		this.visibilityFlag = visibilityFlag;
		this.downloadFlag = downloadFlag;
		this.playCount = playCount;
		this.categoryId = categoryId;
		this.memberId = memberId;
		this.musicTags = musicTags;
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
	public Integer getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Integer playTime) {
		this.playTime = playTime;
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
	public Boolean getVisibilityFlag() {
		return visibilityFlag;
	}
	public void setVisibilityFlag(Boolean visibilityFlag) {
		this.visibilityFlag = visibilityFlag;
	}
	public Boolean getDownloadFlag() {
		return downloadFlag;
	}
	public void setDownloadFlag(Boolean downloadFlag) {
		this.downloadFlag = downloadFlag;
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
	public List<String> getMusicTags() {
		return musicTags;
	}
	public void setMusicTags(List<String> musicTags) {
		this.musicTags = musicTags;
	}

	@Override
	public String toString() {
		return "MusicBean [id=" + id + ", title=" + title + ", playTime=" + playTime + ", registerDate=" + registerDate
				+ ", description=" + description + ", visibilityFlag=" + visibilityFlag + ", downloadFlag="
				+ downloadFlag + ", playCount=" + playCount + ", categoryId=" + categoryId + ", memberId=" + memberId
				+ ", musicTags=" + musicTags + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((downloadFlag == null) ? 0 : downloadFlag.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((musicTags == null) ? 0 : musicTags.hashCode());
		result = prime * result + ((playCount == null) ? 0 : playCount.hashCode());
		result = prime * result + ((playTime == null) ? 0 : playTime.hashCode());
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((visibilityFlag == null) ? 0 : visibilityFlag.hashCode());
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
		if (downloadFlag == null) {
			if (other.downloadFlag != null)
				return false;
		} else if (!downloadFlag.equals(other.downloadFlag))
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
		if (musicTags == null) {
			if (other.musicTags != null)
				return false;
		} else if (!musicTags.equals(other.musicTags))
			return false;
		if (playCount == null) {
			if (other.playCount != null)
				return false;
		} else if (!playCount.equals(other.playCount))
			return false;
		if (playTime == null) {
			if (other.playTime != null)
				return false;
		} else if (!playTime.equals(other.playTime))
			return false;
		if (registerDate == null) {
			if (other.registerDate != null)
				return false;
		} else if (!registerDate.equals(other.registerDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (visibilityFlag == null) {
			if (other.visibilityFlag != null)
				return false;
		} else if (!visibilityFlag.equals(other.visibilityFlag))
			return false;
		return true;
	}
}
