package com.thinkgem.jeesite.modules.rpc;

/**
 * 远程业务订单构建工厂，用于构建远程业务订单
 *
 */
public abstract class RpcOrderFactory {
	
	public abstract RpcOrder create(TradeIntent intent);
	
}
