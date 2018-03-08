package com.itheima.service;

import java.sql.SQLException;

import com.itheima.dao.UserDao;

public class UserService {
    UserDao dao=new UserDao();
	public boolean checkUsername(String username) throws SQLException {
		Long count=dao.checkUsername(username);
		
		return count>0?true:false;
	}

}
