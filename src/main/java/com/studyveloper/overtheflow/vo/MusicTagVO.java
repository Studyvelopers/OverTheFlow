package com.studyveloper.overtheflow.vo;

public class MusicTagVO {
	private String contents;
	private String musicId;
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getMusicId() {
		return musicId;
	}
	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((musicId == null) ? 0 : musicId.hashCode());
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
		MusicTagVO other = (MusicTagVO) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (musicId == null) {
			if (other.musicId != null)
				return false;
		} else if (!musicId.equals(other.musicId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MusicTagVO [contents=" + contents + ", musicId=" + musicId + "]";
	}
}
