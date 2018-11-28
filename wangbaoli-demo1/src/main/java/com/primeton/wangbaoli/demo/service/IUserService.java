package com.primeton.wangbaoli.demo.service;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.primeton.wangbaoli.demo.entity.User;
import com.primeton.wangbaoli.demo.exception.DemoException;

/**
 * 用户服务接口
 * 
 * @author root
 *
 */
public interface IUserService {
	/**
	 * 增加用户信息
	 * 
	 * @param user 用户信息
	 * @return 用户信息
	 */
	User createUser(User user) throws DemoException;

	/**
	 * 通过员工id删除员工
	 * 
	 * @param id 员工id
	 * @return 返回1代表删除成功，返回0代表删除失败。
	 */
	Integer removeUser(Integer id) throws DemoException;

	/**
	 * 通过得到的实体类信息修改员工
	 * 
	 * @param user 实体类信息
	 * @return
	 */
	User modifyUser(User user, HttpSession sessions) throws DemoException;

	/**
	 * 用户登录
	 * 
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 用户信息
	 */
	User login(User user) throws DemoException;

	/**
	 * 修改用户密码
	 * 
	 * @param newPassword 新用户密码
	 * @param oldPassword 原用户密码
	 * @param id          用户id
	 * @return 用户的信息
	 */
	User modifyPassword(User user, HttpSession session) throws DemoException;

	/**
	 * 通过用户名通过模糊查询得到用户信息
	 * 
	 * @param username 用户名
	 * @param page     访问第几页
	 * @param size     每一页的信息条数
	 * @return 用户信息列表
	 */
	PageInfo<User> queryByLikeName(String username, Integer page, Integer size) throws DemoException;

	/**
	 * 通过用户名查询用户信息
	 * 
	 * @param username 用户名
	 * @return 用户对象
	 */
	User getUserByUserName(String username) throws DemoException;

	/**
	 * 通过用户所在机构id查找该机构的用户
	 * 
	 * @param orgId 用户所在机构id
	 * @return 该机构的用户数据
	 * @throws DemoException 服务异常
	 */
	PageInfo<User> queryUserByOrgId(Integer orgId, Integer page, Integer size) throws DemoException;

	/**
	 * 根据用户id的到用户
	 * 
	 * @param id 用户id
	 * @return user 用户信息
	 */
	User getUser(Integer id) throws DemoException;

	/**
	 * 得到全部的用户
	 * 
	 * @return 包含全部用户的集合
	 */
	PageInfo<User> queryUsers(Integer page, Integer size) throws DemoException;
}
