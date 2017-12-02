package com.shang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shang.base.BaseController;
import com.shang.base.BaseException;
import com.shang.biz.IMenuBiz;
import com.shang.biz.IUserBiz;
import com.shang.dal.model.Menu;
import com.shang.dal.model.User;
import com.shang.menu.TreeNode;
import com.utils.TreeUtils;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	
	@Autowired
	private IMenuBiz menuBiz;
	
	//返回結果集合
	Map<String,Object> resMap = new HashMap<String, Object>();
	
	@RequestMapping(value = "/listMenu")
	public ModelAndView listMenu(){
		return new ModelAndView("menu/listMenu");
	}
	@RequestMapping(value = "/listMenus")
	public Object listMenus(){
		List<Menu> list = menuBiz.listEntities(null);
		return JSON.toJSON(list);
	}
	
	
}
