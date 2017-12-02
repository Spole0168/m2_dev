package com.shang.biz;

import com.shang.base.IBaseBiz;
import com.shang.dal.model.UserRoles;
public interface IUserRolesBiz extends IBaseBiz<UserRoles>{
	/**
	 * 根据用户id 删除 UR信息
	 * @param uid
	 */
	public void delUrsByUid(String uid);
	
	/**
	 * 根据角色 id  删除 UR信息
	 * @param uid
	 */
	public void delUrsByRid(String rid);
}
