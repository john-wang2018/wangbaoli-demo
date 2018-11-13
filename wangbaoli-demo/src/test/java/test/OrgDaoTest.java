package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.primeton.wangbaoli.Application;
import com.primeton.wangbaoli.dao.OrgMapper;
import com.primeton.wangbaoli.entity.Org;

@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
public class OrgDaoTest {
	@Autowired
	private OrgMapper orgm;
	
	public OrgDaoTest() {
		super();
	}

	@Test
	public void insert() {
		Org org =new Org();
		org.setOrgname("中央指挥部");
		org.setPreorgid(4);
		orgm.create(org);
	}
	

}
