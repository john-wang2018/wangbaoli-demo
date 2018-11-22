package com.primeton.wangbaoli.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.primeton.wangbaoli.demo.entity.Org;
import com.primeton.wangbaoli.demo.exception.DemoException;

/**
 * 组织机构服务层接口
 * 
 * @author root
 *
 */
public interface IOrgService {

	/**
	 * 创建组织机构
	 * @param org 组织机构对象
	 * @return 组织机构对象信息
	 */
	Org createOrg(Org org,HttpSession session)throws DemoException;

	/**
	 * 通过组织机构id删除组织机构
	 * 
	 * @param id 组织机构id
	 * @return 返回1，表示成功；0表示删除失败
	 */
	Integer removeOrg(Integer id) throws DemoException;

	/**
	 * 修改组织机构信息
	 * 
	 * @param org 组织机构对象
	 * @return 修改后的组织机构对象
	 */
	Org modifyOrg(Org org, HttpSession session) throws DemoException;

	/**
	 * 通过组织机构的id查找组织机构信息
	 * 
	 * @param id 组织机构id
	 * @return 组织机构实体类对象
	 */
	Org getOrg(Integer id) throws DemoException;

	/**
	 * 查询所有组织机构的信息
	 * 
	 * @return 组织机构对象列表
	 */
	List<Org> queryOrgs(Integer page, Integer size) throws DemoException;
}
