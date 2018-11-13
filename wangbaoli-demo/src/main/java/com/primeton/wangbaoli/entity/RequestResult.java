package com.primeton.wangbaoli.entity;

import java.util.List;

public class RequestResult<T> {
	public Integer  state;
	public T data;
	public String message;
	public static final Integer ERR=0;
	public static final Integer OK=1;
	public RequestResult() {
		super();
	}
	
	public RequestResult(Integer state, String message) {
		super();
		setState(state);
		setMessage(message);
	}

	public RequestResult(Integer state, T data, String message) {
		super();
		setState(state);
		setMessage(message);
		setData(data);
	}

	public RequestResult(Integer state, T data) {
		setState(state);
		setData(data);
	}

	public RequestResult(Integer state) {
		setState(state);
	}

	public RequestResult(T data) {
		setData(data);
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
