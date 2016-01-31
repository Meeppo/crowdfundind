package com.thinkgem.jeesite.modules.fund.reward.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.fund.reward.entity.Reward;

/**
 * 抽奖回报类型的抽奖器
 *
 */
@Component
public class LuckyRewardDrawer implements DrawManager<ProjectSupport, ProjectSupport> {
	
	@Override
	public List<ProjectSupport> draw(List<ProjectSupport> list,Reward reward) {

		
		
		return null;
	}

}
