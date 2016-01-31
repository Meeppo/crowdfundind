package com.thinkgem.jeesite.modules.member.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.thinkgem.jeesite.modules.sys.entity.User;

@Entity
@Table(name="member")
@PrimaryKeyJoinColumn(name="id")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Member extends User {

	private static final long serialVersionUID = 7284925113078211919L;
	
	
	
}
