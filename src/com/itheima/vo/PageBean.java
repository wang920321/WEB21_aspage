package com.itheima.vo;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
	// 当前页
	private int currentPage;
	// 当前页显示条数
	private int currentCount;
	//总条数
	private int totalCount;
	// 总页数
	private int totalPage;
   
	// 每页显示的数据
	private List<T> productList=new ArrayList<T>();

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the currentCount
	 */
	public int getCurrentCount() {
		return currentCount;
	}

	/**
	 * @param currentCount the currentCount to set
	 */
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the productList
	 */
	public List<T> getProductList() {
		return productList;
	}

	/**
	 * @param productList the productList to set
	 */
	public void setProductList(List<T> productList) {
		this.productList = productList;
	}
	
	
	
	
	
}
