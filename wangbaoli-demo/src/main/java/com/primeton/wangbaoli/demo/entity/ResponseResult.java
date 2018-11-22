package com.primeton.wangbaoli.demo.entity;

import com.primeton.wangbaoli.demo.exception.DemoException;

/**
 * 相应结果实体类
 * 
 * @author root
 *
 * @param <T>
 */
public class ResponseResult<T> {
	public Integer state;
	public T data;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String message;
	public static final Integer ERR = 0;
	public static final Integer OK = 1;

	public ResponseResult() {
		super();
	}

	public ResponseResult(DemoException e) {
		setState(ResponseResult.ERR);
		setCode(e.getCode());
		setMessage(e.getMsg());
	}

	public ResponseResult(Integer state, String message) {
		super();
		setState(state);
		setMessage(message);
	}

	public ResponseResult(Integer state, T data, String message) {
		super();
		setState(state);
		setMessage(message);
		setData(data);
	}

	public ResponseResult(Integer state, T data) {
		setState(state);
		setData(data);
	}

	public ResponseResult(Integer state) {
		setState(state);
	}

	public ResponseResult(T data) {
		setData(data);
	}

	public ResponseResult(String message) {
		setMessage(message);
	}

	@Override
	public String toString() {
		return "RequestResult [state=" + state + ", data=" + data + ", message=" + message + "]";
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
