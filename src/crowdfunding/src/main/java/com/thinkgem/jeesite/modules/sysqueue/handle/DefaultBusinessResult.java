package com.thinkgem.jeesite.modules.sysqueue.handle;

public class DefaultBusinessResult implements BusinessResult {
	
	private Object result;
	
	public DefaultBusinessResult() {
		super();
	}
	
	
	public DefaultBusinessResult(Object result) {
		super();
		this.result = result;
	}



	@Override
	public void setResult(Object result) {
		this.result = result;

	}

	@Override
	public Object getResult() {
		return this.result;
	}

}
