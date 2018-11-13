package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.primeton.wangbaoli.Application;
import com.primeton.wangbaoli.dao.UserMapper;
import com.primeton.wangbaoli.entity.User;

@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
public class UserDaoTest {

	@Autowired
	UserMapper userm;
	//@Test
	public void deleteuser() {
		User user=userm.getByUsername("wangbaoli");
		System.err.println(user+""+user.getOrg());
	}
	//@Test
	public void deleteusername(){
		User user=userm.get(1);
		System.err.println(user+""+user.getOrg());
	}
	//@Test
	public void findusername(){
		User user=userm.queryLike("%xu%").get(0);
		System.err.println(user+"");
	}
	@Test
	public void updateuser(){
		User user=new User();
		user.setId(17);
		user.setUsername("mengqiY");
		userm.update(user);
	}

}
