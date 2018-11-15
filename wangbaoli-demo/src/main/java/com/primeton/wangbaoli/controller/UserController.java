package com.primeton.wangbaoli.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.primeton.wangbaoli.config.LogAop;
import com.primeton.wangbaoli.entity.ResponseResult;
import com.primeton.wangbaoli.entity.User;
import com.primeton.wangbaoli.service.IUserService;
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
	IUserService userS;

	/**
	 * 通过用户id查找用户
	 * 
	 * @param id 用户id
	 * @return User 用户数据
	 */
	@ApiOperation("通过用户id查找用户信息")
	@LogAop(name = "/aop/aop.action")
	@GetMapping("/get/{id}")
	public User get(@PathVariable("id") Integer id) {
		return userS.getUser(id);
	}

	/**
	 * 查询所有用户数据
	 * 
	 * @return List<User> 所有用户数据
	 */
	@ApiOperation("查询所有用户数据")
	@LogAop(name = "/aop/aop.action")
	@GetMapping("/query")
	public ResponseResult<List<User>> query(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "0") Integer size) {
		List<User> list = userS.queryUsers(page, size);
		return new ResponseResult<List<User>>(ResponseResult.OK, list);
	}

	/**
	 * 创建用户
	 * 
	 * @param user 用户实体
	 * @return 用户信息
	 */
	@ApiOperation(value = "创建新用户", notes = "用户名不可以重复")
	@ApiImplicitParam(name = "user", value = "用户对象", dataTypeClass = User.class)
	@PostMapping("/create")
	public ResponseResult<User> create(User user) {
		User data = userS.createUser(user);
		return new ResponseResult<User>(ResponseResult.OK, data);
	}

	/**
	 * 通过用户id删除用户
	 * 
	 * @param id 用户id
	 * @return 返回状态
	 */
	@ApiOperation(value = "通过id删除用户")
	@LogAop(name = "/aop/aop.action")
	@DeleteMapping("/remove/{id}")
	public ResponseResult<Void> remove(@PathVariable("id") Integer id) {
		userS.removeUser(id);
		return new ResponseResult<Void>(ResponseResult.OK);
	}

	/**
	 * 用户登录功能
	 * 
	 * @param username 用户名
	 * @param password 用户密码
	 * @return User实体
	 */
	@ApiOperation("实现用户登录")
	@PostMapping("/login")
	public ResponseResult<User> login(String username, String password, HttpSession session) {
		User user = userS.login(username, password);
		session.setAttribute("id", user.getId());
		session.setAttribute("username", user.getUsername());
		session.setAttribute("permission", user.getPermission());
		return new ResponseResult<User>(ResponseResult.OK, user);
	}

	/**
	 * 模糊查找用户信息
	 * 
	 * @param username 用户名
	 * @param page     查找第几页
	 * @param size     每一页多少条信息
	 * @return 查找到的用户信息列表
	 */
	@ApiOperation("通过用户名模糊查找用户信息")
	@LogAop(name = "/aop/aop.action")
	@GetMapping("/getlike")
	public ResponseResult<List<User>> queryByUsername(@RequestParam(value = "username") String username,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "0") Integer size) {
		List<User> data = userS.queryByLikename(username, page, size);
		return new ResponseResult<List<User>>(ResponseResult.OK, data);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user    用户对象
	 * @param session 会话
	 * @return 修改后的用户信息
	 */
	@ApiOperation("修改用户信息")
	@LogAop(name = "/aop/aop.action")
	@PutMapping("/put_user/{id}")
	public ResponseResult<User> modify(User user, HttpSession session, @PathVariable("id") Integer id) {
		User data = userS.modifyUser(user, session, id);
		return new ResponseResult<User>(ResponseResult.OK, data);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param oldpassword 原密码
	 * @param newpassword 新密码
	 * @param session     会话
	 * @return 修改后的对象信息
	 */
	@ApiOperation("修改用户密码")
	@LogAop(name = "/aop/aop.action")
	@PutMapping("/put_password/{id}")
	public ResponseResult<User> modifyPassword(String oldpassword, String newpassword, @PathVariable("id") Integer id,
			HttpSession session) {
		User data = userS.modifyPassword(newpassword, oldpassword, id);
		return new ResponseResult<User>(ResponseResult.OK, data);
	}
}
