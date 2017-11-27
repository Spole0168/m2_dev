package com.shang.base;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.shang.base.common.DgridPage;

//add , upd ,del , list ,get , query Page
public interface IBaseBiz<T> {
	public void addEntity(T record);
	
	public void delEntity(String id);
	
	public void updEntity(T record);
	
	public List<T> listEntities(T record);
	
	public T getEntity(String id);
	
	public DgridPage<T> queryPageEntities(DgridPage<T> dp, T record);
	
	public XSSFWorkbook export(T record,String[] titles);
}
