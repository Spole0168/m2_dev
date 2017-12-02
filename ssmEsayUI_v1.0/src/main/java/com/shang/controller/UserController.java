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
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IMenuBiz menuBiz;
	//返回結果集合
	Map<String,Object> resMap = new HashMap<String, Object>();
	
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView login(@ModelAttribute("user") User user,Model model) {
    	ModelAndView mav = null;
    	logger.info("login(User user)="+JSON.toJSONString(user));
    	User resultUser = user;
        try {
			resultUser = userBiz.login(user);
		} catch (BaseException e) {
			 resMap.put("currentUser", resultUser);
			resMap.put("msg", e.getMsg());
			mav =  new ModelAndView("/index","resMap",resMap);
			return mav;
		}
        
        //加载权限 显示菜单list
        //暂时加载所有的菜单
        List<Menu> menuList = menuBiz.listEntities(null);
        String menu = getTreeNodeStr(menuList);
        
        logger.info(resultUser.getUsername()+"有菜单："+menu);
        
        //用户存放session 中
        model.addAttribute(resultUser.getUsername(), resultUser);
        
        resMap.put("currentUser", resultUser);
        resMap.put("menu", menu);
        mav = new ModelAndView("/main","resMap",resMap);
        return mav;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView logout(Model model) {
    	//加载权限 显示菜单list
    	resMap.put("currentUser", null);
        resMap.put("menu", "");
    	//用户存放session 中
//    	model.addAttribute(arg0, arg1)
    	return new ModelAndView("/index");
    }
    
    @RequestMapping(value = "/dtree")
    public ModelAndView dtree(){
    	//dTree : 只需要把 含有父子关系的List 逐个 加入Node 即可
		List<Menu> menuList = menuBiz.listEntities(null);
	    return new ModelAndView("dtree","dtree",JSON.toJSON(menuList));
    }
    
    private  String getTreeNodeStr(List<Menu> menuList){
    	List<TreeNode> buildTreeNode = TreeUtils.buildTreeNode(null, menuList);
    	return TreeUtils.createListHtml(buildTreeNode);
    }
}
