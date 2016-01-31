package com.thinkgem.jeesite.modules.fund.reward.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.fund.reward.entity.Reward;

@Repository
public class RewardDao extends BaseDao<Reward> {
	
	/**
	 * 根据项目id获取项目中定义的回报类型
	 * @param projectId
	 * @return
	 */
	public List<Reward> getListByProject(String projectId){
		
		StringBuilder buff = new StringBuilder("select r from Reward r where r.delFlag = '0' ");
		
		Parameter parameter = new Parameter();
		
		if(StringUtils.isNotBlank(projectId)){
			
			buff.append(" and r.project.id = :projectId");
			parameter.put("projectId", projectId);
		}
		
		
		return find(buff.toString(), parameter);
		
	}
	
}
