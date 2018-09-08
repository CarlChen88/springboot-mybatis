package com.speed.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.speed.entity.UserVO;



public interface UserDao {
	List<UserVO> queryUserAll();
	UserVO queryUserByUserId(int userId);
	int insertUser(UserVO user);
	int updateUser(UserVO user);
	int deleteUser(int id);
	//批量插入
	int  inserUsers(@Param("emps")List<UserVO> emps);
	//批量删除
	int deleteBatch(@Param("emps") String[] emps);
	//批量更新
	int updateBatch(@Param("emps") String[] emps,@Param("user") UserVO user);
}
