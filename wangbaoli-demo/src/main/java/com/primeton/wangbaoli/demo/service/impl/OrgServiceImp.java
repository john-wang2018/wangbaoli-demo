package com.primeton.wangbaoli.demo.service.impl;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.primeton.wangbaoli.demo.dao.OrgMapper;
import com.primeton.wangbaoli.demo.entity.Org;
import com.primeton.wangbaoli.demo.exception.DemoException;
import com.primeton.wangbaoli.demo.exception.enums.ErrorEnum;
import com.primeton.wangbaoli.demo.service.IOrgService;
import com.primeton.wangbaoli.demo.util.ServiceValidator;

/**
 * 组织机构服务层实现类，实现组织机构的增删改查功能。
 * 
 * @author root
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class OrgServiceImp implements IOrgService {
	@Autowired
	private OrgMapper orgMapper;

	public OrgServiceImp() {

	}

	@Override
	public Org createOrg(Org org, HttpSession session) throws DemoException {
		String orgName = org.getOrgName();
		Integer superOrgId = org.getSuperOrgId();
		ServiceValidator.checkInfoIsNull(org, superOrgId);
		ServiceValidator.checkUserRepeat(!(orgMapper.getByName(orgName) == null));
		Date now = new Date();
		String createUser = (String) session.getAttribute("userName");
		org.setCreateUser(createUser);
		org.setCreateTime(now);
		org.setModifyUser(createUser);
		org.setModifyTime(now);
		orgMapper.insertOrg(org);
		return org;
	}

	@Override
	public Integer removeOrg(Integer id) throws DemoException {
		ServiceValidator.checkIdExist(orgMapper.getOrg(id) == null);
		return orgMapper.deleteOrg(id);

	}

	@Override
	public Org modifyOrg(Org org, HttpSession session) throws DemoException {
		Integer id = org.getId();
		ServiceValidator.checkInfoIsNull(id);
		String orgname = org.getOrgName();
		Org orginfo = orgMapper.getOrg(id);
		if (orgMapper.getByName(orgname) == null) {
			orgMapper.updateOrg(org);
		} else {
			if (orginfo.getId().equals(id)) {
				org.setOrgName(null);
				orgMapper.updateOrg(org);
			} else {
				ServiceValidator.checkUserRepeat(true);
			}
		}
		orginfo = orgMapper.getOrg(id);
		return orginfo;
	}

	@Override
	public Org getOrg(Integer id) throws DemoException {
		ServiceValidator.checkInfoIsNull(id);
		Org data = orgMapper.getOrg(id);
		if (data == null) {
			throw new DemoException(ErrorEnum.ERROR_ORG_ID);
		}
		return data;
	}

	@Override
	public List<Org> queryOrgs(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		List<Org> list = orgMapper.queryOrgs();
		return list;
	}

}
