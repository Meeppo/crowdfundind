package com.thinkgem.jeesite.modules.fund.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.fund.service.AddSupportRunner;
import com.thinkgem.jeesite.modules.fund.service.ProjectProcessService;
import com.thinkgem.jeesite.modules.fund.service.ProjectService;
import com.thinkgem.jeesite.modules.sysqueue.QueueSystem;
import com.thinkgem.jeesite.modules.sysqueue.exception.QueueRejectException;
import com.thinkgem.jeesite.modules.sysqueue.handle.BusinessResult;
import com.thinkgem.jeesite.modules.sysqueue.queue.order.OrderQueueManager;

/**
 * 项目controller,负责前台的项目项目相关的操作。
 * 
 *
 */
@Controller
@RequestMapping("${frontPath}/project")
public class FundProjectFrontController extends BaseController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectProcessService projectProcessService;
	
	@Autowired
	private QueueSystem queueSystem;
	
	@Autowired
	private AddSupportRunner supportRunner;
	
	@Autowired
	private OrderQueueManager queueManager;
	
	@ModelAttribute
	public FundProject injectEntity(String id){
		FundProject project = null;
		if(StringUtils.isNotBlank(id)){
			project = projectService.get(id);
		}else{
			project = new FundProject();
		}
		return project;
	}
	/**
	 * 创建修改
	 * @param project
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.GET)
	public String toSave( FundProject project,Model model){
		model.addAttribute("project", project);
		return "/modules/project/front/createProject";
	}
	/**
	 * 创建或修改并保存草稿
	 * @param project
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@ModelAttribute("project") FundProject project,Model model){
		
		projectService.save(project);
		addMessage(model, "保存成功");
		return "/modules/project/front/createProject";
	}
	
	/**
	 * 创建并提交项目
	 * @param project
	 * @param model
	 * @return
	 */
	@RequestMapping("/submit")
	public String submit(FundProject project,Model model){
		
		if(!this.beanValidator(model, project)){
			model.addAttribute("project", project);
			return "modules/project/front/createProject";
		}
		try {
			projectProcessService.createOrUpdateProject(project);
			addMessage(model, "创建成功");
		} catch (Exception e) {
			model.addAttribute("project", "创建失败");
		}
		
		return "redirect:" + Global.getFrontPath() + "/project/myList";
	}
	
	@RequestMapping("myList")
	public String myList(FundProject project, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<FundProject> page = projectService.find(new Page<FundProject>(request, response), project); 
        model.addAttribute("page", page);
		model.addAttribute("project", project);
		return "modules/project/front/myProjectList";
	}

	@RequestMapping("support")
	public String support(ProjectSupport support,Model model){
		
		try {
			BusinessResult result = queueSystem.request(supportRunner,support.getProject().getId(),support,queueManager);
			return result.getResult().toString();
		} catch (QueueRejectException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
