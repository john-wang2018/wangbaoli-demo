package com.primeton.wangbaoli.entity;

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
	private String orgname;
	private Integer preorgcode;
	private Integer orgcode;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Integer getPreorgcode() {
		return preorgcode;
	}

	public void setPreorgcode(Integer preorgcode) {
		this.preorgcode = preorgcode;
	}

	public Integer getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(Integer orgcode) {
		this.orgcode = orgcode;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getModifuser() {
		return modifuser;
	}

	public void setModifuser(String modifuser) {
		this.modifuser = modifuser;
	}

	public Date getModifdate() {
		return modifdate;
	}

	public void setModifdate(Date modifdate) {
		this.modifdate = modifdate;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	private String createuser;
	private Date createdate;
	private String modifuser;
	private Date modifdate;
	private List<User> users;

	public Org() {
		super();
	}

	
}
