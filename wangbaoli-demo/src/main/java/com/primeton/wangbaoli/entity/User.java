package com.primeton.wangbaoli.entity;

import java.util.Date;

/**
 * 用户实体类
 * 
 * @author root
 *
 */
public class User {
	private Integer id;
	private String username;
	private Integer orgid;
	private String password;
	private String uuid;
	private Integer permission = 0;
	private String createUser;
	private Date createTime;
	private String modifUser;
	private Date modifTime;
	private Org org;

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public User() {

	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", uuid=" + uuid
				+ ", permission=" + permission + ", createUser=" + createUser + ", createTime=" + createTime
				+ ", modifUser=" + modifUser + ", modifTime=" + modifTime + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
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

	public String getModifUser() {
		return modifUser;
	}

	public void setModifUser(String modifUser) {
		this.modifUser = modifUser;
	}

	public Date getModifTime() {
		return modifTime;
	}

	public void setModifTime(Date modifTime) {
		this.modifTime = modifTime;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

}
