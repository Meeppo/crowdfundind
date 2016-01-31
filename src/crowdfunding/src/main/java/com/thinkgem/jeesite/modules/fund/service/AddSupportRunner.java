package com.thinkgem.jeesite.modules.fund.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.rpc.RpcPayService;
import com.thinkgem.jeesite.modules.rpc.exception.RPCException;
import com.thinkgem.jeesite.modules.sysqueue.handle.BusinessInterface;

@Service
public class AddSupportRunner implements BusinessInterface {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private RpcPayService payService;

	@Override
	public Object invoke(Object obj) {
		
		ProjectSupport support = (ProjectSupport) obj;
		
		//添加订单
		projectService.addSupport(support);
		//请求支付
		try {
			return payService.pay();
		} catch (RPCException e) {
			e.printStackTrace();
			return null;
		}
	}



}
