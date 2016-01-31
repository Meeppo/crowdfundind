package com.thinkgem.jeesite.modules.fund.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.fund.StatusOberserver;
import com.thinkgem.jeesite.modules.fund.StatusObersvable;
import com.thinkgem.jeesite.modules.fund.reward.entity.Reward;
import com.thinkgem.jeesite.modules.fund.reward.entity.RewardList;
import com.thinkgem.jeesite.modules.member.entity.Member;

/**
 * 项目订单，或称之为支持记录。用于记录用户对项目的支持情况。
 *
 */
@Entity
@Table(name = "fund_project_support")
@DynamicInsert @DynamicUpdate
public class ProjectSupport extends IdEntity<ProjectSupport> implements StatusObersvable{
		
		/**
		 * 投资中
		 */
		public final static String SUPPORTING = "0";
		
		/**
		 * 投资成功
		 */
		public final static String SUPPORT_SUCCESS = "1";
		
		/**
		 * 投资失败
		 */
		public final static String SUPPORT_FAIL = "2";
		
		/**
		 * 投资结束
		 */
		public final static String SUPPORTED = "3";
		
		
		private String orderNumber;
		
		private FundProject project;
		
		private Member member;
		
		private BigDecimal amount;
		
		private Reward reward;
		
		private String status;
		
		private RewardList rewardList;
		
		/**
		 * 订单编号
		 * @return
		 */
		public String getOrderNumber() {
			return orderNumber;
		}

		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}

		@ManyToOne
		@JoinColumn(name="project_id")
		public FundProject getProject() {
			return project;
		}

		public void setProject(FundProject project) {
			this.project = project;
		}
		@ManyToOne
		@JoinColumn(name="member_id")
		public Member getMember() {
			return member;
		}

		public void setMember(Member member) {
			this.member = member;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		@ManyToOne
		@JoinColumn(name="reward_id")
		public Reward getReward() {
			return reward;
		}

		public void setReward(Reward reward) {
			this.reward = reward;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		@OneToOne
		public RewardList getRewardList() {
			return rewardList;
		}

		public void setRewardList(RewardList rewardList) {
			this.rewardList = rewardList;
		}

		private StatusOberserver sObserver = new StatusOberserver() {
			
			@Override
			public void change(StatusObersvable obersvable,String tarStatus) {
				String curStau = obersvable.getStatus();
				
				//if is legal
				obersvable.setStatus(tarStatus);
			}
		};
		
		@Override
		public void changeStatus(String tarStatus) {
			if(sObserver != null){
				sObserver.change(this,tarStatus);	
			}else{
				this.status = tarStatus;
			}
		}
		
		
	
	
	
}
