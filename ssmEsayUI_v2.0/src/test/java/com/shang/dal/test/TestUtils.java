package com.shang.dal.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.shang.dal.model.Menu;
import com.utils.HelpUtils;

public class TestUtils {
	
	@Test
	public void test_Date(){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd hhmmss").format(new Date()));
	}
	@Test
	public void test_copyMap2Bean(){
		Menu m = new Menu();
		Map map =new HashMap<>();
		map.put("id", "123213");
		HelpUtils.copyMap2Bean(map, m);
		System.out.println(JSON.toJSON(m));
	}
}
