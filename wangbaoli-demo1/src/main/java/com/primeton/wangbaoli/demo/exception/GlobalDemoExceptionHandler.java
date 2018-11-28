package com.primeton.wangbaoli.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.primeton.wangbaoli.demo.entity.ResponseResult;

/**
 * 统一异常处理类
 * 
 * @author root
 *
 */
@ControllerAdvice
public class GlobalDemoExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalDemoExceptionHandler.class);

	public GlobalDemoExceptionHandler() {

	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseResult<Void> defaultErrorHandler(HttpServletRequest request, HttpServletResponse res, Exception e) {
		logger.error("Restful Http请求发生异常...");
		if (e instanceof DemoException) {
			DemoException service = (DemoException) e;
			logger.error("====" + service.getCode() + "====", e);
			return new ResponseResult<Void>((DemoException) e);
		} else if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			logger.error("代码99：" + e.getMessage(), e);
			return new ResponseResult<Void>(ResponseResult.ERR, "您访问的页面不存在！");
		} else {
			logger.error("代码77：" + e.getMessage(), e);
			return new ResponseResult<Void>(ResponseResult.ERR, "服务器代码发生异常,请联系管理员");
		}

	}
}
