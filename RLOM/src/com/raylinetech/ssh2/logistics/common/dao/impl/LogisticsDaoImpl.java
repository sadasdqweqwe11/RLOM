package com.raylinetech.ssh2.logistics.common.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.raylinetech.ssh2.logistics.common.dao.LogisticsDao;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;

public class LogisticsDaoImpl implements LogisticsDao{

	private HibernateTemplate hibernateTemplate;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public List<Logistics> getAll() {
		String hql = "from Logistics logi";
		return this.hibernateTemplate.find(hql);
	}
	@Override
	public Logistics find(int logisticsid) {
		return (Logistics)this.hibernateTemplate.get(Logistics.class,logisticsid);
	}
	@Override
	public List getIdName() {
		String hql = "select logi.id, logi.name from Logistics logi order by logi.id";
		return this.hibernateTemplate.find(hql);
	}
	@Override
	public List getIdAccount() {
		String hql = "select logi.account, logi.account from Logistics logi where logi.id != 999 group by logi.account order by logi.id";
		return this.hibernateTemplate.find(hql);
	}
	@Override
	public List getIdAddress() {
		String hql = "select logi.address, logi.address from Logistics logi where logi.id != 999 group by logi.address order by logi.id";
		return this.hibernateTemplate.find(hql);	}

}

