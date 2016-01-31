package com.thinkgem.jeesite.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
	
	private Object target;
	
	public  DynamicProxy() {

	}
	
	public DynamicProxy(Object target) {
		super();
		this.target = target;
		
	}
	
	public Object newInstanceProxy(){
		return newInstanceProxy(null);
	}
	
	public Object newInstanceProxy(Object target){
		if(target != null)
			this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		System.out.println("dynamic");
		
		return method.invoke(target, args);
		
	}

}
