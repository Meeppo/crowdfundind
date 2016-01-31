package com.thinkgem.jeesite.modules.fund.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.service.ProjectProcessService;
import com.thinkgem.jeesite.modules.fund.service.ProjectService;

@Controller
@RequestMapping("${adminPath}/project/project")
public class FundProjectController extends BaseController {
	
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectProcessService projectProcessService;
	
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
	
	@RequestMapping(value = {"list",""})
	public String list(FundProject project, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<FundProject> page = projectService.find(new Page<FundProject>(request, response), project); 
        model.addAttribute("page", page);
		model.addAttribute("project", project);
		return "modules/project/back/list";
	}
	
	/**
	 * 待办任务列表
	 * @param project
	 * @param taskName
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="todoTask")
	public String taskList(@ModelAttribute("project") FundProject project,@ModelAttribute("taskName")String taskName,  HttpServletRequest request, HttpServletResponse response, Model model){
		
		Page<FundProject> page = projectProcessService.findTodoProjects(new Page<FundProject>(request, response),project, taskName);
		model.addAttribute("page", page);
		return "modules/project/back/taskList";
	}
	@RequestMapping(value="toResolve")
	public String toTaskResolve(FundProject project,Model model){
		model.addAttribute("project", project);
		return "modules/project/back/resolve";
	}
	
	/**
	 * 初次提交项目时，后台审核人员校验
	 * @param id
	 * @param isPass
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping(value="verify")
	public String verify(FundProject project,Boolean isPass,Model model,RedirectAttributes attr){
		
		if(!beanValidator(attr, project)){
			model.addAttribute("project", project);
			return "modules/project/back/resolve";
		}
		
		if(isPass == null){
			addMessage(attr,"请输入审核结果");
			return "redirect:" + adminPath + "/project/project/todoTask?repage";
		}
		
		
		if(StringUtils.isNotBlank(project.getId())){
			projectProcessService.verify(project, isPass);
			addMessage(attr, "处理成功");
		}else{
			addMessage(attr, "项目不存在");
		}
		
		return "redirect:" + adminPath + "/project/project/todoTask?repage";
		
	}
	
	public String auditFull(FundProject project,Boolean isPass,Model model ,RedirectAttributes attr){
		
		if(!beanValidator(attr, project)){
			model.addAttribute("project", project);
			return "modules/project/back/resolve";
		}
		
		if(isPass == null){
			addMessage(attr,"请输入审核结果");
			return "redirect:" + adminPath + "/project/project/todoTask?repage";
		}
		
		
		if(StringUtils.isNotBlank(project.getId())){
			projectProcessService.auditFull(project, isPass);
			addMessage(attr, "处理成功");
		}else{
			addMessage(attr, "项目不存在");
		}
		
		return "redirect:" + adminPath + "/project/project/todoTask?repage";
	}
	
}
