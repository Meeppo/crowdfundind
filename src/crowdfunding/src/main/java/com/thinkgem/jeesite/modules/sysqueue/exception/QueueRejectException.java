package com.thinkgem.jeesite.modules.sysqueue.exception;

import java.util.concurrent.ExecutionException;

public class QueueRejectException extends ExecutionException {

	public QueueRejectException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QueueRejectException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public QueueRejectException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public QueueRejectException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
