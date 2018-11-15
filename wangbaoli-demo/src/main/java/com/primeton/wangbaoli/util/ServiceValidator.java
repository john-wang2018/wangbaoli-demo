package com.primeton.wangbaoli.util;

import com.primeton.wangbaoli.exception.enums.ErrorEnum;
import com.primeton.wangbaoli.exception.service.DemoException;

/**
 * 对前端数据进行通用验证
 * 对信息进行通用验证
 * @author root
 *
 */
public class ServiceValidator{
	
	/**
	 * 验证用户名是否符合规范
	 */
	public static final String REGEX_USERNAME 
		= "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,15}$";
	
	/**
	 * 验证密码是否符合规范
	 */
	public static final String REGEX_PASSWORD 
		= "^\\d{6,18}$";
	
	/**
	 * 验证是否与登录的用户名相同
	 * @param username 用户名
	 * @return 相同则返回true，不同则返回false
	 */
	public static boolean checkUsername(String username){
		return username.matches(REGEX_USERNAME);
	}
	
	/**
	 * 检查密码是否匹配
	 * @param password 用户输入的密码
	 * @return 相同则返回true，不同则返回false
	 */
	public static boolean checkPassword(String password) {
		return password.matches(REGEX_PASSWORD);
	}
	/**
	 * 对前端信息和数据库信息进行验证
	 */
	/**
	 * 验证输入信息是否为空
	 * @param infos传入的信息
	 * @return 信息为null返回true，不为null返回false
	 */
	public static void checkInfoIsNull(Object...infos) throws DemoException{
		if(infos==null)
			throw new DemoException(ErrorEnum.ERROR_CONTENT_CANNOT_EMPTY);
		for(Object info:infos) {
			if(info==null)
				throw new DemoException(ErrorEnum.ERROR_CONTENT_CANNOT_EMPTY);
			
		}
		
	}
	/**
	 * 用户如果不存在抛出用户不存在异常。
	 * @param result 用户是否存在。
	 * @throws DemoException 用户不存在异常
	 */
	public static void checkUsernameNotFound(boolean result) throws DemoException{
		if(result) {
			throw new DemoException(ErrorEnum.ERROR_USER);
		}
	}
	/**
	 * 判断删除结果 
	 * @param result 删除结果
	 * @throws DemoException 删除失败异常
	 */
	public static void checkDelete(boolean result) throws DemoException{
		if(result) {
			throw new DemoException(ErrorEnum.ERROR_DELETE);
		}
	}
	/**
	 * 判断组织机构是否重复
	 * @param result 判断结果
	 * @throws DemoException 组织机构已经存在异常
	 */
	public static void checkUserRepeat(boolean result) throws DemoException{
		if(result) {
			throw new DemoException(ErrorEnum.ERROR_USER_INUSE);
		}
	}
	/**
	 * 判断密码是否有误
	 * @param result 判断结果
	 * @throws DemoErrException 密码输入错误异常
	 */
	public static void checkPasswordErr(boolean result) throws DemoException{
		if(result) {
			throw new DemoException(ErrorEnum.ERROR_USER_PASSWPRD);
		}
	}
	/**
	 * 判断id是否存在
	 * @param result 判断结果
	 * @throws DemoException 选中的用户不存在
	 */
	public static void checkIdEextis(boolean result) throws DemoException{
		if(result) {
			throw new DemoException(ErrorEnum.ERROR_USER);
		}
	}
	/**
	 * 判断是否删除失败
	 * @param result 判断结果
	 * @throws DemoException 删除失败
	 */
	public static void checkIdEextisff(boolean result) throws DemoException{
		if(result) {
			throw new DemoException(ErrorEnum.ERROR_DELETE);
		}
	}
}
