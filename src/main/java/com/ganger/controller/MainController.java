package com.ganger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ganger.utils.MD5Util;

@RestController
public class MainController {

	@GetMapping("/hello")
	public String hello() {
		return MD5Util.MD5("hello");
	}
}
