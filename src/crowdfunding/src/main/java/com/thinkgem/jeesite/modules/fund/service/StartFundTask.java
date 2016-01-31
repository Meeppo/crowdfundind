package com.thinkgem.jeesite.modules.fund.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;

/**
 * 启动项目任务
 * @author Administrator
 *
 */
public class StartFundTask implements JavaDelegate {
	
	private ProjectProcessService processService = SpringContextHolder.getBean(ProjectProcessService.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
			processService.startProject(execution.getProcessBusinessKey());
	}

}
