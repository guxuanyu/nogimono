package com.ganger.VO;

public class UnreadReplyVO {

	private CommentUserVO fromuser;
	
	private Integer cid;
	
	private Integer fid;
	
	private String time;
	
	private String message;

	private Integer fathercid;
	
	public UnreadReplyVO() {
	}
	public UnreadReplyVO(CommentUserVO fromuser, Integer cid, Integer fid, String time, String message,
			Integer fathercid) {
		this.fromuser = fromuser;
		this.cid = cid;
		this.fid = fid;
		this.time = time;
		this.message = message;
		this.fathercid = fathercid;
	}
	public Integer getFathercid() {
		return fathercid;
	}
	public void setFathercid(Integer fathercid) {
		this.fathercid = fathercid;
	}
	public Integer getCid() {
		return cid;
	}



	public void setCid(Integer cid) {
		this.cid = cid;
	}



	public CommentUserVO getFromuser() {
		return fromuser;
	}

	public void setFromuser(CommentUserVO fromuser) {
		this.fromuser = fromuser;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
