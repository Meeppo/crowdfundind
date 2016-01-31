package com.thinkgem.jeesite.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SynchTest implements Runnable {
	
	public static ConcurrentMap<String, Object> lockMap = new ConcurrentHashMap<String, Object>();
	{
		lockMap.put("1", new Object());
		lockMap.put("2", new Object());
	}
	public SynchTest(String id,String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public String id;

	
	public static Integer count = 0;
	
	public String type;
	public void add(){
		count ++;
		System.out.println("add...");
	}
	
	public void sub(){
		count --;
		System.out.println("sub...");
	}
	
	@Override
	public void run() {
		
		synchronized (lockMap.get(id)) {
			while(true){
				if(type.equals("1"))
				add();
				if(type.equals("2"))
				sub();
				System.out.println(id + ":" + count);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	public static void main(String[] args) {
		
		SynchTest test1 = new SynchTest(new String("1"),"1");
		SynchTest test2 = new SynchTest(new String("1"),"2");
		System.out.println(test1.id == test2.id);
		
		new Thread(test1).start();
		new Thread(test2).start();
		
		
	}
}
