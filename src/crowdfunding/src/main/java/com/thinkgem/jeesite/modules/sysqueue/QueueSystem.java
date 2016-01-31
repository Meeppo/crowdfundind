package com.thinkgem.jeesite.modules.sysqueue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.sysqueue.exception.QueueRejectException;
import com.thinkgem.jeesite.modules.sysqueue.handle.BusinessInterface;
import com.thinkgem.jeesite.modules.sysqueue.handle.BusinessResult;
import com.thinkgem.jeesite.modules.sysqueue.queue.Queue;
import com.thinkgem.jeesite.modules.sysqueue.queue.QueueManager;

@Service
public class QueueSystem {
	
	@Autowired
	private ThreadPoolTaskExecutor threadPool;
	
	/**
	 * 是否排队
	 */
	private boolean isQueue = false;
	
	/**
	 * 把任务加入队列
	 */
	private Queue pushItem(String queueID,Object param,QueueManager queueManager){
		
		Queue queue = queueManager.get(queueID);
		if(queue == null)
			queue = queueManager.create(queueID);
		queueManager.push(queueID, param);
		
		return queue;
	}
	
	public BusinessResult request(BusinessInterface businessInf,String queueID,Object param,QueueManager queueManager) throws QueueRejectException{
		
		boolean inQueue = false;
		if(isQueue){
			//查询队列，若已存在排队现象，将任务直接丢入队列
			
		}
		
		BusinessResult result = null;
		try {
			result = execute(inQueue, businessInf, param);
			
			if(isQueue){
				//移出队列
				queueManager.remove(queueID, param);
			}
			
			return result;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			//使用排队策略或者直接抛出异常
			if(!isQueue)
				throw new QueueRejectException(e);
			
			
			startQueue(queueID,param,queueManager);
			
		}
		
		return result;
	}
	
	public BusinessResult execute(boolean inQueue,BusinessInterface businessInf,Object param) throws InterruptedException, ExecutionException{
		
		Future<BusinessResult> future = threadPool.submit(createExecutable(inQueue,businessInf,param));

		return future.get();
		
	}
	
	private void startQueue(String queueID,Object param,QueueManager<?> queueManager){
		

		//若是队列过长直接抛弃
		
		//加入队列
		Queue queue = pushItem(queueID,param,queueManager);
				
		//根据队列长度判断策略，异步执行或同步执行
				
		//
	}
	
	
	/**
	 * 创建运行对象
	 * @param callable
	 * @return
	 */
	private Executor createExecutable(boolean inQueue,BusinessInterface businessInf,Object param){
		return new Executor(inQueue,businessInf,param);
	}
}
