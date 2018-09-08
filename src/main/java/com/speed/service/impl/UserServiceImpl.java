package com.speed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.speed.dao.UserDao;
import com.speed.entity.UserVO;
import com.speed.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserVO> getAllUsers() {
		
		return userDao.queryUserAll();
	}

}
