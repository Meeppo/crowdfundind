package com.thinkgem.jeesite.modules.fund.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.workflow.WorkflowUtils;
import com.thinkgem.jeesite.modules.fund.FundFullChecker;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.fund.exception.FundRuntimeException;
import com.thinkgem.jeesite.modules.fund.exception.NoTaskInRuntimeException;
import com.thinkgem.jeesite.modules.fund.reservation.entity.Reservation;
import com.thinkgem.jeesite.modules.fund.reservation.service.ReservationService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Service
@Transactional(readOnly = false)
public class ProjectProcessService {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private ReservationService reservationService;
	/**
	 * 获取当前用户所有未完成任务
	 * @param page
	 * @param project
	 * @return
	 */
	public Page<FundProject> findTodoProjects(Page<FundProject> page, FundProject project,String taskName) {

		User user = UserUtils.getUser();
		
		List<FundProject> list = projectService.find(page, project).getList();
		List<FundProject> result = Lists.newArrayList();
		//过滤出当前用户的任务
		if(list.size()>0) {
			List<Task> tasks =Lists.newArrayList();
			TaskQuery todoQuery = taskService.createTaskQuery().processDefinitionKey(FundProject.PROCESS_DEFINITION_KEY).active();
			//过滤任务名称
			if(StringUtils.isNotBlank(taskName)){
				todoQuery = todoQuery.taskName(taskName);
			}
			//当前用户的任务列表
			List<Task> todoList = todoQuery.taskAssignee(ObjectUtils.toString(user.getId())).list();
			//当前为被认领的任务列表
			List<Task> unsignedTasks = taskService.createTaskQuery().processDefinitionKey(FundProject.PROCESS_DEFINITION_KEY).active().taskCandidateUser(ObjectUtils.toString(user.getId())).list();
			tasks.addAll(todoList);
			tasks.addAll(unsignedTasks);
			Set<String> processInstanceIds = Sets.newHashSet();
			for (Task task : tasks) {
				processInstanceIds.add(task.getProcessInstanceId());
			}
			for(FundProject p:list) {
				if(processInstanceIds.contains(p.getFundProcessInstId())) {
					result.add(p);
				}
			}
		}
		page.setCount(result.size());
		page.setList(result.subList(page.getFirstResult(),page.getLastResult()));
		return page;
	}
	
	/**
	 * 执行修改项目用户任务
	 * @param project
	 */
	private void updateProject(FundProject project){
		Task task = taskService.createTaskQuery().processInstanceId(project.getFundProcessInstId()).singleResult();
		taskService.complete(task.getId());
	}
	
	/**
	 * 创建修改项目, 启动项目流程
	 * @param project
	 */
	@Transactional(readOnly = false)
	public void createOrUpdateProject(FundProject project){
		
		//保存项目信息
		projectService.save(project);
		
		//启动项目流程
		String businessKey = project.getId().toString();
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey, FundProject.PROCESS_DEFINITION_KEY).singleResult(); ;
		
		if(processInstance == null){
			try {
				identityService.setAuthenticatedUserId(ObjectUtils.toString(project.getCreateBy().getId()));
				processInstance = runtimeService.startProcessInstanceByKey(FundProject.PROCESS_DEFINITION_KEY, businessKey);
			} finally {
				identityService.setAuthenticatedUserId(null);
			}
		}
		//修改项目,并执行用户任务
		else{
			updateProject(project);
		}
		
		String processInstanceId = processInstance.getId();
		project.setFundProcessInstId(processInstanceId);
		project.setStatus(FundProject.PRO_VERIFY);
		projectService.save(project);
	}
	
	/**
	 * 项目审核
	 * @param project
	 */
	public void verify(FundProject project,Boolean isPass){
		
		WorkflowUtils.claim(project.getFundProcessInstId());
		Task task = taskService.createTaskQuery().processInstanceId(project.getFundProcessInstId()).singleResult();
		//添加批注
		if(StringUtils.isNotBlank(project.getVerifyRemarks()))
			taskService.addComment(task.getId(), project.getFundProcessInstId(),project.getVerifyRemarks());
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("isPass", isPass);
		map.put("reservation", project.getReservation());
		//完成任务
		taskService.complete(task.getId(),map);
	
	}
	
	
	
	/**
	 * 启动预约
	 * @param project
	 */
	public void startReservation(String projectID){
		//change project status
		FundProject project = projectService.get(projectID);
		project.setStatus(FundProject.PRO_RESV_START);
		
		//initial reservation
		Reservation reservation = new Reservation();
		reservation.setProject(project);
		reservationService.save(reservation);
	}
	
	/**
	 * 预约
	 * @param project
	 */
	public void makeReservation(String reservationID, Integer supportNum ,String memberID){
		
		
		//添加支持
		Reservation reservation = reservationService.addSupport(reservationID, memberID, supportNum);
		
		//满标
		if(reservation.isStuff()){
			Map<String,Object> var = new HashMap<>(); 
			Execution execution = runtimeService.createExecutionQuery().processInstanceBusinessKey(reservation.getProject().getId()).signalEventSubscriptionName("full_reservation").singleResult();
			runtimeService.signalEventReceived("reveration_full",execution.getId(), var);
			
		}
	}
	
	/**
	 * 预约标审核
	 * @param reservation
	 * @param isPass
	 */
	public void auditReservation(Reservation reservation,boolean isPass){
		
		Task task = WorkflowUtils.claim(reservation.getProject().getFundProcessInstId());
		if(task == null)
			throw new NoTaskInRuntimeException("任务不存在");
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("isPass", isPass);
		
		//完成任务
		taskService.complete(task.getId(),map);
		
	}
	
	/**
	 * 启动项目
	 */
	public void startProject(String businessKey){
		
		//修改标的状态为“启动”
		FundProject project = projectService.get(businessKey);
		project.setStatus(FundProject.PRO_START);
		projectService.save(project);
		
		
	}
	
	/**
	 * 投资/支持项目
	 * @param projectID
	 */
	public void successSupport(ProjectSupport support){
		
		projectService.successSupport(support);
		
		//若满标
		if(FundFullChecker.instance().isFull(support.getProject())){
			Map<String,Object> var = new HashMap<>(); 
			Execution execution = runtimeService.createExecutionQuery().processInstanceBusinessKey(support.getProject().getId()).signalEventSubscriptionName("full_project").singleResult();
			runtimeService.signalEventReceived("full_project",execution.getId(), var);
		}
	}
	
	/**
	 * 项目过期
	 * 需流程定时器执行
	 */
	public void expired(){
		
	}
	
	/**
	 * 项目投满审核
	 * @param project
	 * @param isPass
	 */
	public void auditFull(FundProject project,boolean isPass){
		
		
		Task task = WorkflowUtils.claim(project.getFundProcessInstId());
		if(task == null)
			throw new NoTaskInRuntimeException("任务不存在");
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("isPass", isPass);
		
		//完成任务
		taskService.complete(task.getId(),map);
		
	}
	
	public void createRewards(){
		
	}
	
	
	/**
	 * 退款
	 * @param projectID
	 */
	void refund(String projectID){
		
	}
	/**
	 * 项目失败
	 * @param projectID
	 */
	public void failing(String projectID){
		
	}
	
	
}
