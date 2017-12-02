package com.shang.dal.dao.ext;

import java.util.List;

import com.shang.dal.model.Menu;
import com.shang.dal.model.User;
import com.shang.dto.MenuDto;

public interface ExtMapper {
	
    List<User> queryUserByOrConditions(User user);
    
    List<MenuDto> queryMenusByConditions(Menu menu);
    
    List<Menu> getMenuListByUser(User user);
    
}