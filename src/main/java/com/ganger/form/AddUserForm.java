package com.ganger.form;

import javax.validation.constraints.NotEmpty;

public class AddUserForm {

	@NotEmpty(message="密码不能为空")
	private String password;

	@NotEmpty(message="昵称不能为空")
	private String nickname;
	
	public AddUserForm() {
	}

	public AddUserForm(@NotEmpty(message = "密码不能为空") String password, @NotEmpty(message = "昵称不能为空") String nickname) {
		this.password = password;
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
