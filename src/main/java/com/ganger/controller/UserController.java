package com.ganger.controller;

import javax.validation.Valid;

import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ganger.VO.ResponseVo;
import com.ganger.form.AddUserForm;
import com.ganger.form.ChangeUserSetForm;
import com.ganger.form.LoginForm;
import com.ganger.service.UserService;
import com.ganger.utils.ResponseUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/all")
	public ResponseVo allUser() {
		return ResponseUtil.success(userService.findAll());
	}
	
	@PostMapping("/new")
	public ResponseVo addNewUser(@Valid AddUserForm addUserForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
		}
		return ResponseUtil.success(userService.addNewUser(addUserForm));
	}
	
	@PostMapping("/userset")
	public ResponseVo changeIntroduction(@Valid ChangeUserSetForm changeUserSetForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
		}
		userService.changeUserSet(changeUserSetForm);
		return ResponseUtil.success();
	}
	
	@PostMapping("/login/phone")
	public ResponseVo loginByPhone(LoginForm loginForm) {
		return ResponseUtil.success(userService.loginByPhone(loginForm));
	}
	
	@PostMapping("/login/email")
	public ResponseVo loginByEmail(LoginForm loginForm) {
		return ResponseUtil.success(userService.loginByEmail(loginForm));
	}
	
	@PostMapping("/login/nickname")
	public ResponseVo loginByNickname(LoginForm loginForm) {
		return ResponseUtil.success(userService.loginByNickName(loginForm));
	}
	
	@PostMapping("/available")
	public ResponseVo available(String nickname) {
		return ResponseUtil.success(userService.available(nickname));
	}
	
	@PostMapping("/get")
	public ResponseVo getOne(Integer id, String token) {
		return ResponseUtil.success(userService.getSelf(id, token));
	}
	
	@PostMapping("/getother")
	public ResponseVo getOther(Integer id) {
		return ResponseUtil.success(userService.getOther(id));
	}
}
