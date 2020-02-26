package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.District;
import cn.tedu.store.entity.Product;


public class ProductMapperTestCase4 {
	private AbstractApplicationContext ac;
	private ProductMapper productMapper;

	
	
	@Test
	public void getList() {
		String where=null;
		String orderBy="priority DESC";
		Integer offset=0;
		Integer count=4;
		List<Product> productList=productMapper.getList(where, orderBy, offset, count);
		System.out.println("List("+productList.size()+")");
		for(Product product:productList) {
			System.out.println(product);
		}
		System.out.println("End.");
	}
	
	@Before
	public void doBefore() {
		ac=new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		productMapper=ac.getBean("productMapper",ProductMapper.class);
	}
	@After
	public void doAfter() {
		ac.close();
	}
}
