package com.thinkgem.jeesite.modules.sysqueue.queue;

/**
 * 排队系统队列，所有需要排队的成员都保存在当中
 *	
 */
public interface Queue {
	
	public Integer getSize();
	
	public void setSize(Integer size);
	
	public String getFirst();
	
	public void setFirst(String first);

	public String getLast();
	
	public void setLast(String last);
	
}
