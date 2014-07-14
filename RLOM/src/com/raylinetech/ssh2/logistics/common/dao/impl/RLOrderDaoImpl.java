package com.raylinetech.ssh2.logistics.common.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.raylinetech.ssh2.logistics.common.dao.RLOrderDao;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;

public class RLOrderDaoImpl implements RLOrderDao{

	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Override
	public void save(RLOrder rlOrder) {
		this.hibernateTemplate.save(rlOrder);
	}

	@Override
	public void saveorUpdateAll(List<RLOrder> rlOrders) {
		this.hibernateTemplate.saveOrUpdateAll(rlOrders);
	}

	@Override
	public void update(RLOrder rlOrder) {
		this.hibernateTemplate.update(rlOrder);
	}

	@Override
	public void delete(RLOrder rlOrder) {
		this.hibernateTemplate.delete(rlOrder);
	}


	@Override
	public List<RLOrder> findByDate(String date) {
		String hql = "from RLOrder order where order.date=:date";
		List list =  this.hibernateTemplate.findByNamedParam(hql, "date", date);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}		
	}
	@Override
	public List<RLOrder> findByFile(long id) {
		String hql = "from RLOrder order where order.fileid=:fileid  order by order.splitstatus desc";
		try {
			List list =  this.hibernateTemplate.findByNamedParam(hql, "fileid", id);
			if(list.size()== 0 ){
				return null;
			}else{
				return list;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}	
	}
	@Override
	public void deleteAll(List<RLOrder> orders) {
		this.hibernateTemplate.deleteAll(orders);
	}
	@Override
	public List<RLOrder> findByDateLogistics(String beginDate, String endDate,
			int logisticsid) {
		String hql = "from RLOrder order where order.date>=:beginDate and order.date<=:endDate and order.logistics.id=:logisticsid  order by order.logistics.id";
		String[] name = {"beginDate","endDate","logisticsid"};
		Object[] password = {beginDate, endDate, logisticsid};
		List list =  this.hibernateTemplate.findByNamedParam(hql, name, password);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}		
	}
	@Override
	public List<RLOrder> findByIds(List<Long> orderIds) {
		String hql = "from RLOrder order where order.id in (:ids) order by order.logistics.id";
		List list =  this.hibernateTemplate.findByNamedParam(hql, "ids", orderIds);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}
	}
	@Override
	public List<RLOrder> findByDateVendor(String beginDate, String endDate,
			String vendor) {
		String hql = "from RLOrder order where order.date>=:beginDate and order.date<=:endDate and order.vendor=:vendor  order by order.logistics.id";
		String[] param = {"beginDate","endDate","vendor"};
		Object[] value = {beginDate, endDate, vendor};
		List list =  this.hibernateTemplate.findByNamedParam(hql, param, value);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}		
	}
	@Override
	public List<RLOrder> findByRLOrdernumbers(List rlOrdernumbers) {
		String hql = "from RLOrder order where order.rlordernumber in (:numbers) order by order.logistics.id";
		List list =  this.hibernateTemplate.findByNamedParam(hql,"numbers", rlOrdernumbers);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}
	}
	@Override
	public List<Object[]> getLogisticsAndCountFromDateVendor(String yyyyMMdd,
			String yyyyMMdd2, String vendor) {
		String hql = "select order.logistics.name,count(*) from RLOrder order where order.date>=:beginDate and order.date<=:endDate and order.vendor=:vendor group by order.logistics.id order by order.logistics.id";
		String[] param = {"beginDate","endDate","vendor"};
		Object[] value = {yyyyMMdd, yyyyMMdd2, vendor};
		List list =  this.hibernateTemplate.findByNamedParam(hql, param, value);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}		
	}
	@Override
	public List<RLOrder> findByDate(String startDate, String endDate) {
		String hql = "from RLOrder order where order.date>=:beginDate and order.date<=:endDate order by order.splitstatus desc, order.logistics.id asc";
		String[] param = {"beginDate","endDate"};
		Object[] value = {startDate, endDate};
		List list =  this.hibernateTemplate.findByNamedParam(hql, param, value);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}			
	}
	@Override
	public List<String> findVendorFromDate(String startDate, String endDate) {
		String hql = "select order.vendor from RLOrder order where order.date>=:beginDate and order.date<=:endDate group by order.vendor order by order.vendor";
		String[] param = {"beginDate","endDate"};
		Object[] value = {startDate, endDate};
		List list =  this.hibernateTemplate.findByNamedParam(hql, param, value);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}	
	}
	@Override
	public List<RLOrder> findByDateLogisticsAccount(String beginDate,
			String endDate, String code) {
		String hql = "from RLOrder order where order.date>=:beginDate and order.date<=:endDate and order.logistics.account=:account order by order.logistics.id";
		String[] name = {"beginDate","endDate","account"};
		Object[] password = {beginDate, endDate, code};
		List list =  this.hibernateTemplate.findByNamedParam(hql, name, password);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}		
	}
	@Override
	public List<RLOrder> findByDateLogisticsAddress(String beginDate,
			String endDate, String code) {
		String hql = "from RLOrder order where order.date>=:beginDate and order.date<=:endDate and order.logistics.address=:address order by order.logistics.id";
		String[] name = {"beginDate","endDate","address"};
		Object[] password = {beginDate, endDate, code};
		List list =  this.hibernateTemplate.findByNamedParam(hql, name, password);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}		
	}
	@Override
	public List<Object[]> findLogisticsAndCountByDate(String startDate,
			String endDate) {
		String hql = "select order.logistics.name,count(*) from RLOrder order where order.date>=:beginDate and order.date<=:endDate  group by order.logistics.id order by order.logistics.id";
		String[] param = {"beginDate","endDate"};
		Object[] value = {startDate, endDate};
		List list =  this.hibernateTemplate.findByNamedParam(hql, param, value);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}	
	}
	@Override
	public List<RLOrder> findByDateAccount(String beginDate, String endDate,
			String code) {
		String hql = "from RLOrder order where order.date>=:beginDate and order.date<=:endDate and order.account=:account order by order.logistics.id";
		String[] params = {"beginDate","endDate","account"};
		Object[] values = {beginDate, endDate, code};
		List list =  this.hibernateTemplate.findByNamedParam(hql, params, values);
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}		
	}
	@Override
	public List getAccountsByDate(String beginDate, String endDate) {
		String hql = "select order.account,order.account from RLOrder order where order.date>=:beginDate and order.date<=:endDate  group by order.account";
		String[] param = {"beginDate","endDate"};
		Object[] value = {beginDate, endDate};
		List list =  this.hibernateTemplate.findByNamedParam(hql, param, value);
		System.out.println(list.size());
		if(list.size()== 0 ){
			return null;
		}else{
			return list;
		}	
	}
	@Override
	public List getVendorVendorByDate(String startDate, String endDate) {
			String hql = "select order.vendor,order.vendor from RLOrder order where order.date>=:beginDate and order.date<=:endDate group by order.vendor order by order.vendor";
			String[] param = {"beginDate","endDate"};
			Object[] value = {startDate, endDate};
			List list =  this.hibernateTemplate.findByNamedParam(hql, param, value);
			if(list.size()== 0 ){
				return null;
			}else{
				return list;
			}	
	}
}
