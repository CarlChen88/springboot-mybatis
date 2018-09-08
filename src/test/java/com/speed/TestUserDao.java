package com.speed;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.speed.dao.UserDao;
import com.speed.entity.UserVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserDao {
	
	@Autowired
	private UserDao userDao;
	@Test
	public void queryUserAll() {
		System.out.println("2222");
		List<UserVO> userList=userDao.queryUserAll();
		//测试：判断取到的值和断言的值做对比
		//assertEquals(5, userList.size());
		
		for(UserVO vo:userList) {
			System.out.println(vo.toString());
		}
	}
	
	@Test
	public void queryUserByUserId() {
		int userId=2;
		UserVO user=userDao.queryUserByUserId(userId);
		System.out.println(user);
	}

	@Test
	public void insertUser() {
		UserVO user=new UserVO();
		user.setUserName("小花");
		user.setPassword("466578");
		int n=userDao.insertUser(user);
		System.out.println(n);
	}
	@Test
	public void updateUser() {
		UserVO user=userDao.queryUserByUserId(3);
		user.setUserName("小米");
		int n=userDao.updateUser(user);
		System.out.println(n);
	}
	@Test
	public void deleteUser() {
		int n=userDao.deleteUser(3);
		System.out.println(n);
	}
	@Test
	public void insertUsers() {
		List<UserVO> users=new ArrayList<UserVO>();
		for(int i=0;i<5;i++) {
			UserVO user=new UserVO();
			user.setUserName("晓明"+i+"号");
			user.setPassword("12345"+i);
			users.add(user);
		}
		int n=userDao.inserUsers(users);
		System.out.println(n);
	}
	@Test
	public void deleteBatch() {
		String[] idArray="4,5,6".split(",");
		if(idArray.length==0) {
			idArray=null;
		}
		int n=userDao.deleteBatch(idArray);
		System.out.println(n);
	}
	@Test
	public void updateBatch() {
		UserVO user=new UserVO();
		user.setUserName("nnnnnnnn");
		user.setPassword("pppppppp");
		String[] ids="5,6,7,8".split(",");
		int n=userDao.updateBatch(ids, user);
		System.out.println(n);
	}
}
