package com.raylinetech.ssh2.logistics.common.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.raylinetech.ssh2.logistics.common.dao.UserDao;
import com.raylinetech.ssh2.logistics.common.dao.impl.UserDaoImpl;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.entity.Sku;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;



public class RLOrderServiceTest {
	
//	private UserService userService;
	
	private RLOrderService rlOrderService;
	private static BeanFactory factory = 
		new ClassPathXmlApplicationContext("file:WebRoot/WEB-INF/applicationContext.xml");

	@Before
	public void setUp() throws Exception {
		this.rlOrderService = (RLOrderService)factory.getBean("rlOrderService");
	}

	@After
	public void tearDown() throws Exception {
		this.rlOrderService = null;
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
//		RLOrder order = new RLOrder();
//		order.setAccount("sdfs");
//		RLOrderItem item = new RLOrderItem();
////		item.setSkuno("sk");
//		item.setSku(new Sku("13-P"));
//		List<RLOrderItem> items = new LinkedList<RLOrderItem>();
//		items.add(item);
//		order.setRlorderitems(items);
//		order.setDate("20140513");
//		order.setLogistics(new Logistics(1,"",""));
//		List<RLOrder> orderList = new LinkedList<RLOrder>();
//		orderList.add(order);
//		this.rlOrderService.saveOrUpdateRLOrderList(orderList);
//		System.out.println(orderList.get(0).getRlorderitems().get(0).getId());
//	}

//	@Test
//	public void testFind() {
//		List<RLOrder> order =  this.rlOrderService.getRLOrderListFromDate("20140620","20140620");
//		for (RLOrder rlOrder : order) {
//			System.out.println(rlOrder.getRlordernumber());
//		}
//	}
	
	@Test
	public void testDel() {
		List<RLOrder> order =  this.rlOrderService.getRLOrderListFromDate("20140623","20140624");
		List<Long> ids = new ArrayList<Long>();
		for (RLOrder rlOrder : order) {
			System.out.println(rlOrder.getRlordernumber());
			ids.add(rlOrder.getId());
		}
		this.rlOrderService.deleteRLOrdersAndReturnTrackingno(ids);
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
