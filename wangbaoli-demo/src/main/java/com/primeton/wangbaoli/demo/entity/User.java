package com.primeton.wangbaoli.demo.entity;

import java.util.Date;

/**
 * 用户实体类
 * 
 * @author root
 *
 */
public class User {
	private Integer id;
	private String userName;
	private Integer orgId;
	private String password;
	private String newPassword;
	private String seed;
	private Integer permission = 0;
	private String createUser;
	private Date createTime;
	private String modifyUser;
	private Date modifyTime;
	
	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", orgId=" + orgId + ", password=" + password + ", seed="
				+ seed + ", permission=" + permission + ", createUser=" + createUser + ", createTime=" + createTime
				+ ", modifyUser=" + modifyUser + ", modifyTime=" + modifyTime + ", org=" + org + "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSeed() {
		return seed;
	}
	public void setSeed(String seed) {
		this.seed = seed;
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
	public Org getOrg() {
		return org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	private Org org;

	

}
