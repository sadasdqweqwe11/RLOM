package com.raylinetech.ssh2.logistics.common.service.impl;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.dao.UserDao;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.service.UserService;


public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public User find(long name) {
		User user = null;
		user = this.userDao.query(name);
		return user;
	}
	@Override
	public User findByEmail(String email) {
		User user = null;
		user = this.userDao.queryByEmail(email);
		return user;
	}
	
	
	public User findByNick(String nickname) {
		User user = null;
		user = this.userDao.queryByNick(nickname);
		return user;
	}
	
	public void save(User user){
					this.userDao.insert(user);
	}

	public void modify(User user) {
		this.userDao.update(user);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}




	@Override
	public boolean deleteUnsignUser(){
		List<User> users= this.userDao.queryUnsignUser();
		for (User user : users) {
			this.userDao.delete(user);
		}
		return true;
	}
	@Override
	public boolean deleteUser(User user){
		this.userDao.delete(user);
		return true;
	}
		
}
