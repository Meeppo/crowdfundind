package com.thinkgem.jeesite.modules.fund.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.service.ProjectService;
/**
 * 超时定时器初始化操作
 * @author Administrator
 *
 */
public class OnExpireCreateListener implements ExecutionListener {
	

	private static final long serialVersionUID = 1L;
	private ProjectService projectService = SpringContextHolder.getBean(ProjectService.class);
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		System.out.println(execution.getCurrentActivityName() + ":" + execution.getEventName());;
		String projectID = execution.getProcessBusinessKey();
		
		FundProject project = projectService.get(projectID);
		
		//初始化执行时间
		execution.setVariable("duration", getISO8601(project.getDuration()));
		
		
	}
	
	
	private String getISO8601(Object duration){
		return "PT" + duration.toString() + "M";
	}
}
