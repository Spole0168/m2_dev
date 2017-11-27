package com.shang.biz.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.shang.base.BaseBiz;
import com.shang.base.common.DgridPage;
import com.shang.biz.IMenuBiz;
import com.shang.dal.dao.MenuMapper;
import com.shang.dal.model.Menu;
import com.shang.dal.model.MenuExample;
import com.shang.dal.model.MenuExample.Criteria;
import com.utils.ExcelsUtils;

@Service("menuBiz")  
public class MenuBizImpl extends BaseBiz<Menu> implements IMenuBiz{
	
	@Autowired
	private MenuMapper menuMapper;
	
	
	@Override
	public void addEntity(Menu obj) {
		menuMapper.insertSelective(obj);
		
	}

	@Override
	public void delEntity(String id) {
		menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updEntity(Menu obj) {
		menuMapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public List<Menu> listEntities(Menu obj) {
		//设置查询条件
		MenuExample me = new MenuExample();
		Criteria criteria = me.createCriteria();
		if(null != obj){
			String menuName = obj.getMenuName();
			if(StringUtils.isNotEmpty(menuName)){
				criteria.andMenuNameLike(menuName);
			}
		}
		
		return menuMapper.selectByExample(me);
	}

	@Override
	public Menu getEntity(String id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public DgridPage<Menu> queryPageEntities(DgridPage<Menu> dp, Menu record) {
		DgridPage<Menu> resDp = new DgridPage<>();
		//设置查询条件
		MenuExample me = new MenuExample();
		if(null!=dp){
			BeanUtils.copyProperties(dp, resDp);
			BeanUtils.copyProperties(dp, me);
		}
		Criteria criteria = me.createCriteria();
		
		if(null != record){
			String menuName = record.getMenuName();
			if(StringUtils.isNotEmpty(menuName)){
				criteria.andMenuNameLike(menuName);
			}
		}
		List<Menu> rows = menuMapper.selectByExample(me);
		long total = menuMapper.countByExample(me);
		resDp.setRows(rows);
		resDp.setTotal(total);
		return resDp;
	}

	@Override
	public XSSFWorkbook export(Menu record, String[] titles) {
		MenuExample me = new MenuExample();
		Criteria criteria = me.createCriteria();
		if(null != record){
			String menuName = record.getMenuName();
			if(StringUtils.isNotEmpty(menuName)){
				criteria.andMenuNameLike(menuName);
			}
		}
		List<Menu> rows = menuMapper.selectByExample(me);
		XSSFWorkbook exportList2Excel = ExcelsUtils.exportList2Excel(titles, JSON.parseArray(JSON.toJSONString(rows)));
		return exportList2Excel;
	}

}
