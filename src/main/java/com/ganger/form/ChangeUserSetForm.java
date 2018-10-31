package com.ganger.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(value="用户修改表单")
public class ChangeUserSetForm {

	@NotNull(message="id不能为空")
	@ApiModelProperty(required = true)
	private Integer id;
	
	@NotEmpty(message="token不能为空")
	@ApiModelProperty(required = true)
	private String token;
	
	private String nickname;
	
	private String introduction;
	
	private String email;
	
	private String phone;

	private Integer sex;
	
	private String avatar;
	
	public ChangeUserSetForm() {
	}

	public ChangeUserSetForm(@NotNull(message = "id不能为空") Integer id, @NotEmpty(message = "token不能为空") String token,
			String nickname, String introduction, String email, String phone, Integer sex, String avatar) {
		this.id = id;
		this.token = token;
		this.nickname = nickname;
		this.introduction = introduction;
		this.email = email;
		this.phone = phone;
		this.sex = sex;
		this.avatar = avatar;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
