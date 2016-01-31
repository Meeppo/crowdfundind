package com.thinkgem.jeesite.modules.fund.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.fund.reward.entity.LuckyReward;
import com.thinkgem.jeesite.modules.fund.reward.entity.NumalReward;
import com.thinkgem.jeesite.modules.fund.reward.entity.Reward;

@Repository
public class ProjectSupportDao extends BaseDao<ProjectSupport> {
	
	/**
	 * 获取支持记录
	 * @param support
	 * @return
	 */
	public List<ProjectSupport> getSupports(ProjectSupport support){
		
		StringBuilder buff = new StringBuilder();
		Parameter parameter = new Parameter();
		buff.append("select s from ProjectSupport s ");
		if(support != null && support.getReward() != null){
			buff.append("left join s.reward as r join s.project p");
		}
		buff.append(" where s.delFlag = '0' ");
		if(support != null){
			if(support.getReward() != null){
				if(support.getReward() instanceof LuckyReward){
					buff.append("and exists(select LuckyReward.id from LuckyReward lr where lr.id = r.id)");
				}else if(support.getReward() instanceof NumalReward){}{
					buff.append("and exists(select NumalReward.id from NumalReward nr where nr.id = r.id)");
				}
			}
			if(support.getProject() != null){
				if(StringUtils.isNotBlank(support.getProject().getId())){
					buff.append("and p.id = :projectId");
					parameter.put("projectId", support.getProject().getId());
				}
			}
			
		}
		
		return find(buff.toString(), parameter);
	}
}
