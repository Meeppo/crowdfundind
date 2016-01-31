package com.thinkgem.jeesite.modules.rpc;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.DiscriminatorFormula;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 三方支付接口交易订单
 * @author Administrator
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class TradeOrder extends IdEntity<TradeOrder> {

	
	public abstract void onCallBack(TradeIntent intent);
	
	private String formData;
	
	/**
	 * 交易的表单数据
	 * 一般为json格式
	 * @return
	 */
	@Column(name="form_data")
	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}
	
	
	
	
}
