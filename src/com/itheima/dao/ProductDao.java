package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;

public class ProductDao {

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product";
		List<Product> products = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return products;
	}
    //获得全部的商品条数
	public int getTotalCount() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from product";
		Long query=(Long)runner.query(sql, new ScalarHandler());
		return query.intValue();
	}
	public List<Product> findProductListAsPageBean(int i, int currentCount) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product limit ? , ? ";
		List<Product> productList=runner.query(sql, new BeanListHandler<Product>(Product.class), i,currentCount);
		return productList;
	}
	public List<Object> findProductByWord(String word) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where pname like ? limit 0,8 ";
		List<Object> productList = runner.query(sql,new ColumnListHandler("pname"),"%"+word+"%");
		return productList;
	}

}
