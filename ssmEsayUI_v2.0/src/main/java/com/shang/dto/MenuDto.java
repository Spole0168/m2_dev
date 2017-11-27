package com.shang.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.shang.dal.model.Menu;

public class MenuDto {

    /**
     * VARCHAR(40) 必填<br>
     * 主键
     */
    private String id;

    /**
     * VARCHAR(40)<br>
     * 父级菜单id
     */
    private String menuPid;

    /**
     * VARCHAR(60)<br>
     * 菜单名称
     */
    @JSONField(name="text")
    private String menuName;

    /**
     * VARCHAR(20)<br>
     * 菜单编码   唯一性
     */
    private String menuCode;

    /**
     * VARCHAR(50)<br>
     * 菜单类型
     */
    private String menuType;

    /**
     * INTEGER(10)<br>
     * 菜单级别 1-一级菜单 2-二级菜单 3-三级菜单
     */
    private Integer menuLevel;

    /**
     * VARCHAR(50)<br>
     * 菜单连接地址 url
     */
    private String menuUrl;

    /**
     * VARCHAR(2)<br>
     * 菜单排序
     */
    private String menuOrder;

    /**
     * CHAR(1) 默认值[0]<br>
     * 是否有效 0-有效，1-删除 
     */
    private String isValid;

    /**
     * VARCHAR(20)<br>
     * 创建者
     */
    private String creator;

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 创建时间
     */
    private Date createTime;

    /**
     * VARCHAR(20)<br>
     * 最后修改者
     */
    private String updator;

    /**
     * TIMESTAMP(19)<br>
     * 最后修改时间
     */
    private Date updateTime;
    
    private List<MenuDto> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuPid() {
		return menuPid;
	}

	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}

	public static void main(String[] args) {
		MenuDto root = new MenuDto();
		root.setMenuName("root");
		
		
	}
	
    /**
     * 获取指定ID 的子菜单
     * @param id
     * @param rootMenu
     * @return
     */
	public static  List<MenuDto> getChild(String id, List<MenuDto> rootMenu) {
	    // 子菜单
	    List<MenuDto> childList = new ArrayList<MenuDto>();
	    for (MenuDto md : rootMenu) {
	    	//id == null md.getMenuPid() ==null  Root
	    	if(StringUtils.isBlank(md.getMenuPid()) && StringUtils.isBlank(id)){
	    		childList.add(md);
	    	}
	    	if(StringUtils.isNotBlank(id) && id.equals(md.getMenuPid())) {
	    		childList.add(md);
	    	}
	    }
	    if(childList.size() > 0){
	    	// 把子菜单的子菜单再循环一遍
	    	for (MenuDto cmd : childList) {
	    		// 递归
	    		cmd.setChildren(getChild(cmd.getId(), rootMenu));
	    	}
	    }
	    return childList;
	}
}
