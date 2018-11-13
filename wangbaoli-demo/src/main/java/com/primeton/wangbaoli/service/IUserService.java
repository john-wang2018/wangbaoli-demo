package com.primeton.wangbaoli.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.primeton.wangbaoli.entity.User;

public interface IUserService {
	/**
	 * 增加用户信息
	 * 
	 * @param user 用户信息
	 * @return 用户信息
	 */
	public User createUser(User user);

	/**
	 * 根据用户id的到用户
	 * 
	 * @param id 用户id
	 * @return user 用户信息
	 */
	public User get(Integer id);

	/**
	 * 得到全部的用户
	 * 
	 * @return 包含全部用户的集合
	 */
	public List<User> queryUsers(Integer page,Integer size);

	/**
	 * 通过员工id删除员工
	 * 
	 * @param id 员工id
	 */
	public void removeUser(Integer id);

	/**
	 * 通过得到的实体类信息修改员工
	 * 
	 * @param user 实体类信息
	 * @return 
	 */
	public User modify(User user,HttpSession sessions);
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 用户密码
	 * @return  用户信息
	 */
	public User login(String username, String password);
	/**
	 * 通过用户名得到用户信息
	 * @param username 用户名
	 * @param page 访问第几页
	 * @param size 每一页的信息条数
	 * @return 用户信息列表
	 */
	public List<User> getByUsername(String username, Integer page, Integer size);
	/**
	 * 修改用户密码
	 * @param newPassword 新用户密码
	 * @param oldPassword 原用户密码
	 * @param id 用户id
	 * @return 用户的信息
	 */
	public User modifyPassword(String newPassword,String oldPassword,Integer id);
	/**
	 * 将同一个部门的员工的orgid设为nul
	 * @param orgid  员工的部门id
	 * @return 员工的信息列表
	 */
	public List<User> modifyOrgIdIsNull(Integer orgid);

}
