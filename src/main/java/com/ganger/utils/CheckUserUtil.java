package com.ganger.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ganger.entity.User;
import com.ganger.exception.MyException;
import com.ganger.repository.UserRepository;

@Component
public class CheckUserUtil {

	@Autowired
	UserRepository userRepository;
	
	public void checkUser(Integer id,String token) {
		if(id==null || token==null) {
			String err="id或token不能为空";
			throw new MyException(err, err);
		}
		Optional<User> userOptional=userRepository.findById(id);
		String noUserErr="无此用户";
		User user=userOptional.orElseThrow(()->new MyException(noUserErr, "input_uid:["+id+"] "+noUserErr));
		if(!user.getToken().equals(token)) {
			String err="验证失败";
			throw new MyException(err, "input_id:["+id+"] "+"token:["+token+"]"+err);
		}
	}
}
