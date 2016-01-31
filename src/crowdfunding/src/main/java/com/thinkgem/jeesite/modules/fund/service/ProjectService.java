package com.thinkgem.jeesite.modules.fund.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.fund.dao.FundingProjectDao;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.fund.reward.entity.Reward;
import com.thinkgem.jeesite.modules.fund.reward.service.RewardListService;
import com.thinkgem.jeesite.modules.fund.reward.service.RewardService;

@Service("FundProjectService")
@Transactional(readOnly = true)
public class ProjectService extends BaseService {

	@Autowired
	private FundingProjectDao projectDao;
	@Autowired
	private ProjectSupportService projectSupportService;
	@Autowired
	private RewardService rewardService;
	@Autowired
	private RewardListService rewardListService;
	
	
	public FundProject get(String id){
		return projectDao.get(id);
	}
	protected FundProject get(FundProject project){
		if(project == null)
			throw new IllegalArgumentException("The param of the method 'get(FundProject)' cant' be null!");
		return projectDao.get(project);
	}
	
	
	public Page<FundProject> find(Page<FundProject> page,FundProject project){
		return projectDao.find(page,project);
	}
	@Transactional(readOnly = false)
	public void save(FundProject project) {
		projectDao.save(project);
	}
	/**
	 * 保存或修改项目，不包含上传文件，包含关联表信息修改或添加
	 * @param project
	 */
	@Transactional(readOnly = false)
	public void saveDeep(FundProject project) {
		
		//保存或修改创建人信息
		
		//保存或修改项目银行卡信息
		
		//创建或修改项目基本信息
		projectDao.save(project);
	}
	
	/**
	 * 添加项目投资，初始订单状态为投资中,
	 * 投资中一直未付款的订单在超时后会自动转为投资失败
	 * @param support
	 */
	@Transactional(readOnly = false)
	public void addSupport(ProjectSupport support){
		//设置注释订单状态为：投资中
		support.setStatus(ProjectSupport.SUPPORTING);
		//保存订单
		projectSupportService.save(support);
		
	}
	
	/**
	 * 用户投资项目成功，更新投资记录状态
	 * @param project
	 * @param support
	 */
	@Transactional(readOnly = false)
	public void successSupport(ProjectSupport support){
		
		//保存
		support.changeStatus(ProjectSupport.SUPPORT_SUCCESS);
		projectSupportService.save(support);
	}
	
	/**
	 * 根据项目id创建项目回报清单（发货单）
	 * @param projectID
	 */
	@Transactional
	public void createRewards(String projectID){
		
		//获取回报类型列表
		List<Reward> rewards = rewardService.getListByProject(projectID);
		
		//创建各类型回报的清单
		for (Reward reward : rewards) {
			
			//获取该类型的所有订单
			List<ProjectSupport> supports = projectSupportService.getSupports(projectID, reward.getId());
			
			//创建发货单
			rewardListService.createListOfReward(supports, reward);
			
		}
		
	}
	
}
