package com.studyveloper.overtheflow.vo;

import java.util.Date;

public class MusicVO {
	private String id;
	private String title;
	private	int playTime;
	private Date registerDate;
	private String description;
	private boolean visibilityFlag;
	private boolean downloadFlag;
	private int playCount;
	private String categoryId;
	private String memberId;
	
	public MusicVO(){
		
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
	public int getPlayTime() {
		return playTime;
	}
	public void setPlayTime(int playTime) {
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
	public boolean isVisibilityFlag() {
		return visibilityFlag;
	}
	public void setVisibilityFlag(boolean visibilityFlag) {
		this.visibilityFlag = visibilityFlag;
	}
	public boolean isDownloadFlag() {
		return downloadFlag;
	}
	public void setDownloadFlag(boolean downloadFlag) {
		this.downloadFlag = downloadFlag;
	}
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
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
	
	@Override
	public String toString() {
		return "MusicVO [id=" + id + ", title=" + title + ", playTime=" + playTime + ", registerDate=" + registerDate
				+ ", description=" + description + ", visibilityFlag=" + visibilityFlag + ", downloadFlag="
				+ downloadFlag + ", playCount=" + playCount + ", categoryId=" + categoryId + ", memberId=" + memberId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (downloadFlag ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + playCount;
		result = prime * result + playTime;
		result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + (visibilityFlag ? 1231 : 1237);
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
		MusicVO other = (MusicVO) obj;
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
		if (downloadFlag != other.downloadFlag)
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
		if (playCount != other.playCount)
			return false;
		if (playTime != other.playTime)
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
		if (visibilityFlag != other.visibilityFlag)
			return false;
		return true;
	}
}
