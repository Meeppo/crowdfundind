package com.thinkgem.jeesite.modules.rpc;

import javax.persistence.Column;

public abstract class RpcOrder {
	
	protected String businessKey;
	
	@Column(name="business_key",nullable=false)
	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	
	
}
