package com.primeton.wangbaoli.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.primeton.wangbaoli.demo.dao.OrgMapper;
import com.primeton.wangbaoli.demo.dao.UserMapper;
import com.primeton.wangbaoli.demo.entity.User;
import com.primeton.wangbaoli.demo.exception.DemoException;
import com.primeton.wangbaoli.demo.service.IUserService;
import com.primeton.wangbaoli.demo.util.ServiceValidator;

import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务层实现类，实现用户的增删改查。
 * 
 * @author root
 *
 */
@Service
@Transactional
public class UserServiceImp implements IUserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	OrgMapper orgMapper;

	public UserServiceImp() {
	}

	public User getUser(Integer id) throws DemoException {
		ServiceValidator.checkIdExist(userMapper.getUser(id) == null);
		User user = userMapper.getUser(id);
		user.setPassword(null);
		return user;
	}

	@Override
	public User createUser(User user) throws DemoException {

		String userName = user.getUserName();
		String password = user.getPassword();
		ServiceValidator.checkInfoIsNull(userName, password);
		ServiceValidator.checkUsername(userName);
		ServiceValidator.checkPassword(password);
		ServiceValidator.checkUserRepeat(!(userMapper.getUserByUserName(userName) == null));
		String uuid = UUID.randomUUID().toString();
		String md5Password = getMd5Password(password, uuid);
		user.setPassword(md5Password);
		user.setSeed(uuid);
		Date now = new Date();
		user.setCreateUser(user.getUserName());
		user.setCreateTime(now);
		user.setModifyUser(user.getUserName());
		user.setModifyTime(now);
		userMapper.insertUser(user);
		return user;
	}

	@Override
	@Transactional
	public Integer removeUser(Integer id) throws DemoException {
		ServiceValidator.checkIdExist(userMapper.getUser(id) == null);
		return userMapper.deleteUser(id);
	}

	@Override
	public User modifyUser(User user, HttpSession session) throws DemoException {
		Integer id = user.getId();
		ServiceValidator.checkInfoIsNull(id);
		ServiceValidator.checkIdExist(userMapper.getUser(id) == null);
		String userName = user.getUserName();
		User data = userMapper.getUser(id);
		user.setId(id);
		if (userMapper.getUserByUserName(userName) == null) {
			userMapper.updateUser(user);
		} else {
			if (data.getId().equals(id)) {
				user.setUserName(null);

				userMapper.updateUser(user);
			} else {
				ServiceValidator.checkUserRepeat(true);
			}
		}
		data = userMapper.getUser(id);
		return data;
	}

	@Override
	public List<User> queryUsers(Integer page, Integer size) throws DemoException {
		PageHelper.startPage(page, size);
		List<User> users = (List<User>) userMapper.queryUsers();
		return users;
	}

	/**
	 * 生成加密后的密码
	 * 
	 * @param password 密码
	 * @param salt     盐值
	 * @return 加密后的密码
	 */
	public String getMd5Password(String password, String salt) {
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		salt = DigestUtils.md5DigestAsHex(salt.getBytes());
		return DigestUtils.md5DigestAsHex((password + salt).getBytes());
	}

	@Override
	public User login(User user) throws DemoException {

		String userName = user.getUserName();
		String password = user.getPassword();
		ServiceValidator.checkInfoIsNull(userName, password);
		User userInfo = userMapper.getUserByUserName(userName);
		ServiceValidator.checkUsernameNotFound(userInfo == null);
		User responceUser = userInfo;
		String salt = userInfo.getSeed();
		password = getMd5Password(password, salt);
		ServiceValidator.checkPasswordErr(!userInfo.getPassword().equals(password));
		responceUser.setPassword(null);
		responceUser.setSeed(null);
		return responceUser;
	}

	@Override
	public List<User> queryByLikename(String userName, Integer page, Integer size) throws DemoException {
		PageHelper.startPage(page, size);
		return userMapper.queryUsersByKeyWord("%" + userName + "%");
	}

	@Override
	public User modifyPassword(User user, HttpSession session) throws DemoException {
		String newPassword = user.getNewPassword();
		String oldPassword = user.getPassword();
		Integer id = user.getId();
		ServiceValidator.checkInfoIsNull(newPassword, oldPassword, id);
		User data = userMapper.getUser(id);
		ServiceValidator.checkPassword(newPassword);
		String salt = data.getSeed();
		oldPassword = getMd5Password(oldPassword, salt);
		String password = data.getPassword();
		ServiceValidator.checkPasswordErr(!password.equals(oldPassword));
		newPassword = getMd5Password(newPassword, salt);
		userMapper.updatePassword(id, newPassword);
		data.setPassword(null);
		return data;
	}

	@Override
	public User getUserByUserName(String username) throws DemoException {
		ServiceValidator.checkInfoIsNull(username);
		return userMapper.getUserByUserName(username);
	}

}
