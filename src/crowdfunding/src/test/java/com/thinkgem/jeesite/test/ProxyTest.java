package com.thinkgem.jeesite.test;

public class ProxyTest {
	
	public static void staticProxy(){
		
		BusinessInterface bu = new BusinessImpl();
		
		new BusinessProxy(bu).invoke(new Object());
		
	}
	
	public static void dynamicProxy(){
		
		BusinessInterface bu = new BusinessImpl();
		
		BusinessInterface proxy = (BusinessInterface) new DynamicProxy().newInstanceProxy(bu);
		
		proxy.invoke(new Object());
	}
	
	public static void main(String[] args) {
		
		staticProxy();
		
		dynamicProxy();
		
	}
}
