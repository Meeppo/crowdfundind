package com.thinkgem.jeesite.modules.fund.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.fund.reservation.dao.ReservationDao;
import com.thinkgem.jeesite.modules.fund.reservation.entity.Reservation;
import com.thinkgem.jeesite.modules.sys.entity.User;

@Service
@Transactional(readOnly = true)
public class ReservationService extends BaseService {
	
	@Autowired
	private ReservationDao reservationDao;
	
	public Reservation get(String id){
		return reservationDao.get(id);
	}
	
	@Transactional(readOnly = false)
	public void save(Reservation reservation){
		reservationDao.save(reservation);
	}
	
	/**
	 * 支持预约,每人只限支持一次
	 * @param reservationID
	 * @param memberID
	 * @param supportNum
	 */
	@Transactional(readOnly  = false)
	public Reservation addSupport(String reservationID,String memberID,int supportNum){
		
		Reservation reservation = get(reservationID);
		
		if(!reservation.isEffectiveLySupport(memberID, supportNum)){
			return reservation;
		}
		reservation.addMember(memberID);
		
		reservation.addSupportNum(supportNum);
		
		save(reservation);
		
		return reservation;
	}
	
}
