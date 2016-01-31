package com.thinkgem.jeesite.modules.rpc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.rpc.TradeIntent;
import com.thinkgem.jeesite.modules.rpc.service.TradeIntentService;

/**
 * 三方支付回调入口
 * @author Administrator
 *
 */
@Controller
@RequestMapping("rpc")
public class RpcPayController extends BaseController {
	
	@Autowired
	private TradeIntentService intentService;
	
	@RequestMapping("callBack")
	public String callBack(String intentID){
		
		//unSerialize intent
		TradeIntent intent = intentService.get(intentID);
		
		//execute tradeOrder's callback function
		intent.getTradeOrder().onCallBack(intent);
		
		return "";
	}
	
}
