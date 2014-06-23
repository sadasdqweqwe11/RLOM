package com.raylinetech.ssh2.logistics.common.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.raylinetech.ssh2.logistics.common.dao.UserDao;
import com.raylinetech.ssh2.logistics.common.dao.impl.UserDaoImpl;
import com.raylinetech.ssh2.logistics.common.entity.User;



public class UserServiceTest {
	
//	private UserService userService;
	
	private UserDao userDao;
	private static BeanFactory factory = 
		new ClassPathXmlApplicationContext("file:WebRoot/WEB-INF/applicationContext.xml");

	@Before
	public void setUp() throws Exception {
		this.userDao = (UserDaoImpl)factory.getBean("userDao");
	}

	@After
	public void tearDown() throws Exception {
		this.userDao = null;
	}
	
	
//	@Before
//	public void setUp() throws Exception {
//		this.userService = (UserServiceImpl)factory.getBean("userService");
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		this.userService = null;
//	}

	@Test
	public void testSave() {
		User user = new User();
		user.setUsername("admin");
		user.setNickname("admin");
		user.setPassword("123");
		user.setEmail("com");
			this.userDao.insert(user);
		System.out.println(user.getUid() + user.getNickname());
	}
//
//	@Test
//	public void testFind() {
//		User user = this.userDao.queryByEmail("com");
//		System.out.println(user.getEmail()+user.getPassword());
//	}
//	
//	@Test
//	public void testModify() {
//		User user = new User();
//		user.setUsername("admin2");
//		user.setNickname("admin2");
//		this.userService.modify(user);
//		System.out.println(user.getUid() + user.getNickname());
//	}
//
//	@Test
//	public void testFindUserField() {
//		this.userService.findUserField(1);
//	}
//
//	@Test
//	public void testSaveUserField() {
//		UserField userField = new UserField();
//		userField.setQq("111111111");
//		userField.setWebsite("www.admin1.com");
//		userField.setBio("admin1");
//		this.userService.saveUserField(userField);
//		System.out.println(userField.getUid() + userField.getQq());
//		
//	}
//
//	@Test
//	public void testUpdateUserField() {
//		UserField userField = new UserField();
//		userField.setQq("22222222222");
//		userField.setWebsite("www.admin1.com");
//		userField.setBio("admin1");
//		this.userService.updateUserField(userField);
//		System.out.println(userField.getUid() + userField.getQq());
//	}
//	
//	@Test
//	public void testFindHotUser() {
//		java.util.List<User> user = this.userService.findHotUser();
//		for (User user2 : user) {
//			System.out.println(user2.getNickname());
//		}
//	}
}
