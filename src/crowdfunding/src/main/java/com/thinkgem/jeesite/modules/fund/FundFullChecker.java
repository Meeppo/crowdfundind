package com.thinkgem.jeesite.modules.fund;

import com.thinkgem.jeesite.modules.fund.entity.FundProject;

/**
 * 检验项目是否已满的接口
 * @author Administrator
 *
 */
public class FundFullChecker {
	
	public volatile static FundFullChecker checker;
	
	private FundFullChecker(){}
	
	public static FundFullChecker instance(){
		
		if(checker == null){
			synchronized (FundFullChecker.class) {
				if(checker == null){
					checker = new FundFullChecker();
				}
			}
		}
		
		return checker;
	}
	
	/**
	 * 校验项目是否已经满标
	 * @param project
	 * @return
	 */
	public boolean isFull(FundProject project){
		
		if(project == null)
			throw new IllegalArgumentException("需要校验满标的项目不存在");
		
		//可超募
		if(project.canOverflow()){
			
		}
		//不可超募
		else {
			if(project.getPercent() >= 100){
				return true;
			}
		}
		
		return false ;
	}
	
	
	
}
