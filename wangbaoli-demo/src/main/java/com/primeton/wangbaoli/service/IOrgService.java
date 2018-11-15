package com.primeton.wangbaoli.service;

import java.util.List;

import javax.servlet.http.HttpSession;


import com.primeton.wangbaoli.entity.Org;
/**
 * 组织机构服务层接口
 * @author root
 *
 */
public interface IOrgService {
	/**
	 * 创建组织机构
	 * @param org 组织机构对象
	 * @return 组织机构对象信息
	 */
	Org createOrg(Org org,HttpSession session);
	/**
	 * 通过组织机构id删除组织机构
	 * @param id 组织机构id
	 * @return 返回1，表示成功；0表示删除失败
	 */
	void removeOrg(Integer id);
	/**
	 * 修改组织机构信息
	 * @param org 组织机构对象
	 * @return 修改后的组织机构对象
	 */
	Org modifyOrg(Org org,HttpSession session,Integer id);
	/**
	 * 通过组织机构的id查找组织机构信息
	 * @param id 组织机构id
	 * @return 组织机构实体类对象
	 */
	Org getOrg(Integer id);
	/**
	 * 通过组织机构名模糊查询组织机构信息
	 * @param orgname 组织机构名称
	 * @return 组织机构对象列表
	 */
	List<Org> queryByLike(String orgname,Integer page,Integer size);
	/**
	 * 查询所有组织机构的信息
	 * @return 组织机构对象列表
	 */
	List<Org> queryOrgs(Integer page,Integer size);
}
