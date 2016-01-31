package com.thinkgem.jeesite.modules.sysqueue;

import java.util.concurrent.Callable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.sysqueue.handle.BusinessInterface;
import com.thinkgem.jeesite.modules.sysqueue.handle.BusinessProxy;
import com.thinkgem.jeesite.modules.sysqueue.handle.BusinessResult;
import com.thinkgem.jeesite.modules.sysqueue.handle.DefaultBusinessResult;

/**
 * 排队系统的核心调度器，负责任务的调度，结果的返回
 *
 */
@Service
@Scope("prototype")
public class Executor implements Callable<BusinessResult> {
	
	private BusinessInterface businessInterface;
	
	public Executor(boolean inQueue, BusinessInterface businessInterface,Object param) {
		super();
		this.inQueue = inQueue;
		this.businessInterface = businessInterface;
		this.param = param;
	}
	
	private boolean inQueue;
	
	private Object param;
	
	public Object getParam() {
		return param;
	}


	protected BusinessProxy applyProxy(BusinessInterface businessInterface){
		
		return new BusinessProxy(businessInterface);
		
	};
	
	
	@Override
	@Transactional(readOnly = false)
	public BusinessResult call() throws Exception {
		
		Object object = applyProxy(businessInterface).invoke(this.param);
		
		BusinessResult result = new DefaultBusinessResult(object);
		
		//排队执行需将结果放入仓库
		if(inQueue){
			
		}
		
		return result;
	}

}
