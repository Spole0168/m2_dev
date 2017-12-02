package com.shang.dal.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.shang.biz.IMenuBiz;
import com.shang.biz.IStudentBiz;
import com.shang.biz.IUserBiz;
import com.shang.dal.model.Menu;
import com.shang.dal.model.Student;
import com.shang.dto.MenuDto;
import com.shang.dto.Node;
import com.shang.menu.TreeNode;
import com.utils.TreeUtils;

@RunWith(SpringJUnit4ClassRunner.class)
//配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
//@ContextConfiguration(locations = {"classpath:/testConfig/test_spring_mvc.xml", "classpath:/testConfig/test_spring_mybatis.xml" })
@ContextConfiguration(locations = {"classpath:/testConfig/test_spring_mybatis.xml" })
public class TestBiz {
	@Autowired
	IStudentBiz studentBiz;
	@Autowired
	IMenuBiz menuBiz;
	@Autowired
	IUserBiz userBiz;
	
	@Test
	@Transactional  
//	@Rollback(true)
	public void test_addStudent(){
		Student stu = new Student();
		stu.setAge(20);
		stu.setName("BBBBBBBBB");
		studentBiz.insertSelective(stu);
	}
	@Test
	public void test_list(){
		List<Student> listStudents = studentBiz.listStudents(null);
		System.out.println(JSON.toJSON(listStudents));
	}
	@Test
	public void test_addMenu(){
		Menu record = new Menu();
		String name = "C";
		record.setMenuName(name);
//		record.setMenuPid("cd6725b0-cc33-11e7-aba4-f832e4d9210b");
		record.setMenuUrl("url-"+name);
		menuBiz.addEntity(record);
	}
	
	
	
	@Test
	public void test_mTree(){
		List<Menu> menuList = menuBiz.listEntities(null);
	    List<Node<Menu>> child = TreeUtils.getChild(null, menuList);//树形菜单
	    System.out.println(JSON.toJSON(child));
	    Node<Menu> tree = new Node<Menu>();
		Menu m = new Menu();
		m.setMenuName("test_ROOT");
		tree.setChildren(child);
		tree.setT(m);
		System.out.println(JSON.toJSON(tree));
	}
	@Test
	public void test_mDto(){
		List<MenuDto> listDto = userBiz.queryMenusByConditions(null);
    	List<MenuDto> child = MenuDto.getChild(null, listDto);
    	MenuDto root = new MenuDto();
    	root.setMenuName("root");
    	root.setMenuUrl("#");
    	root.setChildren(child);
		System.out.println(JSONObject.toJSONString(root,SerializerFeature.WriteMapNullValue));
	}
	@Test
	public void test_buildTreeNode(){
		List<Menu> menuList = menuBiz.listEntities(null);
	    List<TreeNode> buildTreeNode = TreeUtils.buildTreeNode(null, menuList);
	    System.out.println(JSON.toJSON(buildTreeNode));
	    Node<Menu> tree = new Node<Menu>();
	    TreeNode root = new TreeNode();
	    root.setId("-1");
	    root.setText("root");
	    root.setChildren(buildTreeNode);
		System.out.println(JSON.toJSON(root));
	}
	@Test
	public void test_page(){
		PageInfo<Menu> page = menuBiz.queryPageEntities(1, 5, null);
		System.out.println(page.getTotal());
		System.out.println(page.getList().size());
		System.out.println(JSON.toJSON(page.getList()));
		System.out.println(page.getNextPage());
	}
}
