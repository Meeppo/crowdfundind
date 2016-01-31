package com.thinkgem.jeesite.modules.fund.reservation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.fund.reservation.service.ReservationService;
import com.thinkgem.jeesite.modules.fund.service.ProjectProcessService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/fund/reservation")
public class ReservertionController extends BaseController {
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ProjectProcessService projectProcessService;
	
	@RequestMapping("make")
	@ResponseBody
	public String makeReservation(String id,Integer supportNum){
		
		projectProcessService.makeReservation(id, supportNum, UserUtils.getUser().getId());
		
		return "ok";
	}
}
