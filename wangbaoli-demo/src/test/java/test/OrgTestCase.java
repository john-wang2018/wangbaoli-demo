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
import org.springframework.transaction.annotation.Transactional;

import com.primeton.wangbaoli.demo.Application;
import com.primeton.wangbaoli.demo.controller.OrgController;
import com.primeton.wangbaoli.demo.dao.OrgMapper;
import com.primeton.wangbaoli.demo.entity.Org;
import com.primeton.wangbaoli.demo.entity.ResponseResult;
import com.primeton.wangbaoli.demo.exception.DemoException;

/**
 * 对组织机构的增删改进行单元测试
 * @author root
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Transactional(rollbackFor=Exception.class)
@AutoConfigureMockMvc
public class OrgTestCase {
	@Autowired
	OrgController orgController;
	@Autowired
	OrgMapper orgMapper;
	MockHttpSession session;
	@Before
	public void initTest() {
		session=new MockHttpSession();
		session.setAttribute("userName", "wangbaoli");
		session.setAttribute("id", 4);
	}
	@Test
	public void testCase() throws DemoException {
		//在测试前先将重复的数据删除
		String orgName="第一实验部";
		String newOrgName="易小川";
		Org orgInfo = orgMapper.getByName(orgName);
		Org newOrgInfo = orgMapper.getByName(newOrgName);
		if(orgInfo!=null) {
		Integer id =orgInfo.getId();
		orgMapper.deleteOrg(id);}
		if(newOrgInfo!=null) {
			Integer id =newOrgInfo.getId();
			orgMapper.deleteOrg(id);}
		//1. 测试增加
		Org org= new Org();
		org.setOrgName("第一实验部");
		org.setSuperOrgId(4);
		ResponseResult<Org> rr = orgController.createOrg(org, session);
		Assert.assertEquals(new Integer(1), rr.getState());
		//2. 测试修改
		Org modifyOrg = new Org();
		modifyOrg.setOrgName("易小川");
		modifyOrg.setSuperOrgId(4);
		modifyOrg.setId(org.getId());
		rr = orgController.modifyOrg(modifyOrg, session);
		Assert.assertEquals(new Integer(1), rr.getState());
		//3. 测试删除
		ResponseResult<Void> newRr = orgController.removeOrg(org.getId());
		Assert.assertEquals(new Integer(1), newRr.getState());
	}

}
