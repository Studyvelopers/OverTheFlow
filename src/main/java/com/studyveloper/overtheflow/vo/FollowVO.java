package com.studyveloper.overtheflow.vo;

public class FollowVO {
	private String followingId;
	private String followerId;
	public FollowVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FollowVO(String followingId, String followerId) {
		super();
		this.followingId = followingId;
		this.followerId = followerId;
	}
	public String getFollowingId() {
		return followingId;
	}
	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}
	public String getFollowerId() {
		return followerId;
	}
	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}
	@Override
	public String toString() {
		return "FollowVO [followingId=" + followingId + ", followerId=" + followerId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followerId == null) ? 0 : followerId.hashCode());
		result = prime * result + ((followingId == null) ? 0 : followingId.hashCode());
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
		FollowVO other = (FollowVO) obj;
		if (followerId == null) {
			if (other.followerId != null)
				return false;
		} else if (!followerId.equals(other.followerId))
			return false;
		if (followingId == null) {
			if (other.followingId != null)
				return false;
		} else if (!followingId.equals(other.followingId))
			return false;
		return true;
	}
	
}
