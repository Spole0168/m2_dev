package com.shang.biz.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.shang.base.BaseBiz;
import com.shang.base.common.DgridPage;
import com.shang.biz.IRoleBiz;
import com.shang.dal.dao.RoleMapper;
import com.shang.dal.model.Menu;
import com.shang.dal.model.MenuExample;
import com.shang.dal.model.Role;
import com.shang.dal.model.RoleExample;
import com.shang.dal.model.RoleExample.Criteria;

@Service("roleBiz")  
public class RoleBizImpl extends BaseBiz<Role> implements IRoleBiz{
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public void addEntity(Role obj) {
		roleMapper.insertSelective(obj);
		
	}

	@Override
	public void delEntity(String id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updEntity(Role obj) {
		roleMapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public List<Role> listEntities(Role obj) {
		RoleExample re = new RoleExample();
		Criteria criteria = re.createCriteria();
		
		String roleCode = obj.getRoleCode();
		if(StringUtils.isNotEmpty(roleCode)){
			criteria.andRoleCodeEqualTo(roleCode);
		}
		return roleMapper.selectByExample(re);
	}

	@Override
	public Role getEntity(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	@Override
	public DgridPage<Role> queryPageEntities(DgridPage<Role> dp, Role record) {
		DgridPage<Role> resDp = new DgridPage<>();
		//设置查询条件
		RoleExample re = new RoleExample();
		if(null!=dp){
			BeanUtils.copyProperties(dp, resDp);
			BeanUtils.copyProperties(dp, re);
		}
		Criteria criteria = re.createCriteria();
		if(null != record){
			String menuName = record.getRoleName();
			if(StringUtils.isNotEmpty(menuName)){
				criteria.andRoleNameLike(menuName);
			}
		}
		List<Role> rows = roleMapper.selectByExample(re);
		long total = roleMapper.countByExample(re);
		resDp.setRows(rows);
		resDp.setTotal(total);
		return resDp;
	}

	@Override
	public XSSFWorkbook export(Role record, String[] titles) {
		// TODO Auto-generated method stub
		return null;
	}
	





}
