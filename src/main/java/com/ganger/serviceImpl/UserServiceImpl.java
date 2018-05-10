package com.ganger.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganger.VO.OtherUserVO;
import com.ganger.VO.UserIdAndTokenVO;
import com.ganger.VO.UserVO;
import com.ganger.entity.User;
import com.ganger.exception.MyException;
import com.ganger.form.AddUserForm;
import com.ganger.form.ChangeUserSetForm;
import com.ganger.form.LoginForm;
import com.ganger.repository.UserRepository;
import com.ganger.service.UserService;
import com.ganger.utils.CheckUserUtil;
import com.ganger.utils.MD5Util;
import com.ganger.utils.TextUtil;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CheckUserUtil checkUserUtil;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}


	@Override
	public UserIdAndTokenVO addNewUser(AddUserForm addUserForm) {
		User user=new User();
//		if(userRepository.findByPhone(addUserForm.getPhone())!=null) {
//			throw new RuntimeException("手机已经注册过啦");
//		}
//		if(userRepository.findByEmail(addUserForm.getEmail())!=null) {
//			throw new RuntimeException("邮箱已经注册过啦");
//		}
		if(userRepository.findByNickname(addUserForm.getNickname())!=null) {
			String err="该昵称已经注册过啦 ";
			throw new MyException(err,"id:["+addUserForm.getNickname()+"]"+err);
		}
		
		TextUtil.nickNameLengthCheck(addUserForm.getNickname());
		
		BeanUtils.copyProperties(addUserForm, user);
		user.setPassword(MD5Util.MD5(user.getPassword()));
		user.setId(0);
		user.setAvatar("");
		user.setIntroduction("");
		user.setPhone("");
		user.setEmail("");
		user.setStatus(0);
		user.setToken(MD5Util.MD5(user.getNickname()+user.getPassword()));
		User user2=userRepository.save(user);
		return new UserIdAndTokenVO(user2.getId().toString(),user2.getToken());
	}

	@Override
	public void changeUserSet(ChangeUserSetForm changeIntructionForm) {
		Optional<User> user=userRepository.findById(changeIntructionForm.getId());
		
		//合法验证
		checkUserUtil.checkUser(changeIntructionForm.getId(), changeIntructionForm.getToken());
		
		User user2=user.get();
		if(changeIntructionForm.getIntroduction()!=null) {
			user2.setIntroduction(changeIntructionForm.getIntroduction());
		}
		
		if(!TextUtil.isNull(changeIntructionForm.getNickname())) {
			if(userRepository.findByNickname(changeIntructionForm.getNickname())!=null&&
					!user2.getNickname().equals(changeIntructionForm.getNickname())) {
				String err="该昵称已经注册过啦";
				throw new MyException(err,"id:["+user2.getId()+"] "+"nickname:["+user2.getNickname()+"]"+err);
			}
			user2.setNickname(changeIntructionForm.getNickname());
		}
		
		if(!TextUtil.isNull(changeIntructionForm.getPhone())) {
			if(userRepository.findByPhone(changeIntructionForm.getPhone())!=null &&
					!user2.getPhone().equals(changeIntructionForm.getPhone())) {
				String err="该手机已经注册过啦";
				throw new MyException(err,"id:["+user2.getId()+"] "+"phone:["+user2.getPhone()+"]"+err);
			}
			user2.setPhone(changeIntructionForm.getPhone());
		}
		if(!TextUtil.isNull(changeIntructionForm.getEmail())) {
			if(userRepository.findByEmail(changeIntructionForm.getEmail())!=null && 
					!user2.getEmail().equals(changeIntructionForm.getEmail())) {
				String err="邮箱已经注册过啦";
				throw new MyException(err,"id:["+user2.getId()+"] "+"email:["+user2.getEmail()+"]"+err);
			}
			user2.setEmail(changeIntructionForm.getEmail());
		}
		if(changeIntructionForm.getSex()!=null) {
			user2.setSex(changeIntructionForm.getSex());
		}
		if(!TextUtil.isNull(changeIntructionForm.getAvatar())) {
			user2.setAvatar(changeIntructionForm.getAvatar());
		}
		userRepository.save(user2);
		
	}
	
	@Override
	public UserIdAndTokenVO loginByPhone(LoginForm loginForm) {
		if(loginForm.getPhone()==null) {
			String err="手机号不能为空";
			throw new MyException(err,err);
		}
		User user=userRepository.findByPhone(loginForm.getPhone());
		if(user==null) {
			String err="您还没注册吧";
			throw new MyException(err,"phone:["+loginForm.getPhone()+"]"+err);
		}
		if(!MD5Util.MD5(loginForm.getPassword()).equals(user.getPassword())) {
			String err="密码错误";
			throw new MyException(err,"phone:["+loginForm.getPhone()+"] "+"psw:["+MD5Util.MD5(loginForm.getPassword())+"]"+err);
		}
		return new UserIdAndTokenVO(user.getId().toString(),user.getToken());
	}
	
	@Override
	public UserIdAndTokenVO loginByEmail(LoginForm loginForm) {
		if(loginForm.getEmail()==null) {
			String err="邮箱不能为空";
			throw new MyException(err,err);
		}
		User user=userRepository.findByEmail(loginForm.getEmail());
		if(user==null) {
			String err="您还没注册吧";
			throw new MyException(err,"email:["+loginForm.getEmail()+"]"+err);
		}
		if(!MD5Util.MD5(loginForm.getPassword()).equals(user.getPassword())) {
			String err="密码错误";
			throw new MyException(err,"email:["+loginForm.getEmail()+"] "+"psw:["+MD5Util.MD5(loginForm.getPassword())+"]"+err);
		}
		return new UserIdAndTokenVO(user.getId().toString(),user.getToken());
	}
	
	@Override
	public UserIdAndTokenVO loginByNickName(LoginForm loginForm) {
		if(loginForm.getNickname()==null) {
			String err="昵称不能为空";
			throw new MyException(err,err);
		}
		User user=userRepository.findByNickname(loginForm.getNickname());
		if(user==null) {
			String err="您还没注册吧";
			throw new MyException(err,"nickname:["+loginForm.getNickname()+"]"+err);
		}
		if(!MD5Util.MD5(loginForm.getPassword()).equals(user.getPassword())) {
			String err="密码错误";
			throw new MyException(err,"nickname:["+loginForm.getNickname()+"] "+"psw:["+MD5Util.MD5(loginForm.getPassword())+"]"+err);
		}
		return new UserIdAndTokenVO(user.getId().toString(),user.getToken());
	}
	
	@Override
	public UserVO getSelf(Integer id, String token) {
		Optional<User> user=userRepository.findById(id);
		
		checkUserUtil.checkUser(id, token);
		return new UserVO(user.orElse(null));
	}
	
	@Override
	public OtherUserVO getOther(Integer id) {
		Optional<User> userOptional=userRepository.findById(id);
		String noUserErr="不存在此用户";
		User otherUser=userOptional.orElseThrow(()->new MyException(noUserErr, "input_id:["+id+"] "+noUserErr));
		
		return new OtherUserVO(otherUser);
		
	}
	
	@Override
	public boolean available(String nickname) {
		
		return userRepository.findByNickname(nickname)==null?true:false;
	}
}