package com.shang.biz;

import java.util.List;

import com.shang.base.BaseException;
import com.shang.base.IBaseBiz;
import com.shang.dal.model.Menu;
import com.shang.dal.model.User;
import com.shang.dto.MenuDto;

public interface IUserBiz extends IBaseBiz<User>{
	
	public User login(User user) throws BaseException;
	
	public List<Menu> getMenuListByUser(User user);
	
	public List<MenuDto> queryMenusByConditions(Menu menu);
	
}
