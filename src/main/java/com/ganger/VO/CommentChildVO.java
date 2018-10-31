package com.ganger.VO;

import com.ganger.entity.Comment;
import com.ganger.utils.TimeUtil;

/**
 * 子评论
 */
public class CommentChildVO {

	/**
	 * 评论人
	 */
	private CommentUserVO user;

	/**
	 * 被回复用户
	 */
	private CommentUserVO touser;

	/**
	 * 评论id
	 */
	private Integer cid;
	
	private String time;
	/**
	 * 评论楼层
	 */
	private Integer floor;
	/**
	 * 评论主体
	 */
	private String msg;

	public CommentChildVO() {
	}

	public CommentChildVO(CommentUserVO user, CommentUserVO touser, Integer cid, String time, Integer floor,
			String msg) {
		this.user = user;
		this.touser = touser;
		this.cid = cid;
		this.time = time;
		this.floor = floor;
		this.msg = msg;
	}

	public CommentChildVO(Comment comment) {
		this.cid=comment.getCid();
		this.floor=comment.getFloor();
		this.msg=comment.getMessage();
		this.time=TimeUtil.showTime(comment.getPost());
		this.touser=comment.getIsreply().equals(1)?new CommentUserVO(comment.getToUser()):null;
		this.user=new CommentUserVO(comment.getUser());
		
	}
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public CommentUserVO getUser() {
		return user;
	}
	
	public void setUser(CommentUserVO user) {
		this.user = user;
	}
	
	public CommentUserVO getTouser() {
		return touser;
	}

	public void setTouser(CommentUserVO touser) {
		this.touser = touser;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
