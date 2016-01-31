package com.thinkgem.jeesite.modules.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.account.dao.TradeOrderDao;
import com.thinkgem.jeesite.modules.rpc.TradeOrder;

@Service
@Transactional(readOnly = true)
public class TradeOrderService extends BaseService {
	
	@Autowired
	private TradeOrderDao tradeOrderDao;
	
	public TradeOrder get(String id){
		return tradeOrderDao.get(id);
	}
	
	@Transactional(readOnly = false)
	public void save(TradeOrder order){
		tradeOrderDao.save(order);
	}
	
	public Page<TradeOrder> find(Page<TradeOrder> page,TradeOrder order){
		return tradeOrderDao.find(page,order);
	}
	
	
}
