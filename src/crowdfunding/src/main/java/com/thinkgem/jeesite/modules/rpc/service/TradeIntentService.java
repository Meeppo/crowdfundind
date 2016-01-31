package com.thinkgem.jeesite.modules.rpc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.account.service.TradeOrderService;
import com.thinkgem.jeesite.modules.rpc.TradeIntent;
import com.thinkgem.jeesite.modules.rpc.dao.TradeIntentDao;

@Service
@Transactional( readOnly = true)
public class TradeIntentService extends BaseService {
	
	@Autowired
	private TradeOrderService orderService;
	
	
	
	public TradeIntent get(String ID){
		
		return null;
	}
}
