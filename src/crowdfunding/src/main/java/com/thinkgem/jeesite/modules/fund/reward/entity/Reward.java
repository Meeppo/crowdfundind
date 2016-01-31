package com.thinkgem.jeesite.modules.fund.reward.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.fund.reward.service.DrawManager;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Reward extends IdEntity<Reward> {

	private static final long serialVersionUID = 4861210002207802059L;
	
	public Reward() {
		super();
	}
	
	public Reward(String id) {
		super();
		this.id = id;
	}
	private FundProject project;
	
	private String type;
	
	private boolean isRoller;
	
	private String reSummary;
	
	private String reDetail;
	
	private Date carryDate;
	
	private Integer receiverNum;
	
	private BigDecimal freight;
	
	private String photo;
	@ManyToOne
	@JoinColumn(name="project_id")
	public FundProject getProject() {
		return project;
	}

	public void setProject(FundProject project) {
		this.project = project;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name="is_roller")
	public boolean isRoller() {
		return isRoller;
	}

	public void setRoller(boolean isRoller) {
		this.isRoller = isRoller;
	}
	@Column(name="re_summary")
	public String getReSummary() {
		return reSummary;
	}

	public void setReSummary(String reSummary) {
		this.reSummary = reSummary;
	}
	@Column(name="re_detail")
	public String getReDetail() {
		return reDetail;
	}

	public void setReDetail(String reDetail) {
		this.reDetail = reDetail;
	}
	@Column(name="carry_date")
	public Date getCarryDate() {
		return carryDate;
	}

	public void setCarryDate(Date carryDate) {
		this.carryDate = carryDate;
	}
	@Column(name="receiver_num")
	public Integer getReceiverNum() {
		return receiverNum;
	}

	public void setReceiverNum(Integer receiverNum) {
		this.receiverNum = receiverNum;
	}
	/**
	 * 运费
	 * @return
	 */
	@Column(name="freight")
	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	/**
	 * 回报图片
	 * Maybe this is unnecessary,I just need specify a store memory that identified by projectID
	 * @return
	 */
	@Column(name="photo")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Transient
	public DrawManager getDrawManager(){
		return null;
	}
	
}
