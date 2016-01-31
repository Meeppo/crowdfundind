package com.thinkgem.jeesite.modules.fund.reservation.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.fund.entity.FundProject;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 项目预约情况
 * @author Administrator
 *
 */
@Entity
@Table(name="reservation")
@DynamicInsert
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Reservation extends IdEntity<Reservation> {

	private static final long serialVersionUID = 1L;
	
	private FundProject project;
	
	private List<User> members = new ArrayList<User>();
	
	private Integer supporterNum;
	
	private Integer supportNum;
	
	private BigDecimal supportAmount;
	
	private Integer sumNum;
	
	private BigDecimal oneAmount;
	
	/**
	 * 
	 * 添加支持监听器
	 *
	 */
	interface OnSupportReservationListener{
		/**
		 * 是否为有效支持
		 * @param reservation
		 * @param memberID
		 * @param suppoertNum
		 * @return false 不是有效支持,支持不会生效
		 */
		public boolean isEffectivelySupport(Reservation reservation,String memberID,int suppoertNum);
		/**
		 * 是否已经满标
		 * @param reservation
		 */
		public boolean isStuff(Reservation reservation);
	}
	
	/**
	 * 默认情况下的支持监听器
	 */
	private OnSupportReservationListener supportReservationListener = new OnSupportReservationListener() {
		
		@Override
		public boolean isEffectivelySupport(Reservation reservation,
				String memberID, int suppoertNum) {
			if(reservation.getMembers().contains(new User(memberID)))
				return false;
			return true;
		}

		@Override
		public boolean isStuff(Reservation reservation) {
			// TODO Auto-generated method stub                                  
			return false;
		}

		
	};
	
	@Transient
	public OnSupportReservationListener getOnSupportReservationListener() {
		return supportReservationListener;
	}

	public void setOnSupportReservationListener(
			OnSupportReservationListener supportReservationListener) {
		this.supportReservationListener = supportReservationListener;
	}

	/**
	 * 判断该支持是否为有效支持
	 * @param reservation
	 * @param memberID
	 * @param suppoertNum
	 * @return 
	 */
	@Transient
	public boolean isEffectiveLySupport(String memberID, int suppoertNum){
		return supportReservationListener.isEffectivelySupport(this, memberID, suppoertNum);
	}
	/**
	 * 是否满标
	 * @return
	 */
	@Transient
	public boolean isStuff(){
		return supportReservationListener.isStuff(this);
	}
	
	public void addMember(String memberID){
		if(StringUtils.isNotBlank(memberID)){
			this.members.add(new User(memberID));
		}
	}
	
	public void addMember(User member){
		if(member != null && StringUtils.isNotBlank(member.getId())){
			this.members.add(member);
		}
	}
	/**
	 * 添加支持份数
	 * @param supportNum
	 */
	public void addSupportNum(Integer supportNum){
		if(supportNum != null ){
			this.supporterNum += supportNum;
		}
	} 
	/**
	 * 添加支持者数量
	 * @param supporterNum
	 */
	public void addSupporterNum(Integer supporterNum){
		if(supporterNum != null){
			this.supporterNum += supporterNum;
		}
	}
	/**
	 * 添加支持金额
	 * @param supportAmount
	 */
	public void addSupportAmount(BigDecimal supportAmount){
		if(supportAmount != null){
			this.supportAmount.add(supportAmount);
		}
	}
	/**
	 * 添加支持
	 * @param supportNum
	 * @param supporterNum
	 */
	public void addSupport(Integer supportNum,Integer supporterNum){
		if(supportNum != null){
			addSupportNum(supportNum);
			addSupportAmount(this.oneAmount.multiply(new BigDecimal(supportNum)));
			addSupporterNum(supporterNum);
		}
	}
	/**
	 * 添加支持
	 * @param supportNum
	 */
	public void addSupport(Integer supportNum){
		addSupport(supportNum, 1);
	}
	
	
	@ManyToMany(cascade=CascadeType.REMOVE)
	@JoinTable(name="reservation_support_record",
	joinColumns={@JoinColumn(name="reservation_id")},
	inverseJoinColumns={@JoinColumn(name="member_id")})
	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}
	
	/**
	 * 项目
	 * @return
	 */
	@OneToOne
	@JoinColumn(name="project_id",nullable=false,unique=true)
	public FundProject getProject() {
		return project;
	}

	public void setProject(FundProject project) {
		this.project = project;
	}
	
	/**
	 * 支持者数量
	 * @return
	 */
	@Column(name="supporter_num",columnDefinition="int(10) default 0")
	public Integer getSupporterNum() {
		return supporterNum;
	}

	public void setSupporterNum(Integer supporterNum) {
		this.supporterNum = supporterNum;
	}
	/**
	 * 支持份数
	 * @return
	 */
	@Column(name="support_num",columnDefinition="int(10) default 0")
	public Integer getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(Integer supportNum) {
		this.supportNum = supportNum;
	}
	/**
	 * 支持金额
	 * @return
	 */
	@Column(name="support_amount",columnDefinition="decimal(10) default 0")
	public BigDecimal getSupportAmount() {
		return supportAmount;
	}

	public void setSupportAmount(BigDecimal supportAmount) {
		this.supportAmount = supportAmount;
	}
	
	@Column(name="sum_num",columnDefinition = "int(10) default 0")
	public Integer getSumNum() {
		return sumNum;
	}

	public void setSumNum(Integer sumNum) {
		this.sumNum = sumNum;
	}
	
	@Column(name="one_amount",columnDefinition="decimal(10) default 0")
	public BigDecimal getOneAmount() {
		return oneAmount;
	}

	public void setOneAmount(BigDecimal oneAmount) {
		this.oneAmount = oneAmount;
	}
	
	
	
}
