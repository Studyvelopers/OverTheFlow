package com.studyveloper.overtheflow.vo;

public class PlaylistTagVO {
	private String playlistId;
	private String tag;
	public String getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(String playlistId) {
		this.playlistId = playlistId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playlistId == null) ? 0 : playlistId.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
		PlaylistTagVO other = (PlaylistTagVO) obj;
		if (playlistId == null) {
			if (other.playlistId != null)
				return false;
		} else if (!playlistId.equals(other.playlistId))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PlaylistTagVO [playlistId=" + playlistId + ", tag=" + tag + "]";
	}
}
