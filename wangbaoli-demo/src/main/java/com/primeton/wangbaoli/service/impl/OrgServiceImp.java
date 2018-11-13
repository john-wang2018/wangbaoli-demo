package com.primeton.wangbaoli.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.primeton.wangbaoli.dao.OrgMapper;
import com.primeton.wangbaoli.entity.Org;
import com.primeton.wangbaoli.entity.User;
import com.primeton.wangbaoli.exception.service.IdNotFoundException;
import com.primeton.wangbaoli.service.IOrgService;
import com.primeton.wangbaoli.service.IUserService;
import com.primeton.wangbaoli.util.ServiceValidator;
@Service
public class OrgServiceImp implements IOrgService{
	@Autowired
	private OrgMapper orgm;
	private IUserService userS;
	public OrgServiceImp() {
		
	}

	@Override
	public Org create(Org org,HttpSession session) {
		String orgname=org.getOrgname();
		Integer preorgid=org.getPreorgid();
		ServiceValidator.checkInfoIsNull(org,preorgid);
		ServiceValidator.checkUserRepeat(!(orgm.getByOrgname(orgname)==null));
		Date now = new Date();
		String createUser=(String)session.getAttribute("username");
		org.setCreateuser(createUser);
		org.setCreatedate(now);
		org.setModifuser(createUser);
		org.setModifdate(now);
		orgm.create(org);
		return org;
	}

	@Override
	public void remove(Integer id) {
		ServiceValidator.checkIdEextis(orgm.get(id)==null);
		orgm.delete(id);
		userS.modifyOrgIdIsNull(id);
		
		ServiceValidator.checkIdEextisff(orgm.get(id)!=null);
	}

	@Override
	public Org modify(Org org,HttpSession session) {
		Integer id=org.getId();
		ServiceValidator.checkInfoIsNull(id);
		String orgname=org.getOrgname();
		Org data=orgm.get(id);
		if(orgm.getByOrgname(orgname)==null) {
			orgm.update(org);
		}else {
			if(data.getId().equals(id)) {
				org.setOrgname(null);
				orgm.update(org);
			}
			else {
				ServiceValidator.checkUserRepeat(true);
			}
		}
		data=orgm.get(id);
		return data;
	}

	@Override
	public Org get(Integer id) {
		ServiceValidator.checkInfoIsNull(id);
		Org data=orgm.get(id);
		if(data==null) {
			throw new IdNotFoundException("组织机构不存在");
		}
		return data;
	}

	@Override
	public List<Org> queryByLike(String orgname,Integer page,Integer size) {
		ServiceValidator.checkInfoIsNull(orgname);
		orgname="%"+orgname+"%";
		PageHelper.startPage(page, size);
		List<Org> list= orgm.queryByOrgname(orgname);
		return list;
	}

	@Override
	public List<Org> query(Integer page,Integer size) {
		PageHelper.startPage(page, size);
		List<Org> list = orgm.query();
		return list;
	}

}
