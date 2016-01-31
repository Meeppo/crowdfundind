package com.thinkgem.jeesite.modules.fund.listener;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.service.ProjectService;

/**
 * 初次提交的项目校验失败监听器
 * 负责处理失败后的操作
 * @author Administrator
 *
 */
public class UpdateProjectListener implements TaskListener  {

	private static final long serialVersionUID = 9058139622694577843L;
	

	@Override
	public void notify(DelegateTask delegateTask) {
		
		System.out.println("hehe holy shit");
		
	}


}
