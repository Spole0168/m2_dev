package com.shang.dal.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shang.dal.dao.MenuMapper;
import com.shang.dal.dao.ext.ExtMapper;
import com.shang.dal.model.Menu;
import com.shang.dal.model.MenuExample;
import com.shang.dal.model.User;
import com.shang.dto.MenuDto;
import com.shang.dto.Node;
import com.utils.TreeUtils;

public class TestDal {
	@Autowired
	private ExtMapper extMapper;
	@Autowired
	private MenuMapper menuMapper;
	
	@Before
	public void before() {
		// 使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] {"testConfig/test_spring_mybatis.xml" });
		// 从Spring容器中根据bean的id取出我们要使用的userService对象
		extMapper = (ExtMapper) ac.getBean("extMapper");
		menuMapper = (MenuMapper) ac.getBean("menuMapper");
	}
	
	@Test
	@Transactional  
	@Rollback(true)
	public void test_addMenu(){
		Menu m = new Menu();
		m.setMenuCode("A2");
		m.setMenuLevel(0);
		m.setMenuUrl("url_A2");
		m.setMenuName("2222");
		menuMapper.insertSelective(m);
		
	}
	
	@Test
	public void test_treeNodeslist(){
		List<Menu> list = menuMapper.selectByExample(null);
		System.out.println(list.size());
		List<Node<Menu>> child = TreeUtils.getChild(null, list);
		System.out.println("DB中存在的菜单树信息："+JSON.toJSON(child));
		
		//构造根目录
		Node<Menu> tree = new Node<Menu>();
		Menu m = new Menu();
		m.setMenuName("test_ROOT");
		tree.setChildren(child);
		tree.setT(m);
		System.out.println(JSON.toJSON(tree));
	}
	
	////////扩展Dal测试///////
	@Test
	public void test_queryUserByOrConditions(){
		User u = new User();
		u.setUsername("123");
		List<User> users = extMapper.queryUserByOrConditions(u);
		System.out.println(users.size());
		System.out.println(JSON.toJSON(users));
		
	}
	
	@Test
	public void test_queryMenusByConditions(){
		List<MenuDto> list = extMapper.queryMenusByConditions(null);
		System.out.println(list.size());
		System.out.println(JSON.toJSON(list));
		List<MenuDto> child = MenuDto.getChild(null, list);
		System.out.println("DB中存在的菜单树信息："+JSON.toJSON(child));
		MenuDto root = new MenuDto();
		root.setChildren(child);
		root.setMenuName("root");
		System.out.println(JSON.toJSON(root));
	}
	
	//插件分页
	
	@Test
	public void test_page(){
		MenuExample me = new MenuExample();
		me.setStartRow(1);
		me.setPageSize(5);
		List<Menu> list = menuMapper.selectByExample(me);
		
//		PageHelper.startPage(1,5);
//		List<Menu> list = menuMapper.selectByExample(null);
//		PageInfo<Menu> page = new PageInfo<Menu>(list);
//		System.out.println(page.getTotal());
//		System.out.println(page.getList().size());
//		System.out.println(JSON.toJSON(page.getList()));
//		System.out.println(JSON.toJSON(page.getNextPage()));
	}
}
