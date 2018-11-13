package com.primeton.wangbaoli.exception;

import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.primeton.wangbaoli.entity.RequestResult;
import com.primeton.wangbaoli.exception.service.ServiceException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	private Logger logger=LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
	
	public GlobalDefaultExceptionHandler() {
		
	}
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public RequestResult<Void> defaultErrorHandler(HttpServletRequest request, HttpServletResponse res,Exception e) {
		logger.info("Restful Http请求发生异常...");

		if (res.getStatus() == HttpStatus.BAD_REQUEST.value()) {
			logger.debug("");
			logger.info("修改返回状态值为200");
			res.setStatus(HttpStatus.OK.value());
		}

		if (e instanceof NullPointerException) {
			logger.error("代码00：" + e.getMessage(), e);
			return new RequestResult<Void>(00,"发生空指针异常");
		} 
		else if (e instanceof IllegalArgumentException) {
			logger.error("代码01：" + e.getMessage(), e);
			return new RequestResult<Void>(01,"请求参数类型不匹配");
		} 
		else if (e instanceof SQLException) {
			logger.error("代码02：" + e.getMessage(), e);
			return new RequestResult<Void>(02,"数据库访问异常");
		}  
		else if (e instanceof NoHandlerFoundException) {
			logger.error("代码02：" + e.getMessage(), e);
			return new RequestResult<Void>(404,"访问路径错误");
		} 
		if(e instanceof ServiceException) {
			logger.error("===="+e.getMessage()+"====");
			
			return new RequestResult<Void>(RequestResult.ERR,e.getMessage());
		}else {
			logger.error("代码99：" + e.getMessage(), e);
			return new RequestResult<Void>(99,"服务器代码发生异常,请联系管理员");
		}
		
	}
	
	
        

}
