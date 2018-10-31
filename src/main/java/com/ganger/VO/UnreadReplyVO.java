package com.ganger.VO;

/**
 * 未读回复
 */
public class UnreadReplyVO {

	/**
	 * 消息来源人
	 */
	private CommentUserVO fromuser;

	/**
	 * 消息id
	 */
	private Integer cid;

	/**
	 *
	 */
	private Integer fid;

	/**
	 * 时间
	 */
	private String time;

	/**
	 * 消息主体
	 */
	private String message;

	/**
	 * 回复的消息的id
	 */
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
