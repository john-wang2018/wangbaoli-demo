package com.primeton.wangbaoli.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.primeton.wangbaoli.demo.entity.ResponseResult;
import com.primeton.wangbaoli.demo.entity.User;
import com.primeton.wangbaoli.demo.exception.DemoException;
import com.primeton.wangbaoli.demo.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户控制层，实现用户的增删改查
 * 
 * @author root
 *
 */
@RestController
@RequestMapping("/api/users")
@Api("服务端的控制层")
public class UserController {

	@Autowired
	IUserService userService;

	/**
	 * 通过用户id查找用户
	 * 
	 * @param id 用户id
	 * @return User 用户数据
	 * @throws DemoException
	 */
	@ApiOperation("通过用户id查找用户信息")
	@GetMapping("/{id}")
	public ResponseResult<User> getUser(@PathVariable("id") Integer id) throws DemoException {
		return new ResponseResult<User>(ResponseResult.OK, userService.getUser(id));
	}

	/**
	 * 查询所有用户数据
	 * 
	 * @return List<User> 所有用户数据
	 * @throws DemoException
	 */
	@ApiOperation("查询所有用户数据")
	@GetMapping("/")
	public ResponseResult<PageInfo<User>> queryUsers(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "0") Integer size) throws DemoException {
		PageInfo<User> list = userService.queryUsers(page, size);
		return new ResponseResult<PageInfo<User>>(ResponseResult.OK, list);
	}

	/**
	 * 创建用户
	 * 
	 * @param user 用户实体
	 * @return 用户信息
	 * @throws DemoException
	 */
	@ApiOperation(value = "创建新用户", notes = "用户名不可以重复")
	@ApiImplicitParam(name = "user", value = "用户对象", dataTypeClass = User.class)
	@PostMapping("/")
	public ResponseResult<User> createUser(@RequestBody User user) throws DemoException {
		User data = userService.createUser(user);
		return new ResponseResult<User>(ResponseResult.OK, data);
	}

	/**
	 * 通过用户id删除用户
	 * 
	 * @param id 用户id
	 * @return 返回状态
	 * @throws DemoException
	 */
	@ApiOperation(value = "通过id删除用户")
	@DeleteMapping("/{id}")
	public ResponseResult<Void> removeUser(@PathVariable("id") Integer id) throws DemoException {
		Integer state = userService.removeUser(id);
		if (state == 1)
			return new ResponseResult<Void>(ResponseResult.OK);
		return new ResponseResult<Void>(ResponseResult.ERR);
	}

	/**
	 * 用户登录功能
	 * 
	 * @param username 用户名
	 * @param password 用户密码
	 * @return User实体
	 * @throws DemoException
	 */
	@ApiOperation("实现用户登录")
	@PostMapping("/actions/login")
	public ResponseResult<User> login(@RequestBody User user, HttpSession session) throws DemoException {
		User userInfo = userService.login(user);
		session.setAttribute("id", userInfo.getId());
		session.setAttribute("username", userInfo.getUserName());
		session.setAttribute("permission", userInfo.getPermission());
		return new ResponseResult<User>(ResponseResult.OK, userInfo);
	}

	/**
	 * 实现用户登出功能
	 * 
	 * @param session 服务器与客户端之间的会话
	 * @return
	 */
	public ResponseResult<Void> lognout(HttpSession session) {
		session.invalidate();
		return new ResponseResult<Void>(ResponseResult.OK);
	}

	/**
	 * 模糊查找用户信息
	 * 
	 * @param username 用户名
	 * @param page     查找第几页
	 * @param size     每一页多少条信息
	 * @return 查找到的用户信息列表
	 * @throws DemoException
	 */
	@ApiOperation("通过用户名模糊查找用户信息")
	@GetMapping("/actions/query/users/name")
	public ResponseResult<PageInfo<User>> queryUsersByUsername(@RequestParam(value = "username",defaultValue="") String username,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "0") Integer size) throws DemoException {
		System.err.println(username);
		PageInfo<User> data = userService.queryByLikeName(username, page, size);
		return new ResponseResult<PageInfo<User>>(ResponseResult.OK, data);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user    用户对象
	 * @param session 会话
	 * @return 修改后的用户信息
	 * @throws DemoException
	 */
	@ApiOperation("修改用户信息")
	@PutMapping("/{id}")
	public ResponseResult<User> modifyUser(@RequestBody User user, HttpSession session) throws DemoException {
		System.err.println("修改用户信息：" + user.getId());
		User data = userService.modifyUser(user, session);
		return new ResponseResult<User>(ResponseResult.OK, data);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param oldpassword 原密码
	 * @param newpassword 新密码
	 * @param session     会话
	 * @return 修改后的对象信息
	 * @throws DemoException
	 */
	@ApiOperation("修改用户密码")
	@PostMapping("/actions/password")
	public ResponseResult<User> modifyPassword(@RequestBody User user, HttpSession session) throws DemoException {
		User data = userService.modifyPassword(user, session);
		return new ResponseResult<User>(ResponseResult.OK, data);
	}

	/**
	 * 通过用户名查找用户信息
	 * 
	 * @param username 用户名
	 * @return 用户信息
	 * @throws DemoException
	 */
	@ApiOperation("通过用户名查找用户信息")
	@GetMapping("/actions/get/{userName}")
	public ResponseResult<User> getUserByName(@PathVariable("userName") String userName) throws DemoException {
		User user = userService.getUserByUserName(userName);
		return new ResponseResult<User>(ResponseResult.OK, user);
	}
	/**
	 * 通过组织机构id查找改机构下所有的用户信息
	 * @param orgId 组织机构id
	 * @param page 第几页
	 * @param size 每一页的条数
	 * @return 该组织所有的人员信息
	 * @throws DemoException
	 */
	@GetMapping("/actions/query/users/{orgId}")
	public ResponseResult<PageInfo<User>> queryUsersByOrgId(@PathVariable("orgId")Integer orgId,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "0") Integer size) throws DemoException{
		return new ResponseResult<PageInfo<User>>(ResponseResult.OK,userService.queryUserByOrgId(orgId,page,size));
	}
}
