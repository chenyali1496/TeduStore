package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;


public class AddressMapperTestCase2 {
	private AbstractApplicationContext ac;
	private AddressMapper addressMapper;

	@Test
	public void countByUid() {
		Integer uid =3;
		Integer count=addressMapper.getCountByUid(uid);
		System.out.println("count="+count);
	}
	
	
@Test
public void insert() {
	Address address=new Address();
	address.setUid(3);
	address.setRecvName("¡ı¿œ ¶");
	Integer rows=addressMapper.insert(address);
	System.out.println("rows="+rows);
}
	
	@Before
	public void doBefore() {
		ac=new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		addressMapper=ac.getBean("addressMapper",AddressMapper.class);
	}
	@After
	public void doAfter() {
		ac.close();
	}
}
