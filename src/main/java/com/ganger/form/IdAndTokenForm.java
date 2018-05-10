package com.ganger.form;

public class IdAndTokenForm {

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
