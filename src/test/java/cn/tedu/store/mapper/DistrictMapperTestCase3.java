package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.District;


public class DistrictMapperTestCase3 {
	private AbstractApplicationContext ac;
	private DistrictMapper districtMapper;

	@Test
	public void findDistrictByCode() {
		String code="510000";
		District district=districtMapper.findDistrictByCode(code);
			System.out.println(district);
		
	}
	
	@Before
	public void doBefore() {
		ac=new ClassPathXmlApplicationContext(
				"spring-dao.xml");
		districtMapper=ac.getBean("districtMapper",DistrictMapper.class);
	}
	@After
	public void doAfter() {
		ac.close();
	}
}
