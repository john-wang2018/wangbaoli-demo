package com.primeton.wangbaoli.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.primeton.wangbaoli.dao.OrgMapper;
import com.primeton.wangbaoli.dao.UserMapper;
import com.primeton.wangbaoli.entity.User;
import com.primeton.wangbaoli.exception.service.DemoException;
import com.primeton.wangbaoli.service.IUserService;
import com.primeton.wangbaoli.util.ServiceValidator;
import org.springframework.transaction.annotation.Transactional;
/**
 * 用户服务层实现类，实现用户的增删改查。
 * @author root
 *
 */
@Service
@Transactional
public class UserServiceImp implements IUserService {
	@Autowired
	UserMapper userm;
	@Autowired
	OrgMapper orgm;

	public UserServiceImp() {
	}

	public User getUser(Integer id)  throws DemoException{
		ServiceValidator.checkIdEextis(userm.getUser(id)==null);
		User user=userm.getUser(id);
		user.setPassword(null);
		return user;
	}

	@Override
	public User createUser(User user)  throws DemoException{
		String username = user.getUsername();
		String password = user.getPassword();
		ServiceValidator.checkInfoIsNull(username, password);
		ServiceValidator.checkUsername(username);
		ServiceValidator.checkPassword(password);
		ServiceValidator.checkUserRepeat(!(userm.getByName(username)==null));
		String uuid = UUID.randomUUID().toString();
		String md5Password = getMd5Password(password, uuid);
		user.setPassword(md5Password);
		user.setUuid(uuid);
		Date now = new Date();
		user.setCreateUser(user.getUsername());
		user.setCreateTime(now);
		user.setModifUser(user.getUsername());
		user.setModifTime(now);
		userm.insertUser(user);
		return user;
	}

	@Override
	@Transactional
	public void removeUser(Integer id)  throws DemoException{
		ServiceValidator.checkIdEextis(userm.getUser(id)==null);
		userm.deleteUser(id);
		ServiceValidator.checkIdEextisff(userm.getUser(id)!=null);
	}

	@Override
	public User modifyUser(User user,HttpSession session,Integer id)  throws DemoException{
		ServiceValidator.checkInfoIsNull(id);
		String username=user.getUsername();
		User data=userm.getUser(id);
		user.setId(id);
		if(userm.getByName(username)==null) {
			userm.updateUser(user);
		}else {
			if(data.getId().equals(id)) {
				user.setUsername(null);
				
				userm.updateUser(user);
			}
			else {
				ServiceValidator.checkUserRepeat(true);
			}
		}
		data=userm.getUser(id);
		return data;
	}

	@Override
	public List<User> queryUsers(Integer page,Integer size)  throws DemoException{
		PageHelper.startPage(page, page);
		List<User> users = (List<User>) userm.queryUsers();
		return users;
	}
	/**
	 * 生成加密后的密码
	 * @param password 密码
	 * @param salt 盐值
	 * @return 加密后的密码
	 */
	public String getMd5Password(String password, String salt) {
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		salt = DigestUtils.md5DigestAsHex(salt.getBytes()
				);
		return DigestUtils.md5DigestAsHex((password + salt).getBytes());
	}

	@Override
	public User login(String username, String password)  throws DemoException{
		ServiceValidator.checkInfoIsNull(username,password);
		User data=userm.getByName(username);
		ServiceValidator.checkUsernameNotFound(data==null);
		User user = data;
		String salt=user.getUuid();
		password=getMd5Password(password, salt);
		ServiceValidator.checkPasswordErr(!user.getPassword().equals(password));
		user.setPassword(null);
		return user;
	}

	
	@Override
	public List<User> queryByLikename(String username,Integer page,Integer size)  throws DemoException{
		PageHelper.startPage(page, size);
		 return userm.queryLikeName("%"+username+"%");
	}

	@Override
	public User modifyPassword(String newPassword, String oldPassword, Integer id)  throws DemoException{
		User data= userm.getUser(id);
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
	public List<User> modifyOrgIdIsNull(Integer orgid)  throws DemoException{
		ServiceValidator.checkInfoIsNull(orgid);
		List<User> data=userm.queryByOrgid(orgid);
		userm.updateOrgidNull(orgid);
		for(User u:data) {
			u.setOrgid(null);
		}
		return  data;
	} 

}
