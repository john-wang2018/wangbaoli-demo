package com.primeton.wangbaoli.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.primeton.wangbaoli.dao.OrgMapper;
import com.primeton.wangbaoli.entity.Org;
import com.primeton.wangbaoli.exception.enums.ErrorEnum;
import com.primeton.wangbaoli.exception.service.DemoException;
import com.primeton.wangbaoli.service.IOrgService;
import com.primeton.wangbaoli.service.IUserService;
import com.primeton.wangbaoli.util.ServiceValidator;

/**
 * 组织机构服务层实现类，实现组织机构的增删改查功能。
 * 
 * @author root
 *
 */
@Service
@Transactional
public class OrgServiceImp implements IOrgService {
	@Autowired
	private OrgMapper orgm;
	private IUserService userS;

	public OrgServiceImp() {

	}

	@Override
	public Org createOrg(Org org, HttpSession session) {
		String orgname = org.getOrgname();
		Integer preorgid = org.getPreorgid();
		ServiceValidator.checkInfoIsNull(org, preorgid);
		ServiceValidator.checkUserRepeat(!(orgm.getByName(orgname) == null));
		Date now = new Date();
		String createUser = (String) session.getAttribute("username");
		org.setCreateuser(createUser);
		org.setCreatedate(now);
		org.setModifuser(createUser);
		org.setModifdate(now);
		orgm.insertOrg(org);
		return org;
	}

	@Override
	public void removeOrg(Integer id) {
		ServiceValidator.checkIdEextis(orgm.getOrg(id) == null);
		orgm.deleteOrg(id);
		userS.modifyOrgIdIsNull(id);

		ServiceValidator.checkIdEextisff(orgm.getOrg(id) != null);
	}

	@Override
	public Org modifyOrg(Org org, HttpSession session, Integer id) {
		ServiceValidator.checkInfoIsNull(id);
		String orgname = org.getOrgname();
		Org data = orgm.getOrg(id);
		if (orgm.getByName(orgname) == null) {
			orgm.updateOrg(org);
		} else {
			if (data.getId().equals(id)) {
				org.setOrgname(null);
				orgm.updateOrg(org);
			} else {
				ServiceValidator.checkUserRepeat(true);
			}
		}
		data = orgm.getOrg(id);
		return data;
	}

	@Override
	public Org getOrg(Integer id) {
		ServiceValidator.checkInfoIsNull(id);
		Org data = orgm.getOrg(id);
		if (data == null) {
			throw new DemoException(ErrorEnum.ERROR_ORG_ID);
		}
		return data;
	}

	@Override
	public List<Org> queryByLike(String orgname, Integer page, Integer size) {
		ServiceValidator.checkInfoIsNull(orgname);
		orgname = "%" + orgname + "%";
		PageHelper.startPage(page, size);
		List<Org> list = orgm.queryByLikeName(orgname);
		return list;
	}

	@Override
	public List<Org> queryOrgs(Integer page, Integer size) {
		PageHelper.startPage(page, size);
		List<Org> list = orgm.queryOrgs();
		return list;
	}

}
