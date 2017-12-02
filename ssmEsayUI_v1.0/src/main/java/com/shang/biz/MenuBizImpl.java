package com.shang.biz;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.shang.base.BaseBiz;
import com.shang.dal.dao.MenuMapper;
import com.shang.dal.model.Menu;
import com.shang.dal.model.MenuExample;
import com.shang.dal.model.MenuExample.Criteria;

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
	public PageInfo<Menu> queryPageEntities(int currentPage, int pageSize,
			Menu t) {
		PageInfo<Menu> page = super.queryPageEntities(currentPage, pageSize, t);
		List<Menu> list = this.listEntities(null);
		page.setList(list);
//		System.out.println(page.getTotal());
//		System.out.println(page.getList().size());
//		System.out.println(JSON.toJSON(page.getList()));
		return (PageInfo<Menu>) list;
	}





}
