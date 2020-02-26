package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.tedu.store.entity.User;

public class UserMapperTestCase {
	private AbstractApplicationContext ac;
	private UserMapper userMapper;
	
	@Test
	public void updateAvatar() {
	    Integer id = 12;
	    String avatar = "1234";
	    Integer rows = userMapper.updateAvatar(id, avatar);
	    System.out.println("rows=" + rows);
	}
	
	@Test
	public void updateInfo() {
		User user=new User();
		user.setId(11);;
		user.setGender(1);
		user.setPhone("138 9247 8956");
		user.setEmail("123@qwe.qw");
		user.setModifiedTime(new Date());
		Integer rows=userMapper.updateInfo(user);
		System.out.println("user="+user);
	}
	
	@Test
	public void updatePassword() {
		Integer id=11;
		String password="1234";
		Integer rows=userMapper.updatePassword(id, password);
		System.out.println("rows="+rows);
	}
	
	@Test
	public void findUserById() {
		Integer id=11;
		User user=userMapper.findUserById(id);
		System.out.println(user);
				
	}
	@Test
	public void insert() {
		User user=new User();
		user.setUsername("root");
		user.setPassword("1234");
		Integer rows=userMapper.insert(user);
		System.out.println("rows="+rows);
		System.out.println(user);
		
	}
	
	@Test
	public void findUserByUsername() {
		
		String username="root";
		User user=userMapper.findUserByUsername(username);
		System.out.println(user);
				
	}
	
	
	@Before
	public void doBefore() {
		ac=new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		userMapper=ac.getBean("userMapper",UserMapper.class);
	}
	@After
	public void doAfter() {
		ac.close();
	}
}
