package com.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.shang.dal.model.Menu;
import com.shang.dto.Node;
import com.shang.menu.Attributes;
import com.shang.menu.TreeNode;

public class TreeUtils {

	/**
	 * 获取指定ID 的子菜单
	 * 
	 * @param id
	 * @param rootMenu
	 * @return
	 */
	public static List<Node<Menu>> getChild(String id, List<Menu> menusList) {
		// 子菜单
		List<Node<Menu>> childList = new ArrayList<Node<Menu>>();
		for (Menu mu : menusList) {
			// id == null md.getMenuPid() ==null Root
			Node<Menu> node = new Node<>();
			if (StringUtils.isBlank(mu.getMenuPid()) && StringUtils.isBlank(id)) {
				node.setT(mu);
				childList.add(node);
			}
			if (StringUtils.isNotBlank(id) && id.equals(mu.getMenuPid())) {
				node.setT(mu);
				childList.add(node);
			}
		}
		if (childList.size() > 0) {
			// 把子菜单的子菜单再循环一遍
			for (Node<Menu> node : childList) {
				// 递归
				node.setChildren(getChild(node.getT().getId(), menusList));
			}
		}
		return childList;
	}
	
	
	public static List<TreeNode> buildTreeNode(String id,List<Menu> menusList) {
		// 子菜单
		List<TreeNode> childList = new ArrayList<TreeNode>();
		for (Menu mu : menusList) {
			// id == null md.getMenuPid() ==null Root
			TreeNode node = new TreeNode();
			if (StringUtils.isBlank(mu.getMenuPid()) && StringUtils.isBlank(id)) {
				node.setId(mu.getId());
				node.setText(mu.getMenuName());
				node.setParentid(mu.getMenuPid());
//				node.setAttributes(new Attributes(mu.getMenuUrl()));   //菜单项目
				childList.add(node);
			}
			if (StringUtils.isNotBlank(id) && id.equals(mu.getMenuPid())) {
				node.setId(mu.getId());
				node.setText(mu.getMenuName());
				node.setParentid(mu.getMenuPid());
				node.setAttributes(new Attributes(mu.getMenuUrl()));   //菜单项目
				childList.add(node);
			}
		}
		if (childList.size() > 0) {
			// 把子菜单的子菜单再循环一遍
			for (TreeNode tn : childList) {
				// 递归
				tn.setChildren(buildTreeNode(tn.getId(), menusList));
				//buildTreeNode(tn.getId(), menusList)
			}
		}
		return childList;
	}
	
	
	public static String createListHtml(List<TreeNode> menuList) {
		String res = "";//data-options=\"iconCls:'icon-ok'\"
	    String menu = "<div title=\"%s\" iconCls=\"icon-ok\"  style=\"padding:10px;\"><ul class=\"easyui-tree\" data-options='data:%s'></ul></div>";
	    for (TreeNode tn : menuList){
	    	res = res + String.format(
	    			menu, 
	    			new Object[] { 
	    					tn.getText(), 
	    					JSON.toJSONString(tn.getChildren()) });
	    }
		return res;
	}

	
	
}
