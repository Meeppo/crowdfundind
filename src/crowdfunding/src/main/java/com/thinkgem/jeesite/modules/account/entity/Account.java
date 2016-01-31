package com.thinkgem.jeesite.modules.account.entity;

import java.math.BigDecimal;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.thinkgem.jeesite.common.persistence.IdEntity;

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Account<T> extends IdEntity<T>{
	
	
	private String ownerID;
	
	private BigDecimal amount;
	
	
	public String getOwnerID() {
		return ownerID;
	}


	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public abstract void addAmount(BigDecimal amount);
}
