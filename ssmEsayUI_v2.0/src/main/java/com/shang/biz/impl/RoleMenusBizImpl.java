package com.shang.biz.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shang.base.BaseBiz;
import com.shang.base.common.DgridPage;
import com.shang.biz.IRoleMenusBiz;
import com.shang.dal.dao.RoleMenusMapper;
import com.shang.dal.model.Role;
import com.shang.dal.model.RoleExample;
import com.shang.dal.model.RoleMenus;
import com.shang.dal.model.RoleMenusExample;
import com.shang.dal.model.RoleMenusExample.Criteria;

@Service("roleMenusBiz")  
public class RoleMenusBizImpl extends BaseBiz<RoleMenus> implements IRoleMenusBiz{
	
	@Autowired
	private RoleMenusMapper roleMenusMapper;
	
	@Override
	public void addEntity(RoleMenus obj) {
		roleMenusMapper.insertSelective(obj);
	}
	@Override
	public void delEntity(String id) {
		roleMenusMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void updEntity(RoleMenus obj) {
		roleMenusMapper.updateByPrimaryKeySelective(obj);
	}
	@Override
	public List<RoleMenus> listEntities(RoleMenus obj) {
		RoleMenusExample rme = new RoleMenusExample();
		Criteria criteria = rme.createCriteria();
		String roleId = obj.getRoleId();
		String menuId = obj.getMenuId();
		if(StringUtils.isNotEmpty(roleId)){
			criteria.andRoleIdEqualTo(roleId);
		}
		if(StringUtils.isNotEmpty(menuId)){
			criteria.andMenuIdEqualTo(menuId);
		}
		return roleMenusMapper.selectByExample(rme);
	}
	@Override
	public RoleMenus getEntity(String id) {
		return roleMenusMapper.selectByPrimaryKey(id);
	}
	@Override
	public DgridPage<RoleMenus> queryPageEntities(DgridPage<RoleMenus> dp,
			RoleMenus record) {
		DgridPage<RoleMenus> resDp = new DgridPage<>();
		//设置查询条件
		RoleMenusExample rme = new RoleMenusExample();
		Criteria criteria = rme.createCriteria();
		String roleId = record.getRoleId();
		String menuId = record.getMenuId();
		if(null!=dp){
			BeanUtils.copyProperties(dp, resDp);
			BeanUtils.copyProperties(dp, rme);
		}
		if(StringUtils.isNotEmpty(roleId)){
			criteria.andRoleIdEqualTo(roleId);
		}
		if(StringUtils.isNotEmpty(menuId)){
			criteria.andMenuIdEqualTo(menuId);
		}
		List<RoleMenus> rows = roleMenusMapper.selectByExample(rme);
		long total = roleMenusMapper.countByExample(rme);
		resDp.setRows(rows);
		resDp.setTotal(total);
		return resDp;
	}
	
	@Override
	public void delRmsByRid(String rid) {
		RoleMenusExample rme = new RoleMenusExample();
		rme.createCriteria()
		.andRoleIdEqualTo(rid);
		roleMenusMapper.deleteByExample(rme);
		
	}
	@Override
	public void delRmsByMid(String mid) {
		RoleMenusExample rme = new RoleMenusExample();
		rme.createCriteria()
		.andMenuIdEqualTo(mid);
		roleMenusMapper.deleteByExample(rme);
	}
	@Override
	public XSSFWorkbook export(RoleMenus record, String[] titles) {
		// TODO Auto-generated method stub
		return null;
	}
	



	

}
