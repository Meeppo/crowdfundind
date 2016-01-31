package com.thinkgem.jeesite.modules.fund.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.thinkgem.jeesite.modules.fund.StatusOberserver;
import com.thinkgem.jeesite.modules.fund.StatusObersvable;
@Table(name="fund_project")
@Entity
@DynamicInsert
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class FundProject extends IdEntity<FundProject> implements StatusObersvable{

	private static final long serialVersionUID = 1865066700340812495L;
	/**
	 * 项目刚创建,未提交
	 */
	public static final String PRO_CREATE = "0";
	/**
	 * 项目待校验状态
	 */
	public static final String PRO_VERIFY = "1";
	/**
	 * 项目预约满标待审核
	 */
	public static final String PRO_RESV_AUDIT = "22";
	/**
	 * 项目预约中
	 */
	public static final String PRO_RESV_START = "21";
	
	/**
	 * 项目开始
	 */
	public static final String PRO_START = "2";
	
	public static final String PRO_EXPIRED = "3";
	
	public static final String PRO_FULL = "4";
	
	public static final String PRO_FULL_VERIFY = "5";
	
	public static final String PRO_OK = "6";
	
	public static final String PRO_REWARDING = "7";
	
	public static final String PRO_FINISH = "8";
	
	/**
	 * 
	 * 管理项目状态和流程的关系
	 *
	 */
	public static class ProjectStatusManager{
		
		private static Map<String, String> statusMap = new HashMap<String, String>();
		
		static {
			statusMap.put("create", PRO_CREATE);
		}
		
		public static String get(String task){
			return statusMap.get(task);
		}
		
	}
	
	/**
	 * 流程定义的key
	 */
	public static final String PROCESS_DEFINITION_KEY = "funding";
	
	public FundProject() {
		super();
	}

	public FundProject(String id) {
		super();
		this.id = id;
	}
	
	private String fundProcessInstId ;
	
	private String title;
	
	private String type;
	
	private List<Category> categories;
	
	private BigDecimal amount;
	
	private Integer amountOverRange;
	
	private Integer duration;
	
	private Float percent;
	
	private String summary;
	
	private String description;
	
	private CreaterInfo createrInfo;
	
	private Date beginDate;
	
	private String status;
	
	private String reservation;
	
	private String auditRemarks;
	
	private String verifyRemarks;
	
	private List<ProjectSupport> supports;
	
	/**
	 * 项目标题
	 * @return
	 */
	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 项目类型
	 * 如：回报众筹、公益众筹、股权众筹
	 * @return
	 */
	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 项目所属分类
	 * @return
	 */
	@ManyToMany
	@JoinTable(name="project_category",
	joinColumns={@JoinColumn(name="project_id")},
	inverseJoinColumns={@JoinColumn(name="category_id")})
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	/**
	 * 项目众筹总金额
	 * @return
	 */
	@Column(name="amount")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * 金额超募范围
	 * @return
	 */
	@Column(name="amount_over_range")
	public Integer getAmountOverRange() {
		return amountOverRange;
	}

	public void setAmountOverRange(Integer amountOverRange) {
		this.amountOverRange = amountOverRange;
	}
	
	/**
	 * 是否可以超募
	 * @return
	 */
	public boolean canOverflow(){
		if(amountOverRange != null && amountOverRange > 0)
			return true;
		return false;
	}
	
	/**
	 * 项目期限
	 * @return
	 */
	@Column(name="duration")
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	/**
	 * 项目已完成百分比（若是超募，可大于100）
	 * @return
	 */
	@Column(name="percent")
	public Float getPercent() {
		return percent;
	}

	public void setPercent(Float percent) {
		this.percent = percent;
	}
	
	/**
	 * 项目简介
	 * @return
	 */
	@Column(name="summary")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * 项目描述
	 * @return
	 */
	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 项目发起人信息
	 * @return
	 */
	@OneToOne
	public CreaterInfo getCreaterInfo() {
		return createrInfo;
	}

	public void setCreaterInfo(CreaterInfo createrInfo) {
		this.createrInfo = createrInfo;
	}
	
	/**
	 * 项目开始时间
	 * @return
	 */
	@Column(name="begin_date")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * 项目状态
	 * @return
	 */
	@Column(name="status",columnDefinition="varchar(2) default 0")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 项目流程id
	 * @return
	 */
	@Column(name="process_id")
	public String getFundProcessInstId() {
		return fundProcessInstId;
	}

	public void setFundProcessInstId(String fundProcessInstId) {
		this.fundProcessInstId = fundProcessInstId;
	}
	
	/**
	 * 审核备注
	 * @return
	 */
	@Column(name="audit_remarks")
	public String getAuditRemarks() {
		return auditRemarks;
	}

	public void setAuditRemarks(String auditRemarks) {
		this.auditRemarks = auditRemarks;
	}
	
	/**
	 * 是否预约
	 * 
	 * @return 如果是返回1,否返回0
	 */
	@Column(name="reservation",columnDefinition="varchar(1) default '0'")
	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	
	/**
	 * 校验备注
	 * @return
	 */
	@Column(name="verify_remarks")
	public String getVerifyRemarks() {
		return verifyRemarks;
	}

	public void setVerifyRemarks(String verifyRemarks) {
		this.verifyRemarks = verifyRemarks;
	}
	
	/**
	 * 项目支持记录
	 * @return
	 */
	@ManyToMany
	@JoinTable(name="project_support",
	joinColumns={@JoinColumn(name="project_id")},
	inverseJoinColumns={@JoinColumn(name="member_id")})
	public List<ProjectSupport> getSupports() {
		return supports;
	}

	public void setSupports(List<ProjectSupport> supports) {
		this.supports = supports;
	}

	private StatusOberserver sObserver = new StatusOberserver() {
		
		@Override
		public void change(StatusObersvable obersvable,String tarStatus) {
			String curStau = obersvable.getStatus();
			
			//if is legal
			obersvable.setStatus(tarStatus);
		}
	};
	
	@Transient
	public StatusOberserver getObserver() {
		return sObserver;
	}
	/**
	 * 设置状态观察者,在状态变化时,变更项目状态
	 * @return
	 */ 
	public void setObserver(StatusOberserver sObserver) {
		this.sObserver = sObserver;
	}

	@Override
	public void changeStatus(String tarStatus) {
		if(sObserver != null){
			sObserver.change(this,tarStatus);	
		}else{
			this.status = tarStatus;
		}
			
	}
	

	
}
