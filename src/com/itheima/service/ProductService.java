package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.vo.PageBean;

public class ProductService {
    ProductDao dao=new ProductDao();
	public List<Product> findAllProduct() throws SQLException {
		
		return dao.findAllProduct();
	}
	//分页操作
	/**
	 * @param currentPage
	 * @param currentCount
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Product> findPageBean(int currentPage,int currentCount) throws SQLException {
		//目的：就是想办法封装一个PageBean并返回
		PageBean<Product> pageBean=new PageBean<Product>();
		//1 当前页private int currentPage;
		pageBean.setCurrentPage(currentPage);
		// 2当前页显示条数private int currentCount;
		pageBean.setCurrentCount(currentCount);
		//3总条数private int totalCount;
		int totalCount=dao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		//4 总页数private int totalPage;
		/*
		 * 总条数      当前页显示的条数   总条数
		 * 10     4           3
		 * 11     4           3
		 * 12     4           3
		 * 13     4           4
		 * 公式：总页数=Math.ceil(总条数/当前显示的条数)
		 */
		int totalPage=(int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
	    //5 每页显示的数据private List<T> productList=new ArrayList<T>();
		/*
		 * 页数与  limit起始索引的关系
		 * 页数        起始索引       每页显示条数
		 * 1页             0        4
		 * 2页            4         4
		 * 3页           8         4
		 * 索引index=（当前页数-1）*每页显示条数
		 */
		int index=(currentPage-1)*currentCount;
		List<Product> productList=dao.findProductListAsPageBean(index,currentCount);
		pageBean.setProductList(productList);
		return pageBean;
	}

}
