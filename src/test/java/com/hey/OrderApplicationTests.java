package com.hey;

import com.hey.dao.BaseDao;
import com.hey.entity.Image;
import com.hey.entity.OrderDetail;
import com.hey.service.BaseService;
import com.hey.util.ExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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

	//测试中文乱码
	@Autowired
	BaseDao baseDao;
	@Test
	public void test11(){
		String imageUrl = "哈哈哈";
		String imagePath = "吸吸吸";
		String imageMd5 = "嘘嘘嘘";
		Image image = new Image (imageUrl, imagePath, imageMd5);
		baseDao.addImage(image);
	}

	//测试导出
	@Test
	public void test111(){

		//模拟从数据库获取需要导出的数据
		List<OrderDetail> personList = new ArrayList<>();

		//导出操作
//		ExcelUtil.exportExcel(personList,"花名册","草帽一伙",OrderDetail.class,"海贼王.xls",response);
	}

}
