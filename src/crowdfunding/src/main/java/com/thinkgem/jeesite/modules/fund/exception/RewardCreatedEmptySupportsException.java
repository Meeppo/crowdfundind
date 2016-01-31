package com.thinkgem.jeesite.modules.fund.exception;

/**
 * 创建回报列表时，支持记录为空错误。支持记录为空时无需调用createReward接口，可在调用处处理。
 * @author Administrator
 *
 */
public class RewardCreatedEmptySupportsException extends
		RewardCreatedFailException {

	public RewardCreatedEmptySupportsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RewardCreatedEmptySupportsException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RewardCreatedEmptySupportsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RewardCreatedEmptySupportsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RewardCreatedEmptySupportsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
