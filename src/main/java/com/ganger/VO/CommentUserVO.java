package com.ganger.VO;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ganger.entity.User;

/**
 * 评论人对象
 */
@JsonTypeName("user")
public class CommentUserVO {

	private Integer id;
	
	private String nickname;

	private String avatar;
	
	public CommentUserVO() {
	}


	public CommentUserVO(Integer id, String nickname, String avatar) {
		this.id = id;
		this.nickname = nickname;
		this.avatar = avatar;
	}

	public CommentUserVO(User user) {
		this.id=user.getId();
		this.nickname=user.getNickname();
		this.avatar=user.getAvatar();
	}
	
	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
