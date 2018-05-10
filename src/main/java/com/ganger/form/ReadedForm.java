package com.ganger.form;

public class ReadedForm {

	private Integer uid;
	
	private String token;
	
	private String cid;
	
	public ReadedForm() {
	}


	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
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


	public ReadedForm(Integer uid, String token, String cid) {
		this.uid = uid;
		this.token = token;
		this.cid = cid;
	}

}
