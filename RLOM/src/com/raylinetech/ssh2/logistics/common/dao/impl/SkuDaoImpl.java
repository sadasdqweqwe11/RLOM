package com.raylinetech.ssh2.logistics.common.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.raylinetech.ssh2.logistics.common.dao.SkuDao;
import com.raylinetech.ssh2.logistics.common.entity.Sku;

public class SkuDaoImpl implements SkuDao{

	private HibernateTemplate hibernateTemplate;


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<Sku> findBySkuno(List<String> skuno) {
		String hql = "from Sku sku where sku.skuno in (:skunos)";
		List list =   this.hibernateTemplate.findByNamedParam(hql,"skunos", skuno);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}	
	}

	@Override
	public Sku find(String skuno) {
		String hql = "from Sku sku where sku.skuno=:skuno";
		String[] name = {"skuno"};
		Object[] password = {skuno};
		List list =   this.hibernateTemplate.findByNamedParam(hql, name, password);
		if(list.size()== 0 ){
			return null;
		}else{
			return (Sku)list.get(0);
		}	
	}

}
