package com.hey;

import com.github.pagehelper.Page;
import com.hey.entity.User;
import com.hey.result.MultiResult;
import com.hey.result.ResultPageInfo;
import com.hey.service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

	@Autowired
	private BaseService baseService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testPageHelper(){
		//测试已成功
		System.out.println(baseService.findUserByTel(1,2,"222"));
	}

}
