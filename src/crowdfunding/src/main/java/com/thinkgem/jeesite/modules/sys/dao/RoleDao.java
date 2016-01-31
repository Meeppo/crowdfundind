/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 角色DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class RoleDao extends BaseDao<Role> {

	public Role findByName(String name){
		return getByHql("from Role where delFlag = :p1 and name = :p2", new Parameter(Role.DEL_FLAG_NORMAL, name));
	}
	
	public Role findByEnname(String ename){
		return getByHql("from Role where delFlag = :p1 and enname = :p2", new Parameter(Role.DEL_FLAG_NORMAL, ename));
	}
	
/*	public List<Role> findByUserId(String userId){
		
		StringBuilder hql = new StringBuilder("select distinct r from Role r, User u "
				+ "where r in elements (u.roleList) "
				+ "and r.delFlag='" + Role.DEL_FLAG_NORMAL +
				"' and u.delFlag='" + User.DEL_FLAG_NORMAL + 
				"' and u.id=:userID "
				+ "or (r.user.id=:userID and r.delFlag='" + Role.DEL_FLAG_NORMAL +
				"') order by r.name");
		
		Parameter parameter = new Parameter();
		parameter.put("userID", userId);
		return find(hql.toString(), parameter);
		
	}*/

}
