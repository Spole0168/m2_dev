package com.shang.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shang.base.BaseController;
import com.shang.base.BaseException;
import com.shang.base.common.Result;
import com.shang.biz.IMenuBiz;
import com.shang.biz.IUserBiz;
import com.shang.dal.model.Menu;
import com.shang.dal.model.User;
import com.shang.menu.TreeNode;
import com.utils.HelpUtils;
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
	
    @RequestMapping(value = "/login")
    @ResponseBody
    public ModelAndView login(HttpServletRequest req, HttpServletResponse res,Model model) {
    	ModelAndView mav = null;
    	Map map = HelpUtils.copyRequst2Map(req);
    	User resultUser = new User();
		HelpUtils.copyMap2Bean(map, resultUser);
    	logger.info("login(User user)="+JSON.toJSONString(resultUser));
        try {
			resultUser = userBiz.login(resultUser);
		} catch (BaseException e) {
			resMap.put("currentUser", resultUser);
			resMap.put("msg", e.getMsg());
			mav =  new ModelAndView("redirect:logout.do","resMap",resMap);
			return mav;
		}
        
        //加载权限 显示菜单list
        //暂时加载所有的菜单
        List<Menu> menuList = menuBiz.listEntities(null);
        String menu = getTreeNodeStr(menuList);
        
        logger.info(resultUser.getUsername()+"有菜单："+menu);
        
        //用户存放session 中
        req.getSession().setAttribute("currentUser", resultUser);
        
        resMap.put("currentUser", resultUser);
        resMap.put("menu", menu);
        mav = new ModelAndView("/main","resMap",resMap);
        return mav;
    }
    
    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	//移除session 中登录用户
    	req.getSession().removeAttribute("currentUser");
    	//用户存放session 中
//    	model.addAttribute(arg0, arg1)
//        res.sendRedirect("/index.jsp");//= /ssu/user/index.jsp
        req.getRequestDispatcher("/index.jsp").forward(req,res);// /ssu/user/index.jsp
//    	return new ModelAndView("redirect:logout");
    }
    
    @RequestMapping(value = "/getCheckCodeImage")
	@ResponseBody
	public void getCheckCodeImage(HttpServletRequest request, HttpServletResponse response){
		logger.info("getCheckCodeImage request=" + JSON.toJSONString(request.getParameterMap()));
		 String[] codeSequence = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J","K", "L",   
		    		"M", "N",  "P", "Q", "R", "S", "T", "U", "V", "W","X", "Y",   
		    		"Z",  "1", "2", "3", "4", "5", "6", "7", "8", "9","0" }; 
		int width = 100;
        int height = 37;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman",0,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        String strCode = "";
        for(int i=0;i<6;i++){
            int num = random.nextInt(35);  
            strCode += codeSequence[num];  
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(codeSequence[num], 13*i+6, 28);
        }
        g.dispose();
        //将字符保存到session中用于前端的验证  strCode

        try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
			response.getOutputStream().flush();
		} catch (IOException e) {
			logger.error("ImageIO.write(image, JPEG, response.getOutputStream()) E="+e);
			return;
		}

	}
	private Color getRandColor(int fc,int bc){
	        Random random = new Random();
	        if(fc>255)
	            fc = 255;
	        if(bc>255)
	            bc = 255;
	        int r = fc + random.nextInt(bc - fc);
	        int g = fc + random.nextInt(bc - fc);
	        int b = fc + random.nextInt(bc - fc);
	        return new Color(r,g,b);
	}
    
	@RequestMapping(value = "/isExist")
	@ResponseBody
    public Result isExist(HttpServletRequest request,HttpServletResponse response){
		Map map = HelpUtils.copyRequst2Map(request);
		User user = new User();
		HelpUtils.copyMap2Bean(map, user);
		List<User> listUsers = userBiz.listEntities(user);
		if(null!=listUsers||listUsers.size()!=0){
			return new Result<>("该用户信息已存在");
		}
		return new Result<>();
    }
	
	@RequestMapping(value = "/register")
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response){
		Map map = HelpUtils.copyRequst2Map(request);
		User user = new User();
		HelpUtils.copyMap2Bean(map, user);
		userBiz.addEntity(user);
		//跳转登录界面
		return new ModelAndView("redirect:logout.do");
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
