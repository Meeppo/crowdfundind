package com.thinkgem.jeesite.modules.account.service;

import java.math.BigDecimal;

public interface AccountService {
	
	public void operateAmount(String AccountID,BigDecimal amount);
}
