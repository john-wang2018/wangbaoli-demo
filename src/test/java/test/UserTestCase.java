package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import com.primeton.wangbaoli.demo.Application;
import com.primeton.wangbaoli.demo.controller.UserController;
import com.primeton.wangbaoli.demo.dao.UserMapper;
import com.primeton.wangbaoli.demo.entity.ResponseResult;
import com.primeton.wangbaoli.demo.entity.User;
import com.primeton.wangbaoli.demo.exception.DemoException;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserTestCase {
	MockHttpSession session;
	User user;
	@Autowired
	UserController userController;
	@Autowired
	UserMapper userMapper;
	String password = "password";
	String userName = "wangbaoli23";

	@Before
	public void initTest() {
		session = new MockHttpSession();
		session.setAttribute("userName", "wangbaoli");
		session.setAttribute("id", 14);
		user = new User();
		user.setUserName(userName);
		user.setPassword(password);
	}

	@Test
	public void orgTest() throws DemoException {
		// 删除测试前数据库中可能存在的重复信息
		User userInfo = userMapper.getUserByUserName(userName);
		if (userInfo != null) {
			Integer id = userInfo.getId();
			userMapper.deleteUser(id);
		}
		// 1.测试添加用户
		ResponseResult<User> responsResult = userController.createUser(user);
		Assert.assertEquals(new Integer(1), responsResult.getState());
		// 2.测试用户登录
		user.setPassword(password);
		responsResult = userController.login(user, session);
		Assert.assertEquals(new Integer(1), responsResult.getState());
		// 3.测试修改用户密码
		String newPassword = "passwordd";
		user.setNewPassword(newPassword);
		responsResult = userController.modifyPassword(user, session);
		Assert.assertEquals(new Integer(1), responsResult.getState());
		// 4.测试删除用户
		ResponseResult<Void> responseResultOfDelete = userController.removeUser(user.getId());
		Assert.assertEquals(new Integer(1), responseResultOfDelete.getState());

	}
}
