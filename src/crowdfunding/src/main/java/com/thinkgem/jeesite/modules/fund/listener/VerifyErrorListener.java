package com.thinkgem.jeesite.modules.fund.listener;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.service.ProjectService;

public class VerifyErrorListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		try {
			ProjectService projectService = SpringContextHolder.getBean(ProjectService.class);

			String projectID = execution.getProcessBusinessKey();
			
			//修改状态,后面需优化为statusManager处理
			FundProject project = projectService.get(projectID);
			project.setStatus(FundProject.PRO_CREATE);
		} catch (Exception e) {
			//此处失败如何处理？
			throw new BpmnError("errorCancel");
		}
	}

}
