package cn.tedu.store.test.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.ServiceException;

public class UserServiceTestCase {
	private AbstractApplicationContext ac;
	private IUserService userService;
	
	@Test
	public void changeAvatar() {
	    try {
	        Integer id = 9;
	        String avatar = "234";
	        userService.changeAvatar(id, avatar);
	        System.out.println("上传成功");
	    } catch (ServiceException e) {
	        System.out.println(e.getMessage());
	    }
	}

	
	
	@Test
	public void changeInfo() {
		try {
			User user=new User();
			user.setId(12);
			user.setGender(0);
			user.setPhone("15877698818");
			user.setEmail("8818@12.cn");
			userService.changeInfo(user);
			System.out.println("修改成功");
			}catch (ServiceException e){
				System.out.println(e.getMessage());
			}
	}
	
	
	
	
	@Test
	public void changePassword() {
		try {
			Integer id=12;
			String oldPassword="8888";
			String newPassword="1234";
			Void result=userService.changePassword(id, oldPassword, newPassword);
			System.out.println("修改成功");
			}catch (ServiceException e){
				System.out.println(e.getMessage());
			}
	}
	
	@Test
	public void login() {
		try {
			String username="qwe";
			String password="123";
			User result=userService.login(username,password);
			System.out.println(result);
			}catch (ServiceException e){
				System.out.println(e.getMessage());
			}
	}
	
	
	@Test
	public void reg() {
		try {
		User user=new User();
		user.setUsername("mysql");
		user.setPassword("1234");
		User result=userService.reg(user);
		System.out.println(result);
		}catch (ServiceException e){
			System.out.println(e.getMessage());
		}
	}
	@Before
	public void doBefore() {
		ac=new ClassPathXmlApplicationContext(
				"spring-dao.xml","spring-service.xml");
		userService=ac.getBean("userService",IUserService.class);
	}
	@After
	public void doAfter() {
		ac.close();
	}
}
