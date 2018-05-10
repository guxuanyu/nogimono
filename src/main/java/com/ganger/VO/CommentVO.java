package com.ganger.VO;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ganger.entity.Comment;
import com.ganger.utils.TimeUtil;

public class CommentVO {

	private Integer cid;
	
	@JsonProperty("user")
	private CommentUserVO commentUserVO;
	
	private String time;
	
	private Integer floor;
	
	private String msg;
	
	@JsonProperty("child")
	private List<CommentChildVO> childVOs=new ArrayList<CommentChildVO>();

	public CommentVO() {
	}

	public CommentVO(Integer cid, CommentUserVO commentUserVO, String time, Integer floor, String msg,
			List<CommentChildVO> childVOs) {
		this.cid = cid;
		this.commentUserVO = commentUserVO;
		this.time = time;
		this.floor = floor;
		this.msg = msg;
		this.childVOs = childVOs;
	}
	
	public CommentVO(Comment comment) {
		this.cid=comment.getCid();
		this.commentUserVO=new CommentUserVO(comment.getUser());
		this.floor=comment.getFloor();
		this.msg=comment.getMessage();
		this.time=TimeUtil.showTime(comment.getPost());
		
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public CommentUserVO getCommentUserVO() {
		return commentUserVO;
	}

	public void setCommentUserVO(CommentUserVO commentUserVO) {
		this.commentUserVO = commentUserVO;
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

	public List<CommentChildVO> getChildVOs() {
		return childVOs;
	}

	public void setChildVOs(List<CommentChildVO> childVOs) {
		this.childVOs = childVOs;
	}

	@Override
	public String toString() {
		return "CommentVO [cid=" + cid + ", commentUserVO=" + commentUserVO + ", time=" + time + ", floor=" + floor
				+ ", msg=" + msg + ", childVOs=" + childVOs + "]";
	}
	
	
}
