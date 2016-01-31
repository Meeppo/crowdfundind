package com.thinkgem.jeesite.modules.fund.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.thinkgem.jeesite.common.persistence.IdEntity;
@Entity
@Table(name="fund_category")
public class Category extends IdEntity<Category>{
	
	private List<Category> pCategories ;
	
	private String name;
	
	@ManyToMany
	@JoinTable(name="asso_category",
	joinColumns={@JoinColumn(name="category_id")},
	inverseJoinColumns={@JoinColumn(name="pcategory_id")})
	public List<Category> getpCategories() {
		return pCategories;
	}

	public void setpCategories(List<Category> pCategories) {
		this.pCategories = pCategories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
