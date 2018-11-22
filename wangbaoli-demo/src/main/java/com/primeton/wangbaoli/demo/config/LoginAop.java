package com.primeton.wangbaoli.demo.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.primeton.wangbaoli.demo.exception.DemoException;
import com.primeton.wangbaoli.demo.exception.enums.ErrorEnum;

/**
 * 登录拦截器，实现用户登录拦截
 * 
 * @author root
 *
 */
//@Component
@Aspect
public class LoginAop {
	/**
	 * 定义切点
	 */
	@Pointcut("execution(* com.primeton.wangbaoli.demo.controller..*.*(..))")
	public void loginPointcut() {
	}
	/**
	 * 对未登录的请求进行拦截
	 * @param joinPoint
	 * @throws DemoException
	 */
	@Before("loginPointcut()")
	public void loginIntercept(JoinPoint joinPoint) throws DemoException {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		String methodName = method.getName();
		List<String> methodNames = new ArrayList<String>();
		methodNames.add("createUser");
		methodNames.add("login");
		if (!methodNames.contains(methodName)) {
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
			if (null == session.getAttribute("id")) {
				throw new DemoException(ErrorEnum.NOT_LOGIN);
			}
		}
	}
}
