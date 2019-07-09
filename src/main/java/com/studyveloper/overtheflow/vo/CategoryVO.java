package com.studyveloper.overtheflow.vo;

public class CategoryVO {
	private String id;
	private String name;
	private String superId;
	private String memberId;

	public CategoryVO(){
		
	}
	
	public CategoryVO(String id, String name, String superId, String memberId) {
		this.id = id;
		this.name = name;
		this.superId = superId;
		this.memberId = memberId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() { 
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuperId() { 
		return superId;
	}
	public void setSuperId(String superId) {
		this.superId = superId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((superId == null) ? 0 : superId.hashCode());
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
		CategoryVO other = (CategoryVO) obj;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (superId == null) {
			if (other.superId != null)
				return false;
		} else if (!superId.equals(other.superId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoryVO [id=" + id + ", name=" + name + ", superId=" + superId + ", memberId=" + memberId + "]";
	}
}
