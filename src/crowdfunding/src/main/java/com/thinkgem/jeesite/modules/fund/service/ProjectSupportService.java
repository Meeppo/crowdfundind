package com.thinkgem.jeesite.modules.fund.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.fund.dao.ProjectSupportDao;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.fund.reward.entity.LuckyReward;
import com.thinkgem.jeesite.modules.fund.reward.entity.NumalReward;
import com.thinkgem.jeesite.modules.fund.reward.entity.Reward;

@Service
@Transactional(readOnly = true)
public class ProjectSupportService extends BaseService {
	
	@Autowired
	private ProjectSupportDao projectSupportDao;
	
	@Transactional(readOnly = false)
	void save(ProjectSupport support){
		projectSupportDao.save(support);
	}
	
	ProjectSupport get(String id){
		return projectSupportDao.get(id);
	}
	
	/**
	 * 根据项目id查询项目支持记录
	 * @param projectId
	 * @return
	 */
	List<ProjectSupport> getSupports(String projectId ){
		ProjectSupport support = new ProjectSupport();
		FundProject project = new FundProject(projectId);
		support.setProject(project);
		return projectSupportDao.getSupports(support);
	}
	
	/**
	 * 根据项目id和回报类型id查询项目支持记录
	 * @param projectId
	 * @param rewardId
	 * @return
	 */
	List<ProjectSupport> getSupports(String projectId ,String rewardId){
		ProjectSupport support = new ProjectSupport();
		FundProject project = new FundProject(projectId);
		support.setProject(project);
		support.setReward(new Reward(rewardId) {});
		return projectSupportDao.getSupports(support);
	}
	
	/**
	 * 获取抽奖回报的支持记录
	 * @param projectId
	 * @return
	 */
	List<ProjectSupport> getLuckySupports(String projectId){
		ProjectSupport support = new ProjectSupport();
		FundProject project = new FundProject(projectId);
		support.setProject(project);
		//构建reward查询条件
		Reward reward = new LuckyReward();
		support.setReward(reward);

		return projectSupportDao.getSupports(support);
		
	}
	
	/**
	 * 获取普通回报的支持记录
	 * @param projectId
	 * @return
	 */
	List<ProjectSupport> getNumalSupports(String projectId){
		
		ProjectSupport support = new ProjectSupport();
		FundProject project = new FundProject(projectId);
		support.setProject(project);
		//构建reward查询条件
		Reward reward = new NumalReward();
		support.setReward(reward);

		return projectSupportDao.getSupports(support);
		
	}
	
}
