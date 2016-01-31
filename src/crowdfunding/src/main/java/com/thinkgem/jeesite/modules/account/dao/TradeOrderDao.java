package com.thinkgem.jeesite.modules.account.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.account.entity.SupportTrade;
import com.thinkgem.jeesite.modules.account.entity.WithdrawTrade;
import com.thinkgem.jeesite.modules.rpc.TradeOrder;

@Repository
public class TradeOrderDao extends BaseDao<TradeOrder> {
	
	public Page<TradeOrder> find(Page<TradeOrder> page,TradeOrder order){
		
		Parameter parameter = new Parameter();
		String table = "TradeOrder";
		if(order instanceof SupportTrade){
			table = "SupportTrade";
		}else if(order instanceof WithdrawTrade){
			table = "WithdrawTrade";
		}
		StringBuilder buff = new StringBuilder("from "+ table +" t where t.delFlag = " + TradeOrder.DEL_FLAG_NORMAL);
		
		
		
		return find(page, buff.toString(), parameter);
	}
}
