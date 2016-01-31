package com.thinkgem.jeesite.modules.fund.reward.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.fund.entity.ProjectSupport;
import com.thinkgem.jeesite.modules.fund.reward.service.DrawManager;

/**
 * 抽奖回报
 */
@Entity
@Table(name="reward_lucky")
public class LuckyReward extends Reward {
	
	private Integer interval;
	
	private String drawManagerClazz;
	
	private DrawManager<ProjectSupport, ProjectSupport> drawManager;
	
	@Column(name="interval")
	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	
	@Column(name="draw_manager_clazz")
	public String getDrawManagerClazz() {
		return drawManagerClazz;
	}

	public void setDrawManagerClazz(String drawManagerClazz) {
		this.drawManagerClazz = drawManagerClazz;
	}

	@Transient
	@Override
	public DrawManager<ProjectSupport,ProjectSupport> getDrawManager() {
		
		if(this.drawManager == null && StringUtils.isNotBlank(this.drawManagerClazz)){
			
			try {
				this.drawManager = (DrawManager<ProjectSupport, ProjectSupport>) SpringContextHolder.getBean(Class.forName(drawManagerClazz));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		return this.drawManager;
	}
	
	
	
}
