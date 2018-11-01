package com.ganger.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cid;
	/**
	 * 文章id
	 */
	private Integer fid;

	/**
	 * 发布时间
	 */
	private Timestamp post;

	/**
	 * 关联上级评论id
	 */
	private Integer father;

	/**
	 * 评论楼层
	 */
	private Integer floor;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="uid")
	private User user;
	
	private String message;
	
	private Integer status;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="touid")
	private User toUser;
	/**
	 * 已读标志位
	 */
	private Integer readed;
	/**
	 * 是否为回复评论
	 */
	private Integer isreply;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment() {
	}

	public Comment(Integer cid, Integer fid, Timestamp post, Integer father, Integer floor, User user, String message,
			Integer status, User toUser, Integer readed, Integer isreply) {
		this.cid = cid;
		this.fid = fid;
		this.post = post;
		this.father = father;
		this.floor = floor;
		this.user = user;
		this.message = message;
		this.status = status;
		this.toUser = toUser;
		this.readed = readed;
		this.isreply = isreply;
	}

	public Integer getIsreply() {
		return isreply;
	}

	public void setIsreply(Integer isreply) {
		this.isreply = isreply;
	}

	public Integer getReaded() {
		return readed;
	}

	public void setReaded(Integer readed) {
		this.readed = readed;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Integer getFather() {
		return father;
	}

	public void setFather(Integer father) {
		this.father = father;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Timestamp getPost() {
		return post;
	}

	public void setPost(Timestamp post) {
		this.post = post;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Comment readed() {
		this.readed=1;
		return this;
	}

	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", post=" + post + ", father=" + father + ", floor=" + floor + "]";
	}

	
	
}
