package com.ganger.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="添加评论表单")
public class AddCommentForm {

	private Integer uid;
	@ApiModelProperty(value = "文章id")
	private Integer fid;
	
	private String message;

	@ApiModelProperty(value = "主评论id")
	private Integer father;

	@ApiModelProperty(value = "副评论id")
	private Integer touid;

	private String token;
	
	public AddCommentForm() {
	}

	public AddCommentForm(Integer uid, Integer fid, String message, Integer father, Integer touid, String token) {
		this.uid = uid;
		this.fid = fid;
		this.message = message;
		this.father = father;
		this.touid = touid;
		this.token = token;
	}

	public Integer getTouid() {
		return touid;
	}

	public void setTouid(Integer touid) {
		this.touid = touid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getFather() {
		return father;
	}

	public void setFather(Integer father) {
		this.father = father;
	}
	
	
}
