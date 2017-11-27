package com.shang.base.common;

import java.util.List;

/**
 * 分页显示实体
 * @author Spole
 *
 * @param <T>
 */
public class DgridPage<T>{

	private int currentPage = -1;			//current page number
	private int pageSize = -1;				//current page records count
	private int totalPage ;					//total page number
	private long total;						//the record number
	
	private int startRow;					//查询起始条
	
	private List<T> rows;
	
	private T bean;

	public DgridPage(){}

	
	
	public DgridPage(int currentPage, int pageSize,T t) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.startRow = (currentPage - 1) * pageSize ;
		this.bean = t;
	}
	
	public DgridPage(int currentPage, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.startRow = (currentPage - 1) * pageSize ;
	}



	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if(this.pageSize > 0){
			this.startRow = (currentPage - 1) * pageSize ;
		}
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(this.currentPage  > 0){
			this.startRow = (currentPage - 1) * pageSize ;
		}
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public long getTotal() {
		return total;
	}



	public void setTotal(long total) {
		this.total = total;
	}



	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getStartRow() {
		return startRow;
	}



	public T getBean() {
		return bean;
	}



	public void setBean(T bean) {
		this.bean = bean;
	}
	
	
	
	
}
