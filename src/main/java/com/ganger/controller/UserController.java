package com.ganger.controller;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "用户接口")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/all")
	@ApiOperation(value = "获得所有用户列表", notes = "")
	public ResponseVo allUser() {
		return ResponseUtil.success(userService.findAll());
	}
	
	@PostMapping("/new")
	@ApiOperation(value = "添加用户", notes = "")
	public ResponseVo addNewUser(AddUserForm addUserForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
		}
		return ResponseUtil.success(userService.addNewUser(addUserForm));
	}
	
	@PostMapping("/userset")
	@ApiOperation(value = "修改用户", notes = "")
	public ResponseVo changeIntroduction(@Valid ChangeUserSetForm changeUserSetForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
		}
		userService.changeUserSet(changeUserSetForm);
		return ResponseUtil.success();
	}
	
	@PostMapping("/login/phone")
	@ApiOperation(value = "通过手机号登录", notes = "")
	public ResponseVo loginByPhone(LoginForm loginForm) {
		return ResponseUtil.success(userService.loginByPhone(loginForm));
	}
	
	@PostMapping("/login/email")
	@ApiOperation(value = "通过邮箱登录", notes = "")
	public ResponseVo loginByEmail(LoginForm loginForm) {
		return ResponseUtil.success(userService.loginByEmail(loginForm));
	}
	
	@PostMapping("/login/nickname")
	@ApiOperation(value = "通过昵称登录", notes = "")
	public ResponseVo loginByNickname(LoginForm loginForm) {
		return ResponseUtil.success(userService.loginByNickName(loginForm));
	}
	
	@PostMapping("/available")
	@ApiOperation(value = "验证昵称是否可用", notes = "")
	public ResponseVo available(String nickname) {
		return ResponseUtil.success(userService.available(nickname));
	}
	
	@PostMapping("/get")
	@ApiOperation(value = "验证验证并返回用户数据", notes = "")
	public ResponseVo getOne(Integer id, String token) {
		return ResponseUtil.success(userService.getSelf(id, token));
	}
	
	@PostMapping("/getother")
	@ApiOperation(value = "获取其他用户信息", notes = "")
	public ResponseVo getOther(Integer id) {
		return ResponseUtil.success(userService.getOther(id));
	}
}
