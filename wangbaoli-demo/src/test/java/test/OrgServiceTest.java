package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.primeton.wangbaoli.Application;
import com.primeton.wangbaoli.entity.Org;
import com.primeton.wangbaoli.service.impl.OrgServiceImp;

@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
public class OrgServiceTest {
	@Autowired
	private OrgServiceImp orgS;
	Org org;
	public OrgServiceTest() {
		
	}
	@Before
	public void innit() {
		org=new Org();
		org.setOrgname("作战指挥部");
		org.setPreorgid(4);
	}
	
}
