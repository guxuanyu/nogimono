package com.ganger.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="获取用户表单")
public class IdAndTokenForm {

	@ApiModelProperty(value = "用户id")
	private Integer uid;
	
	private String token;

	public IdAndTokenForm() {
	}

	public IdAndTokenForm(Integer uid, String token) {
		this.uid = uid;
		this.token = token;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
