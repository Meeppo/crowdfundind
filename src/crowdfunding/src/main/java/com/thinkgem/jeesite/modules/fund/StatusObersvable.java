package com.thinkgem.jeesite.modules.fund;

public interface StatusObersvable {
	
	public void changeStatus(String tarStatus);
	
	public abstract void setStatus(String status);
	
	public abstract String getStatus();
}
