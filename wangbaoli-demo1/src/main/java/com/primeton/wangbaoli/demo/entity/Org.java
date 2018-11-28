package com.primeton.wangbaoli.demo.entity;

import java.util.Date;
import java.util.List;

/**
 * 组织机构实体类
 * 
 * @author root
 *
 */
public class Org {

	private Integer id;
	private String orgName;
	private Integer superOrgId;
	private Integer orgCode;
	private String createUser;
	private Date createTime;
	private String modifyUser;
	private Date modifyTime;
	private List<User> users;
	private List<Org> childrens;
	public Org() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getSuperOrgId() {
		return superOrgId;
	}
	public void setSuperOrgId(Integer superOrgId) {
		this.superOrgId = superOrgId;
	}
	public Integer getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Org> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<Org> childrens) {
		this.childrens = childrens;
	}
	
	
}
