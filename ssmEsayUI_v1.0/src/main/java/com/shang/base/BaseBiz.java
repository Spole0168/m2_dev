package com.shang.base;

import org.apache.log4j.Logger;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseBiz<T> {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public PageInfo<T> queryPageEntities(int currentPage, int pageSize, T t){
		PageHelper.startPage(currentPage, pageSize);
		//子类方法调用
		PageInfo<T> pageInfo = new PageInfo<T>();
//		pageInfo.setPageNum(currentPage);
//		pageInfo.setPageSize(pageSize);
        return pageInfo;
	}
}
