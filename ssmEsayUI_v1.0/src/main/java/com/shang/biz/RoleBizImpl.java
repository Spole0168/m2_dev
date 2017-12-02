package com.shang.biz;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.shang.base.BaseBiz;
import com.shang.dal.dao.RoleMapper;
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

	public PageInfo<Role> queryPageEntities(int currentPage, int pageSize,
			Role t) {
		// TODO Auto-generated method stub
		return null;
	}
	





}
