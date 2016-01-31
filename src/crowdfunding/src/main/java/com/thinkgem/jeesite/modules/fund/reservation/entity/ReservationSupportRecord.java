package com.thinkgem.jeesite.modules.fund.reservation.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/*@Entity
@Table(name="reservation_support_record")
@DynamicInsert
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)*/
public class ReservationSupportRecord extends IdEntity<ReservationSupportRecord> {
	
	private static final long serialVersionUID = 8440785835298408560L;

	private Reservation reservation;
	
	private User member;
	
	@ManyToOne
	@JoinColumn(name="reservation_id")
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	@ManyToOne
	@JoinColumn(name="member_id")
	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}
	
	
	
}
