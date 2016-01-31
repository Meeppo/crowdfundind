package com.thinkgem.jeesite.test;

public class BusinessProxy implements BusinessInterface {
	
	private BusinessInterface businessInterface;	
	
	public BusinessProxy(BusinessInterface businessInterface) {
		super();
		this.businessInterface = businessInterface;
	}


	public Object invoke(Object obj){
		return businessInterface.invoke(obj);
	}
}
