package com.shang.dal.dao;

import com.shang.dal.model.RoleMenus;
import com.shang.dal.model.RoleMenusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenusMapper {
    long countByExample(RoleMenusExample example);

    int deleteByExample(RoleMenusExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoleMenus record);

    int insertSelective(RoleMenus record);

    List<RoleMenus> selectByExample(RoleMenusExample example);

    RoleMenus selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoleMenus record, @Param("example") RoleMenusExample example);

    int updateByExample(@Param("record") RoleMenus record, @Param("example") RoleMenusExample example);

    int updateByPrimaryKeySelective(RoleMenus record);

    int updateByPrimaryKey(RoleMenus record);
}