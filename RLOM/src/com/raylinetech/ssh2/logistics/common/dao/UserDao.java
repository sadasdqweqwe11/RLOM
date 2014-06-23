package com.raylinetech.ssh2.logistics.common.dao;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.User;


public interface UserDao {
	public void insert(User user);

	public void delete(User user);

	public void update(User user);

	public User query(long userId);
	
	public User queryByEmail(String Email);
	
	public User queryByNick(String nickname);

	public List<User> query();

	public List<User> queryUnsignUser();
	
	public List<User> findRandomUser();
}
