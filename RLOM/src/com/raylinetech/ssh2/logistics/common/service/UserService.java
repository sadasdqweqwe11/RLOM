package com.raylinetech.ssh2.logistics.common.service;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.User;


public interface UserService {
	public User find(long uid);
	
	public User findByEmail(String email);
	
	public User findByNick(String nickname);

	public void save(User user);

	public void modify(User user);


	public boolean deleteUnsignUser() ;

	
	public boolean deleteUser(User user) ;
}
