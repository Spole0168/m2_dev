package com.shang.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shang.base.BaseController;
import com.shang.base.common.DgridPage;
import com.shang.base.common.Result;
import com.shang.biz.IMenuBiz;
import com.shang.dal.model.Menu;
import com.utils.HelpUtils;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	
	@Autowired
	private IMenuBiz menuBiz;
	
	//返回結果集合
	Map<String,Object> resMap = new HashMap<String, Object>();
	Result<Menu> res = null; 
	private Menu entity = new Menu();
	
	
	
	
	@RequestMapping(value = "/list")
	public ModelAndView listMenu(){
		return new ModelAndView("menu/listMenu");
	}
	//,produces="text/html;charset=UTF-8"
	@RequestMapping(value = "/queryByPage")
	@ResponseBody
	public Object queryByPage(HttpServletRequest request){
		Map map = HelpUtils.copyRequst2Map(request);
		logger.info("request="+map);
		DgridPage<Menu> resDp11 = new DgridPage<Menu>();
		HelpUtils.copyMap2Bean(map, resDp11);
		HelpUtils.copyMap2Bean(map, entity);
		System.out.println(resDp11);
		int currentPage =    Integer.parseInt(request.getParameter("page"));//获取当前页面号
        int pageSize =    Integer.parseInt(request.getParameter("rows"));//获取当前页面多少条数据
        DgridPage<Menu> resDp = menuBiz.queryPageEntities(new DgridPage<Menu>(currentPage, pageSize), null);
        return JSON.toJSON(resDp);
	}
	
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request){
		String id = request.getParameter("id");
		Menu menu = null;
		if(StringUtils.isNotEmpty(id)){
			menu = menuBiz.getEntity(id);
		}
		return new ModelAndView("menu/editMenu","entity",menu);
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map save(HttpServletRequest request){
		Map map = HelpUtils.copyRequst2Map(request);
		Menu menu = new Menu();
		HelpUtils.copyMap2Bean(map, menu);
//		String id = request.getParameter("id");
		if(StringUtils.isNotEmpty(menu.getId())){
			menuBiz.updEntity(menu);
		}else{
			menuBiz.addEntity(menu);
		}
		resMap.put("code", "0000");
		return resMap;
	}
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request){
		String id = request.getParameter("id");
		menuBiz.delEntity(id);
		res = new Result<Menu>();
		return JSON.toJSON(res);
	}
	@RequestMapping(value = "/export")
	@ResponseBody
	public  Object export(HttpServletRequest request,HttpServletResponse response){
		String cols = request.getParameter("columnFields");
		String[] titles = cols.split("-");
		try {
			String fileName=new String(("Menus "+ new SimpleDateFormat("yyyy-MM-dd HHmmss").format(new Date())).getBytes(),"UTF-8");
			response.setContentType("application/binary;charset=UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName+".xlsx");
			XSSFWorkbook exportList2Excel = menuBiz.export(null, titles);
			ServletOutputStream out=response.getOutputStream();
			exportList2Excel.write(out);
			out.flush();
			out.close();
			return JSON.toJSON(new Result<>());
		} catch (Exception e) {
			return JSON.toJSON(new Result<>("导出信息失败"));
		}
	}
	
	
}
