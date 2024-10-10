package com.itwillbs.controller;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {

	
	@Inject
	private DataSource ds;
	
	@Test
	public void 디비연결테스트() {
		
		System.out.println("디비 연결 : "+ds);
		
	}
	
	
	
	
	
}
