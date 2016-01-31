package com.thinkgem.jeesite.modules.account.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.fund.service.ProjectProcessService;
import com.thinkgem.jeesite.modules.rpc.TradeIntent;
import com.thinkgem.jeesite.modules.rpc.TradeOrder;
@Entity
@Table(name="support_trade")
public class SupportTrade extends TradeOrder {
	
	
	@Override
	public void onCallBack(TradeIntent intent) {
		
		ProjectProcessService projectProcessService = SpringContextHolder.getBean(ProjectProcessService.class);
		//反序列化formData
		ProjectSupport support =JSON.parseObject(intent.getTradeOrder().getFormData(),ProjectSupport.class);
		//添加支持
		projectProcessService.successSupport(support);
		  
	}

}
