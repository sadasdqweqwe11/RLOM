package com.raylinetech.ssh2.logistics.common.service.impl;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.dao.LogisticsDao;
import com.raylinetech.ssh2.logistics.common.dao.RLOrderDao;
import com.raylinetech.ssh2.logistics.common.dao.SkuDao;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.service.SearchService;

public class SearchServiceImpl implements SearchService{

	private RLOrderDao rlOrderDao;
	
	private LogisticsDao logisticsDao;
	
	private SkuDao skuDao;

	public RLOrderDao getRlOrderDao() {
		return rlOrderDao;
	}

	public void setRlOrderDao(RLOrderDao rlOrderDao) {
		this.rlOrderDao = rlOrderDao;
	}

	public LogisticsDao getLogisticsDao() {
		return logisticsDao;
	}

	public void setLogisticsDao(LogisticsDao logisticsDao) {
		this.logisticsDao = logisticsDao;
	}

	public SkuDao getSkuDao() {
		return skuDao;
	}

	public void setSkuDao(SkuDao skuDao) {
		this.skuDao = skuDao;
	}

	@Override
	public List<RLOrder> searchRlOrders(String beginDate, String endDate,
			String func, String code) {
		if(null == func|| "".equals(func.trim())){
			return null;
		}
		if(func.equals(SEARCH_DATE)){
			return this.rlOrderDao.findByDate(beginDate, endDate);
		}else if(func.equals(SEARCH_LOGISTICSID)){
			return this.rlOrderDao.findByDateLogistics(beginDate, endDate, Integer.parseInt(code));
		}else if(func.equals(SEARCH_LOGISTICS_ACCOUNT)){
			return this.rlOrderDao.findByDateLogisticsAccount(beginDate, endDate, code);
		}else if(func.equals(SEARCH_LOGISTICS_ADDRESS)){
			return this.rlOrderDao.findByDateLogisticsAddress(beginDate, endDate, code);
		}else if(func.equals(SEARCH_ACCOUNT)){
			return this.rlOrderDao.findByDateAccount(beginDate, endDate, code);
		}
		return null;
	}

	@Override
	public List getSearchFunc(String func,String beginDate, String endDate) {
		if(null == func|| "".equals(func.trim())){
			return null;
		}
		if(func.equals(SEARCH_DATE)){
			return null;
		}else if(func.equals(SEARCH_LOGISTICSID)){
			return this.logisticsDao.getIdName();
		}else if(func.equals(SEARCH_LOGISTICS_ACCOUNT)){
			return this.logisticsDao.getIdAccount();
		}else if(func.equals(SEARCH_LOGISTICS_ADDRESS)){
			return this.logisticsDao.getIdAddress();
		}else if(func.equals(SEARCH_ACCOUNT)){
			return this.rlOrderDao.getAccountsByDate(beginDate, endDate);
		}
		return null;
	}

	@Override
	public List<Logistics> getAllLogistics() {
		return this.logisticsDao.getAll();
	}
	
	
}
