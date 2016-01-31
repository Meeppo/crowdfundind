package com.thinkgem.jeesite.modules.account.entity;

import javax.persistence.Entity;

import com.thinkgem.jeesite.modules.rpc.TradeIntent;
import com.thinkgem.jeesite.modules.rpc.TradeOrder;
@Entity
public class WithdrawTrade extends TradeOrder {

	@Override
	public void onCallBack(TradeIntent intent) {
		// TODO Auto-generated method stub

	}

}
