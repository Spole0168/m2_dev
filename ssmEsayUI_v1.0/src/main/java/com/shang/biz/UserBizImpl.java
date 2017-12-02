package com.shang.biz;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shang.base.BaseBiz;
import com.shang.base.BaseException;
import com.shang.dal.dao.UserMapper;
import com.shang.dal.dao.ext.ExtMapper;
import com.shang.dal.model.Menu;
import com.shang.dal.model.User;
import com.shang.dal.model.UserExample;
import com.shang.dto.MenuDto;
import com.utils.MD5Util;

@Service("userBiz")  
public class UserBizImpl extends BaseBiz<User> implements IUserBiz{
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ExtMapper extMapper;


	@Override
	public void addEntity(User user) {
		String pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
		user.setPassword(pwd);
		userMapper.insertSelective(user);
	}

	@Override
	public void delEntity(String id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updEntity(User user) {
		String npwd = user.getPassword();
		if(StringUtils.isNotEmpty(npwd)){
			String pwd = MD5Util.MD5Encode(npwd, "UTF-8");
			user.setPassword(pwd);
		}
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<User> listEntities(User user) {
		String key = user.getUsername();
		user.setEmail(key);
		user.setTel(key);
		List<User> list = extMapper.queryUserByOrConditions(user);
		return list;
	}

	
	
	@Override
	public User getEntity(String id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public User login(User user) throws BaseException {
		List<User> listUsers = this.listEntities(user);
		//根据用户名查找用户
		if(null==listUsers || listUsers.size()!=1){
			throw new BaseException("9999", "用户名输入有误");
		}
		User currentUser = listUsers.get(0);
		//验证密码
		String md5Encode = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
		if(!md5Encode.equals(currentUser.getPassword())){
			throw new BaseException("9999", "用户密码输入有误");
		}
		return currentUser;
	}

	@Override
	public List<Menu> getMenuListByUser(User user) {
		if(StringUtils.isEmpty(user.getId())){
			return null;
		}
		return extMapper.getMenuListByUser(user);
	}

	@Override
	public List<MenuDto> queryMenusByConditions(Menu menu) {
//		if(StringUtils.isEmpty(user.getId())){
//			return null;
//		}
		return extMapper.queryMenusByConditions(menu);
	}

	

}
