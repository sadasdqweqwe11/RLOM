package com.raylinetech.ssh2.logistics.common.dao.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.raylinetech.ssh2.logistics.common.dao.OrderFileDao;
import com.raylinetech.ssh2.logistics.common.dao.TrackingNoDao;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.TrackingNo;

public class TrackingNoDaoImpl implements TrackingNoDao{

	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public void save(TrackingNo trackingNo) {
		this.hibernateTemplate.save(trackingNo);
	}

	@Override
	public void update(TrackingNo trackingNo) {
		this.hibernateTemplate.update(trackingNo);
	}

	
	
	@Override
	public void delete(TrackingNo trackingNo) {
		this.hibernateTemplate.delete(trackingNo);		
	}
	@Override
	public List<TrackingNo> getTrackingNo(final int count,final int logisticsid) {

		List trackingNos = this.hibernateTemplate.executeFind(new HibernateCallback() { 
			public Object doInHibernate(Session session) 
			throws HibernateException, SQLException { 
				String hql = "from TrackingNo no where no.isused=0 and no.logisticsid="+logisticsid;
				Query query = session.createQuery(hql); 
				query.setFirstResult(0); //从0开始
				query.setMaxResults(count); 
				List list = query.list(); 
				return list; 
			} }); 
		return trackingNos;
	}
	
	@Override
	public void updateAll(List<TrackingNo> trackingNos) {
		this.hibernateTemplate.saveOrUpdateAll(trackingNos);
	}
	
	@Override
	public List<TrackingNo> getTrackingNoByRLOrderIds(final List<Long> oids) {
//		List trackingNos = this.hibernateTemplate.executeFind(new HibernateCallback() { 
//			public Object doInHibernate(Session session) 
//			throws HibernateException, SQLException { 
//				String hql = "from TrackingNo no where no.rlorderid in (:oids)";
//				Query query = session.createQuery(hql); 
//				query.setParameter("oids", oids);
//				List list = query.list(); 
//				return list; 
//			} }); 
//		return trackingNos;
		String hql = "from TrackingNo no where no.rlorderid in (:oids)";
		List list =  this.hibernateTemplate.findByNamedParam(hql,"oids", oids);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}
	}
	public static void main(String[] args) {
		List list = new LinkedList();
		String in = list.toString();
		System.out.println(in.substring(in.indexOf("[")+1, in.indexOf("]")));
		
	}

}
