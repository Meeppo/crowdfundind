package com.thinkgem.jeesite.modules.rpc;

import java.util.Map;

/**
 * 远程订单和本地订单的转换桥梁，用于调用远程接口时传递数据 
 *
 */
public class TradeIntent{
	
	public TradeIntent() {
		super();
	}

	public TradeIntent(TradeOrder tradeOrder) {
		super();
		this.tradeOrder = tradeOrder;
	}

	private TradeOrder tradeOrder;
	
	private Map<String, Object> extralBundle;
	
	/**
	 * 本地业务订单，远程订单需保存其主键，用于回调时的反序列化操作
	 * @return
	 */
	public TradeOrder getTradeOrder() {
		return tradeOrder;
	}
	
	public void setTradeOrder(TradeOrder tradeOrder) {
		this.tradeOrder = tradeOrder;
	}
	
	public Map<String, Object> getExtralBundle() {
		return extralBundle;
	}

	public void setExtralBundle(Map<String, Object> extralBundle) {
		this.extralBundle = extralBundle;
	}

	
}
