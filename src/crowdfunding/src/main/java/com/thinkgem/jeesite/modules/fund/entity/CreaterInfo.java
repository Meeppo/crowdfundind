package com.thinkgem.jeesite.modules.fund.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.thinkgem.jeesite.common.persistence.IdEntity;
@Entity
@Table(name="creater_info")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class CreaterInfo extends IdEntity<CreaterInfo> {

	private static final long serialVersionUID = 3665491038336700759L;
	
	private FundProject project;
	
	private String selfIntroduction;
	 
	private String selfIntrDetail;
	 
	@OneToOne
	@JoinColumn(name="project_id",referencedColumnName="id",nullable=false)
	public FundProject getProject() {
		return project;
	}


	public void setProject(FundProject project) {
		this.project = project;
	}


	/**
	  * 个人简介
	  * @return
	  */
	 @Column(name="self_introduction")
	public String getSelfIntroduction() {
		return selfIntroduction;
	}
	 
	 
	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}
	/**
	 * 个人简介详情
	 * @return
	 */
	@Column(name="self_intr_detail")
	public String getSelfIntrDetail() {
		return selfIntrDetail;
	}

	public void setSelfIntrDetail(String selfIntrDetail) {
		this.selfIntrDetail = selfIntrDetail;
	}
	 
	 
	
}
