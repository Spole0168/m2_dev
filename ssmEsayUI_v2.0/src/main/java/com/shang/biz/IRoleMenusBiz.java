package com.shang.biz;

import com.shang.base.IBaseBiz;
import com.shang.dal.model.RoleMenus;
public interface IRoleMenusBiz extends IBaseBiz<RoleMenus>{
	/**
	 * 根据角色id删除rm 的信息
	 * @param rid
	 */
	public void delRmsByRid(String rid);
	/**
	 * 根据菜单id删除rm 的信息
	 * @param rid
	 */
	public void delRmsByMid(String mid);
}
