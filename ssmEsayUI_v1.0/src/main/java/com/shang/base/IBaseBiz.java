package com.shang.base;

import java.util.List;

import com.github.pagehelper.PageInfo;

//add , upd ,del , list ,get , query Page
public interface IBaseBiz<T> {
	public void addEntity(T record);
	
	public void delEntity(String id);
	
	public void updEntity(T record);
	
	public List<T> listEntities(T record);
	
	public T getEntity(String id);
	
	public PageInfo<T> queryPageEntities(int currentPage, int pageSize, T t);
}
