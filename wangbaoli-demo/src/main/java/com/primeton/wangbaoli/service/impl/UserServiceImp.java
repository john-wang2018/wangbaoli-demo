package com.primeton.wangbaoli.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.wangbaoli.dao.OrgMapper;
import com.primeton.wangbaoli.dao.UserMapper;
import com.primeton.wangbaoli.entity.Org;
import com.primeton.wangbaoli.entity.User;
import com.primeton.wangbaoli.service.IUserService;
import com.primeton.wangbaoli.util.ServiceValidator;

@Service
public class UserServiceImp implements IUserService {
	@Autowired
	UserMapper userm;
	@Autowired
	OrgMapper orgm;

	public UserServiceImp() {
	}

	public User get(Integer id) {
		ServiceValidator.checkIdEextis(userm.get(id)==null);
		User user=userm.get(id);
		user.setPassword(null);
		return user;
	}

	@Override
	public User createUser(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		ServiceValidator.checkInfoIsNull(username, password);
		ServiceValidator.checkUsername(username);
		ServiceValidator.checkPassword(password);
		ServiceValidator.checkUserRepeat(!(userm.getByUsername(username)==null));
		String uuid = UUID.randomUUID().toString();
		String md5Password = getMd5Password(password, uuid);
		user.setPassword(md5Password);
		user.setUuid(uuid);
		Date now = new Date();
		user.setCreateUser(user.getUsername());
		user.setCreateTime(now);
		user.setModifUser(user.getUsername());
		user.setModifTime(now);
		userm.insert(user);
		return user;
	}

	@Override
	@Transactional
	public void removeUser(Integer id){
		ServiceValidator.checkIdEextis(userm.get(id)==null);
		userm.delete(id);
		ServiceValidator.checkIdEextisff(userm.get(id)!=null);
	}

	@Override
	public User modify(User user,HttpSession session) {
		Integer id=(Integer)session.getAttribute("id");
		ServiceValidator.checkInfoIsNull(id);
		String username=user.getUsername();
		User data=userm.get(id);
		user.setId(id);
		if(userm.getByUsername(username)==null) {
			userm.update(user);
		}else {
			if(data.getId().equals(id)) {
				user.setUsername(null);
				
				userm.update(user);
			}
			else {
				ServiceValidator.checkUserRepeat(true);
			}
		}
		data=userm.get(id);
		return data;
	}

	@Override
	public List<User> queryUsers(Integer page,Integer size) {
		PageHelper.startPage(page, page);
		List<User> users = (List<User>) userm.query();
		return users;
	}

	public String getMd5Password(String password, String salt) {
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		salt = DigestUtils.md5DigestAsHex(salt.getBytes()
				);
		return DigestUtils.md5DigestAsHex((password + salt).getBytes());
	}

	@Override
	public User login(String username, String password) {
		User data=userm.getByUsername(username);
		ServiceValidator.checkUsernameNotFound(data==null);
		User user = data;
		String salt=user.getUuid();
		password=getMd5Password(password, salt);
		ServiceValidator.checkPasswordErr(!user.getPassword().equals(password));
		user.setPassword(null);
		return user;
	}

	

	public List<User> getByUsername(String username,Integer page,Integer size) {
		PageHelper.startPage(page, size);
		 return userm.queryLike("%"+username+"%");
	}

	@Override
	public User modifyPassword(String newPassword, String oldPassword, Integer id) {
		User data= userm.get(id);
		ServiceValidator.checkInfoIsNull(newPassword,oldPassword,id);
		ServiceValidator.checkPassword(newPassword);
		String salt=data.getUuid();
		oldPassword =getMd5Password(oldPassword, salt);
		String password=data.getPassword();
		System.err.println(password);
		ServiceValidator.checkPasswordErr(!password.equals(oldPassword));
		newPassword = getMd5Password(newPassword, salt);
		userm.updatePassword(id, newPassword);
		data.setPassword(null);
		return data;
	}

	@Override
	public List<User> modifyOrgIdIsNull(Integer orgid) {
		ServiceValidator.checkInfoIsNull(orgid);
		List<User> data=userm.queryList(orgid);
		userm.updateByorgidIsNull(orgid);
		for(User u:data) {
			u.setOrgid(null);
		}
		return  data;
	} 

}
