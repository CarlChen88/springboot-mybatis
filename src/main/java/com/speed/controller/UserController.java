package com.speed.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.speed.entity.UserVO;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="toLogin",method=RequestMethod.GET)
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public UserVO login(UserVO user) {
		return user;
	}
	
	@RequestMapping("/test")
	public String test() {
		LOGGER.info("INFO................................");
		LOGGER.error("ERROR...............................");
		LOGGER.warn("WARN...................................");
		return "ok";
	}
}
