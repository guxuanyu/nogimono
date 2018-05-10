package com.ganger.VO;

import com.ganger.entity.User;

public class OtherUserVO {

	private String nickname;
	
	private String introduction;
	
	private Integer status;

	private Integer sex;
	
	private String avatar;

	public OtherUserVO() {
	}

	public OtherUserVO(String nickname, String introduction, Integer status, Integer sex, String avatar) {
		this.nickname = nickname;
		this.introduction = introduction;
		this.status = status;
		this.sex = sex;
		this.avatar = avatar;
	}

	public OtherUserVO(User user) {
		this.nickname=user.getNickname();
		this.introduction=user.getIntroduction();
		this.sex=user.getSex();
		this.status=user.getStatus();
		this.avatar=user.getAvatar();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	
	
}
