package com.shang.biz.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shang.base.BaseBiz;
import com.shang.base.common.DgridPage;
import com.shang.biz.IUserRolesBiz;
import com.shang.dal.dao.UserRolesMapper;
import com.shang.dal.model.Role;
import com.shang.dal.model.RoleExample;
import com.shang.dal.model.UserRoles;
import com.shang.dal.model.UserRolesExample;
import com.shang.dal.model.UserRolesExample.Criteria;

@Service("userRolesBiz")  
public class UserRolesBizImpl extends BaseBiz<UserRoles> implements IUserRolesBiz{
	
	@Autowired
	private UserRolesMapper userRolesMapper;
	
	@Override
	public void addEntity(UserRoles record) {
		userRolesMapper.insertSelective(record);
		
	}
	@Override
	public void delEntity(String id) {
		userRolesMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void updEntity(UserRoles record) {
		userRolesMapper.updateByPrimaryKeySelective(record);
		
	}
	@Override
	public List<UserRoles> listEntities(UserRoles record) {
		UserRolesExample ure = new UserRolesExample();
		Criteria criteria = ure.createCriteria();
		String userId = record.getUserId();
		String roleId = record.getRoleId();
		if(StringUtils.isNotEmpty(userId)){
			criteria.andUserIdEqualTo(userId);
		}
		if(StringUtils.isNotEmpty(roleId)){
			criteria.andRoleIdEqualTo(roleId);
		}
		return userRolesMapper.selectByExample(ure);
	}
	@Override
	public UserRoles getEntity(String id) {
		return userRolesMapper.selectByPrimaryKey(id);
	}
	@Override
	public DgridPage<UserRoles> queryPageEntities(DgridPage<UserRoles> dp,
			UserRoles record) {
		DgridPage<UserRoles> resDp = new DgridPage<UserRoles>();
		//设置查询条件
		UserRolesExample ure = new UserRolesExample();
		if(null!=dp){
			BeanUtils.copyProperties(dp, resDp);
			BeanUtils.copyProperties(dp, ure);
		}
		Criteria criteria = ure.createCriteria();
		String userId = record.getUserId();
		String roleId = record.getRoleId();
		if(StringUtils.isNotEmpty(userId)){
			criteria.andUserIdEqualTo(userId);
		}
		if(StringUtils.isNotEmpty(roleId)){
			criteria.andRoleIdEqualTo(roleId);
		}
		List<UserRoles> rows = userRolesMapper.selectByExample(ure);
		long total = userRolesMapper.countByExample(ure);
		resDp.setRows(rows);
		resDp.setTotal(total);
		return resDp;
	}
	
	@Override
	public void delUrsByUid(String uid) {
		UserRolesExample ure = new UserRolesExample();
		ure.createCriteria()
		.andUserIdEqualTo(uid);
		userRolesMapper.deleteByExample(ure);
		
	}
	@Override
	public void delUrsByRid(String rid) {
		UserRolesExample ure = new UserRolesExample();
		ure.createCriteria()
		.andRoleIdEqualTo(rid);
		userRolesMapper.deleteByExample(ure);
	}
	@Override
	public XSSFWorkbook export(UserRoles record, String[] titles) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
