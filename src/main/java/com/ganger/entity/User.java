package com.ganger.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	
	private String phone;
	
	private String password;
	
	private String token;
	
	private String nickname;
	
	private String introduction;
	
	private Integer status;

	private Integer sex;
	
	private String avatar;
	
	public User() {
	}


	public User(Integer id, String nickname) {
		this.id = id;
		this.nickname = nickname;
	}

	public User(Integer id, String email, String phone, String password, String token, String nickname,
			String introduction, Integer status, Integer sex, String avatar) {
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.token = token;
		this.nickname = nickname;
		this.introduction = introduction;
		this.status = status;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", phone=" + phone + ", password=" + password + ", token="
				+ token + ", nickname=" + nickname + ", introduction=" + introduction + ", status=" + status + "]";
	}
	
	
}
