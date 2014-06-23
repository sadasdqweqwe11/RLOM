package com.raylinetech.ssh2.logistics.common.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.raylinetech.ssh2.logistics.common.dao.TrackingNoDao;
import com.raylinetech.ssh2.logistics.common.dao.UserDao;
import com.raylinetech.ssh2.logistics.common.dao.impl.TrackingNoDaoImpl;
import com.raylinetech.ssh2.logistics.common.dao.impl.UserDaoImpl;
import com.raylinetech.ssh2.logistics.common.entity.TrackingNo;
import com.raylinetech.ssh2.logistics.common.entity.User;



public class TrackingNoDaoTest {
	
//	private UserService userService;
	
	private TrackingNoDao trackingNodao;
	private static BeanFactory factory = 
		new ClassPathXmlApplicationContext("file:WebRoot/WEB-INF/applicationContext.xml");

	@Before
	public void setUp() throws Exception {
		this.trackingNodao = (TrackingNoDaoImpl)factory.getBean("trackingNoDao");
	}

	@After
	public void tearDown() throws Exception {
		this.trackingNodao = null;
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

//	@Test
//	public void testSave() {
//		User user = new User();
//		user.setUsername("admin");
//		user.setNickname("admin");
//		user.setPassword("123");
//		user.setEmail("com");
//			this.userDao.insert(user);
//		System.out.println(user.getUid() + user.getNickname());
//	}
//
	@Test
	public void testFind() {
		List<TrackingNo> trackingNos = this.trackingNodao.getTrackingNo(5,1);
		for (TrackingNo trackingNo : trackingNos) {
			System.out.println(trackingNo.getTrackingno());
		}
	}
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
