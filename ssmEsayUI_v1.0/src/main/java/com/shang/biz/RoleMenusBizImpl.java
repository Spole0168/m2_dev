package com.shang.biz;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shang.base.BaseBiz;
import com.shang.dal.dao.RoleMenusMapper;
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



	

}
