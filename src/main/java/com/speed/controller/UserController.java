package com.speed.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.speed.entity.UserVO;
import com.speed.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping("/getAll")
	public List<UserVO> getAll(){
		return userService.getAllUsers();
	}
}
