package com.ganger.VO;

public class FloorVO {

	private Integer fid;
	
	private CommentVO floor;

	public FloorVO() {
	}

	public FloorVO(Integer fid, CommentVO floor) {
		this.fid = fid;
		this.floor = floor;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public CommentVO getFloor() {
		return floor;
	}

	public void setFloor(CommentVO floor) {
		this.floor = floor;
	}
	
	
}
