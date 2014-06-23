package com.raylinetech.ssh2.logistics.common.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.raylinetech.ssh2.logistics.common.dao.UserDao;
import com.raylinetech.ssh2.logistics.common.entity.User;


public class UserDaoImpl implements UserDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);
	
	private HibernateTemplate hibernateTemplate;

	public void delete(User user) {
		try {
			this.hibernateTemplate.delete(user);
		} catch (DataAccessException e) {
			logger.error(e.getStackTrace().toString());
		}
	}

	public void insert(User user) {
		try {
			this.hibernateTemplate.save(user);
		} catch (DataAccessException e) {
			logger.error(e.getStackTrace().toString());
		}
	}

	public User query(long userId) {
		User user =(User)this.hibernateTemplate.get(User.class, userId);
		return user;
	}
	@Override
	public User queryByEmail(String email) {

//		 String queryString = "select count(*) from bean.User u where u.name=:myName and u.password=:myPassword"; 
//		  String[] paramName= new String[]{"myName", "myPassword"};
//		  String[] value= new String[]{"xiyue", "123"};
//		  this.getHibernateTemplate().findByNamedParam(queryString, paramName, value);
		String hql = "from User user where user.email=:email";
		List list =  this.hibernateTemplate.findByNamedParam(hql, "email", email);
		User user ;
		if(list.size()== 0 ){
			return null;
		}else{
			user = (User) list.get(0);
			return user;
		}
	}

	@Override
	public User queryByNick(String nickname) {
		String hql = "from User user where user.nickname=\'" + nickname + "\'";
		User user;
		if(this.hibernateTemplate.find(hql).size()== 0 ){
			return null;
		}else{
			user = (User) this.hibernateTemplate.find(hql).get(0);
			return user;
		}
	}
	public List<User> query() {
		String hql = "from User user";
		List<User> users = this.hibernateTemplate.find(hql);
		return users;
	}

	public void update(User user) {
		this.hibernateTemplate.update(user);
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<User> queryUnsignUser() {
		String hql = "from User user where user.salt='false'";
		return this.hibernateTemplate.find(hql);
	}

	@Override
	public List<User> findRandomUser() {
		List users = this.hibernateTemplate.executeFind(new HibernateCallback() { 
			public Object doInHibernate(Session session) 
			throws HibernateException, SQLException { 
				//TODO 这里可能有bug，需要主动关闭连接池。
			List list1 = new LinkedList();
			for (int i = 0; i < 9; i++) {
				String sql = "SELECT * FROM bbs_user AS t1 JOIN (SELECT ROUND(RAND() * ((SELECT MAX(uid) FROM bbs_user)-(SELECT MIN(uid) FROM bbs_user))+(SELECT MIN(uid) FROM bbs_user)) AS id) AS t2 WHERE t1.uid >= t2.id ORDER BY t1.uid LIMIT 1; ";
				SQLQuery query = session.createSQLQuery(sql).addEntity(User.class); 
				List list = query.list(); 
				if(list!=null){
					if(list.size()!=0){
						//这里可能有问题，可能会取到空值么？
						list1.add(list.get(0));
					}
				}
			}
			return list1; 
			} }); 
			return users;
	}

}
