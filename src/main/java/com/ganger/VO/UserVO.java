package com.ganger.VO;

import com.ganger.entity.User;

public class UserVO {

	private String email;
	
	private String phone;
	
	private String nickname;
	
	private String introduction;
	
	private Integer status;

	private Integer sex;
	
	private String avatar;
	
	public UserVO() {
	}
	
	public UserVO(String email, String phone, String nickname, String introduction, Integer status, Integer sex,
			String avatar) {
		this.email = email;
		this.phone = phone;
		this.nickname = nickname;
		this.introduction = introduction;
		this.status = status;
		this.sex = sex;
		this.avatar = avatar;
	}

	public UserVO(User user) {
		this.email=user.getEmail();
		this.phone=user.getPhone();
		this.nickname=user.getNickname();
		this.introduction=user.getIntroduction();
		this.status=user.getStatus();
		this.sex=user.getSex();
		this.avatar=user.getAvatar();
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
