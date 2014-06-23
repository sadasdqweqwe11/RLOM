package com.raylinetech.ssh2.logistics.common.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.raylinetech.ssh2.logistics.common.dao.OrderFileDao;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;

public class OrderFileDaoImpl implements OrderFileDao{

	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public void save(OrderFile orderFile) {
		this.hibernateTemplate.save(orderFile);
	}

	@Override
	public void update(OrderFile orderFile) {
		this.hibernateTemplate.update(orderFile);
	}

	@Override
	public void delete(OrderFile orderFile) {
		this.hibernateTemplate.delete(orderFile);
	}
	@Override
	public List<OrderFile> getOrderFilesByUserId(long userId) {
		String hql = "from OrderFile file where file.uid=:uid order by file.id desc";
		List list =  this.hibernateTemplate.findByNamedParam(hql, "uid", userId);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}		
	}
	@Override
	public OrderFile find(long id) {
		return (OrderFile) this.hibernateTemplate.get(OrderFile.class, id);
	}

}
