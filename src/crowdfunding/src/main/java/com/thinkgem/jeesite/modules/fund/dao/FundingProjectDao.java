package com.thinkgem.jeesite.modules.fund.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;

@Repository
public class FundingProjectDao extends BaseDao<FundProject> {
	
	
	public FundProject get(FundProject project){
		
		Parameter parameter = new Parameter();
		StringBuilder sql = new StringBuilder("FROM FundProject p where p.delFlag = '0' ");
		
		//append parameters
		if(StringUtils.isNotBlank(project.getTitle())){
			sql.append("and p.title = :title");
			parameter.put("title", project.getTitle());
		}
		
		return this.getByHql(sql.toString(), parameter);
	}
	
	
	public Page<FundProject> find(Page<FundProject> page,FundProject project){
		
		DetachedCriteria criteria = createDetachedCriteria();
		
		if(StringUtils.isNotBlank(project.getTitle())){
			criteria.add(Restrictions.like("title", project.getTitle()));
		}
		
		return this.find(page,criteria );
	}
}
