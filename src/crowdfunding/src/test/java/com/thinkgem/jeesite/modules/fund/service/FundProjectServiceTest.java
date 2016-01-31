package com.thinkgem.jeesite.modules.fund.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.test.SpringTransactionalContextTests;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.service.ProjectProcessService;

public class FundProjectServiceTest extends SpringTransactionalContextTests{
	
	@Autowired
	private ProjectProcessService service;
	
	@Test
	public void createProject(){
		
		FundProject project = new FundProject();
		service.createOrUpdateProject(project);
	}
	
	@Test
	public void verify(){
		
	}
	/**
	 * 启动项目
	 */
	@Test
	public void startProject(){
		
		
	}
	
	
	/**
	 * 项目过期
	 * 需流程定时器执行
	 */
	@Test
	public void expired(){
		
	}
	/**
	 * 退款
	 * @param projectID
	 */
	@Test
	public void refund(){
		
	}
	/**
	 * 项目失败
	 * @param projectID
	 */
	@Test
	public void failing(){
		
	}
	
	
}
