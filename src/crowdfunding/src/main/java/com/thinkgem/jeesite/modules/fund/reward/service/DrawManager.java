package com.thinkgem.jeesite.modules.fund.reward.service;

import java.util.List;

import com.thinkgem.jeesite.modules.fund.reward.entity.Reward;

/**
 * 
 * 抽奖管理器
 * 定义抽奖接口 
 * @param <T>
 * @param <E>
 */
public interface DrawManager<T,E> {
	
	List<E> draw(List<T> list,Reward reward);
	
}
