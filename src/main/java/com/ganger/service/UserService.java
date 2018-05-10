package com.ganger.service;

import java.util.List;

import com.ganger.VO.OtherUserVO;
import com.ganger.VO.UserIdAndTokenVO;
import com.ganger.VO.UserVO;
import com.ganger.entity.User;
import com.ganger.form.AddUserForm;
import com.ganger.form.ChangeUserSetForm;
import com.ganger.form.LoginForm;

public interface UserService {

	List<User> findAll();
	
	UserIdAndTokenVO addNewUser(AddUserForm addUserForm);
	
	void changeUserSet(ChangeUserSetForm changeUserSetForm);
	
	UserIdAndTokenVO loginByPhone(LoginForm loginForm);
	
	UserIdAndTokenVO loginByEmail(LoginForm loginForm);
	
	UserIdAndTokenVO loginByNickName(LoginForm loginForm);
	
	UserVO getSelf(Integer id,String token);
	
	OtherUserVO getOther(Integer id);
	
	boolean available(String nickname);
	
}
