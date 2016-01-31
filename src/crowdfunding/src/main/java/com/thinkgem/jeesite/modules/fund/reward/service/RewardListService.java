package com.thinkgem.jeesite.modules.fund.reward.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.fund.exception.RewardCreatedEmptySupportsException;
import com.thinkgem.jeesite.modules.fund.exception.RewardCreatedFailException;
import com.thinkgem.jeesite.modules.fund.reward.dao.RewardListDao;
import com.thinkgem.jeesite.modules.fund.reward.entity.Reward;
import com.thinkgem.jeesite.modules.fund.reward.entity.RewardList;

@Service
@Transactional(readOnly = true)
public class RewardListService extends BaseService {
	
	@Autowired
	private RewardListDao rewardListDao;
	
	/**
	 * 创建回报清单
	 * @param supports
	 * @param draw 是否是抽奖模式
	 * @throws RewardCreatedFailException 
	 */
	@Transactional(readOnly = false)
	public void createListOfReward(List<ProjectSupport> supports, Reward reward){
		
		if(supports == null || supports.size() == 0){
			throw new RewardCreatedEmptySupportsException("支持列表为空");
		}
		
		List<RewardList> list = new ArrayList<>();
		//抽奖模式创建回报列表
		if(reward.isRoller()){
			supports = reward.getDrawManager().draw(supports,reward);
		}
		//普通模式创建回报列表
		else{
			
		}
		
		//创建rewardList
		for (ProjectSupport support : supports) {
			list.add(new RewardList(support, reward.isRoller()));
		}
		
		rewardListDao.save(list);
		
	}
}
