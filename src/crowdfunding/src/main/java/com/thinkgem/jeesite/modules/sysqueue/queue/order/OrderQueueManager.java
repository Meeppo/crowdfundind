package com.thinkgem.jeesite.modules.sysqueue.queue.order;

import java.util.List;

import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.sysqueue.queue.Queue;
import com.thinkgem.jeesite.modules.sysqueue.queue.QueueManager;

public class OrderQueueManager implements QueueManager<ProjectSupport> {

	@Override
	public Queue get(String queueID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(String queueID, ProjectSupport t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProjectSupport pop(String queueID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectSupport remove(String queueID, ProjectSupport t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectSupport> remove(String queueID, List<ProjectSupport> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destory(String queueID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Queue create(String queueID) {
		// TODO Auto-generated method stub
		return null;
	}

}
