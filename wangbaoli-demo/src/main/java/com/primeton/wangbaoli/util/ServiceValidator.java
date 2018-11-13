package com.primeton.wangbaoli.util;

import com.primeton.wangbaoli.exception.service.DeleteException;
import com.primeton.wangbaoli.exception.service.IdNotFoundException;
import com.primeton.wangbaoli.exception.service.NullRequestException;
import com.primeton.wangbaoli.exception.service.OrgRepeatException;
import com.primeton.wangbaoli.exception.service.PasswordErrException;
import com.primeton.wangbaoli.exception.service.UserNotFoundException;
import com.primeton.wangbaoli.exception.service.UsernameRepeatException;

/**
 * 对前端数据进行通用验证
 * 对信息进行通用验证
 * @author root
 *
 */
public class ServiceValidator implements Validator{
	
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
	public static void checkInfoIsNull(Object...infos) throws NullRequestException{
		if(infos==null)
			throw new NullRequestException("必填内容不能为空！");
		for(Object info:infos) {
			if(info==null)
				throw new NullRequestException("必填内容不能为空！");
			
		}
		
	}
	/**
	 * 用户如果不存在抛出用户不存在异常。
	 * @param result 用户是否存在。
	 * @throws UserNotFoundException 用户不存在异常
	 */
	public static void checkUsernameNotFound(boolean result) throws UserNotFoundException{
		if(result) {
			throw new UserNotFoundException("用户名不存在");
		}
	}
	/**
	 * 判断删除结果 
	 * @param result 删除结果
	 * @throws DeleteException 删除失败异常
	 */
	public static void checkDelete(boolean result) throws DeleteException{
		if(result) {
			throw new DeleteException("删除失败");
		}
	}
	/**
	 * 判断组织机构是否重复
	 * @param result 判断结果
	 * @throws OrgRepeatException 组织机构已经存在异常
	 */
	public static void checkUserRepeat(boolean result) throws UsernameRepeatException{
		if(result) {
			throw new UsernameRepeatException("用户名已存在");
		}
	}
	/**
	 * 判断密码是否有误
	 * @param result 判断结果
	 * @throws PasswordErrException 密码输入错误异常
	 */
	public static void checkPasswordErr(boolean result) throws PasswordErrException{
		if(result) {
			throw new PasswordErrException("密码输入错误");
		}
	}
	public static void checkIdEextis(boolean result) throws IdNotFoundException{
		if(result) {
			throw new IdNotFoundException("选中的用户不存在");
		}
	}
	public static void checkIdEextisff(boolean result) throws IdNotFoundException{
		if(result) {
			throw new IdNotFoundException("删除失败");
		}
	}
}
