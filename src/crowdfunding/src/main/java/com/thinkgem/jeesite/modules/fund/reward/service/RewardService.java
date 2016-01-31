package com.thinkgem.jeesite.modules.fund.reward.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.fund.reward.dao.RewardDao;
import com.thinkgem.jeesite.modules.fund.reward.entity.Reward;

@Service
@Transactional(readOnly = true)
public class RewardService extends BaseService {
	
	@Autowired
	private RewardDao rewardDao;
	
	@Transactional(readOnly = false)
	public void save(Reward reward){
		rewardDao.save(reward);
	}
	
	public Reward get(String id){
		return rewardDao.get(id);
	}
	
	
	public List<Reward> getListByProject(String projectId){
		return rewardDao.getListByProject(projectId);
	}
	
	
}
