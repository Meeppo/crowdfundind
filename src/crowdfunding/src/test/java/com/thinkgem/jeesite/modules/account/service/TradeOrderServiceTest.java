package com.thinkgem.jeesite.modules.account.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.test.SpringTransactionalContextTests;
import com.thinkgem.jeesite.modules.account.entity.SupportTrade;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.rpc.TradeIntent;
import com.thinkgem.jeesite.modules.rpc.TradeOrder;

public class TradeOrderServiceTest extends SpringTransactionalContextTests {
	
	@Autowired
	private TradeOrderService tradeOrderService;
	
	//@Test
	public void save(){
		
		TradeOrder  order = new SupportTrade();
		tradeOrderService.save(order);
	}
	
	//@Test
	public void get(){
		TradeOrder order = tradeOrderService.get("0a80ac3b37ed41498bb3b1784024ff2c");
		System.out.println(order instanceof SupportTrade);
	}
	@Test
	public void find(){
		Page<TradeOrder> list = tradeOrderService.find(new Page<TradeOrder>(), new TradeOrder(){

			@Override
			public void onCallBack(TradeIntent intent) {
			}});
		
		for(TradeOrder t : list.getList()){
			System.out.println(t.getId() + (t.getClass().getName()));
		}
	}
}
