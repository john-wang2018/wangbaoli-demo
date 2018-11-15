package com.primeton.wangbaoli.exception.service;

import com.primeton.wangbaoli.exception.enums.ErrorEnum;

/**
 * 服务端的自定义异常
 * 
 * @author root
 *
 */
public class DemoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	/**
	 * code 异常码 msg 异常信息
	 */
	private String code;
	private String msg;

	/**
	 * 自定义构造方法用来接收错误枚举状态
	 * 
	 * @param e
	 */
	public DemoException(ErrorEnum e) {
		setCode(e.getCode());
		setMsg(e.getMsg());
	}

	public DemoException() {
		super();
	}

	public DemoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DemoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DemoException(Throwable cause) {
		super(cause);
	}

	public DemoException(String message) {
		super(message);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
