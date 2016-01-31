package com.thinkgem.jeesite.modules.fund.reward.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;

/**
 * 项目回报清单(发货单)
 *
 */
@Entity
@Table(name="reward_list")
@DynamicInsert
public class RewardList extends IdEntity<RewardList> {
	
	
	public RewardList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RewardList(ProjectSupport support,Boolean isDraw) {
		super();
		this.support = support;
		this.isDraw = isDraw;
	}

	private ProjectSupport support;
	
	private Boolean isDraw;
	
	private String status;
	
	private String transportInfo;
	
	@OneToOne
	@JoinColumn(name="support_id")
	public ProjectSupport getSupport() {
		return support;
	}

	public void setSupport(ProjectSupport support) {
		this.support = support;
	}

	public Boolean isDraw() {
		return isDraw;
	}

	public void setDraw(Boolean isDraw) {
		this.isDraw = isDraw;
	}
	
	/**
	 * 发货单的状态，由发起人维护
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 物流信息，虚拟回报没有
	 * @return
	 */
	public String getTransportInfo() {
		return transportInfo;
	}

	public void setTransportInfo(String transportInfo) {
		this.transportInfo = transportInfo;
	}
	
	
	
}
