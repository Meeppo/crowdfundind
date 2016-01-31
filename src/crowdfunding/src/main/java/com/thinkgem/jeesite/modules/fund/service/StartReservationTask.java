package com.thinkgem.jeesite.modules.fund.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.reservation.entity.Reservation;
import com.thinkgem.jeesite.modules.fund.reservation.service.ReservationService;

/**
 * 启动预约任务
 * @author Administrator
 *
 */
public class StartReservationTask implements JavaDelegate {
	
	private ProjectProcessService projectProcessService = SpringContextHolder.getBean(ProjectProcessService.class);
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String projectID = execution.getProcessBusinessKey();
		
		projectProcessService.startReservation(projectID);
		
	}

}
