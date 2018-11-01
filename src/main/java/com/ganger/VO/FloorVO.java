package com.ganger.VO;

/**
 * 评论楼层
 */
public class FloorVO {

	/**
	 * 文章id
	 */
	private Integer fid;

	/**
	 * 评论
	 */
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
