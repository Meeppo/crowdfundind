package com.thinkgem.jeesite.modules.sysqueue.queue;

import java.util.List;
/**
 * 队列管理器，用于负责队列的添加删除操作
 *
 * @param <T>
 */
public interface QueueManager<T> {
	
	public Queue get(String queueID);
	
	public void push(String queueID,T t);
	
	public T pop(String queueID);
	
	public T remove(String queueID,T t);
	
	public List<T> remove(String queueID,List<T> t);
	
	public void destory(String queueID);
	
	public Queue create(String queueID);
}
