package com.ganger.form;

import javax.validation.constraints.NotEmpty;

public class LoginForm {

	private String phone;
	
	private String email;
	
	private String nickname;
	
	@NotEmpty(message="密码必填")
	private String password;

	public LoginForm() {
	}


	public LoginForm(String phone, String email, String nickname, String password) {
		this.phone = phone;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
