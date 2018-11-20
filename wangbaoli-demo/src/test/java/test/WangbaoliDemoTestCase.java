package test;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.primeton.wangbaoli.Application;
import com.primeton.wangbaoli.controller.OrgController;
import com.primeton.wangbaoli.controller.UserController;
import com.primeton.wangbaoli.entity.Org;
import com.primeton.wangbaoli.entity.ResponseResult;
import com.primeton.wangbaoli.entity.User;
import com.primeton.wangbaoli.service.impl.OrgServiceImp;
import com.primeton.wangbaoli.service.impl.UserServiceImp;
@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class WangbaoliDemoTestCase {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private MockHttpSession session;
	@Autowired
	private UserController userController;
	@Autowired 
	private UserServiceImp userService;
	@Autowired
	private OrgServiceImp orgService;
	@Autowired
	private OrgController orgController;
	ResponseResult<User> rr;
	public WangbaoliDemoTestCase() {
		
	}
	@Before
	public void setupSession() {
		session=new MockHttpSession();
		session.setAttribute("id", 14);
	}
	/**
	 * 测试用户登录
	 */
	@Test
	public void testLogin() {
		User user = new User();
		user.setUsername("xumengqi6");
		user.setPassword("password");
		ResponseResult<User> rr= userController.login(user, session);
		Assert.assertEquals(new Integer(1), rr.getState());
	}
	/**
	 * 测试用户注册
	 */
	@Test
	public void testUserCreate() {
		User user = new User();
		user.setUsername("xumengqi7");
		user.setPermission(4);
		user.setPassword("password");
		rr =  userController.createUser(user);
		Assert.assertEquals(new Integer(1), rr.getState());
	}
	/**
	 * 测试修改用户信息
	 */
	@Test
	public void testModifyUserInfo() {
		User user= new User();
		user.setUsername("xumengqi3");
		user.setId(18);
		rr = userController.modifyUser(user, session);
		Assert.assertEquals(new Integer(1), rr.getState());
	}
	/**
	 * 测试修改用户密码
	 */
	@Test
	public void testModifyPassword() {
		String oldpassword = "password";
		String newpassword = "password";
		Integer id = 17;
		rr = userController.modifyPassword(oldpassword, newpassword, id, session);
		Assert.assertEquals(new Integer(1), rr.getState());
	}
	/**=======================组织机构功能测试==========================*/
	/**
	 * 测试组织机构注册功能
	 */
	@Test
	public void testOrgCreate() {
		Org org = new Org();
		org.setOrgname("西山居");
		org.setPreorgcode(4);
		ResponseResult<Org> rr= orgController.createOrg(org, session);
		Assert.assertEquals(new Integer(1), rr.getState());
	}
	/**
	 * 测试删除组织机构功能
	 */
	@Test
	public void testRemoveOrg() {
		ResponseResult<Void> rr= orgController.removeOrg(16);
		Assert.assertEquals(new Integer(1), rr.getState());
	}
	/**
	 * 测试修改组织机构必信息
	 */
	@Test
	public void modifyOrginfo() {
		Org org = new Org();
		org.setOrgname("西山居");
		org.setPreorgcode(4);
		org.setId(16);
		ResponseResult<Org> rr= orgController.modifyOrg(org,session);
		Assert.assertEquals(new Integer(1), rr.getState());
	}
}
