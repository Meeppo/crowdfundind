package com.thinkgem.jeesite.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
	
	public static void main(String[] args) throws Exception {
		ExecutorService excutor = Executors.newCachedThreadPool();
		
		String res = excutor.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				
				return "aa";
			}
			
		}).get();
		
		System.out.println("run:"+res);
	}
}
