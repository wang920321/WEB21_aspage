package com.itheima.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;
import com.itheima.vo.Condition;

public class AdminProductDao {

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product";
		List<Product> products = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return products;
	}

	public int addProduct(Product product) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		int row = runner.update(sql, product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),
				product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid());
		return row;
	}

	public List<Category> findAllCategory() throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		List<Category> categoryList = runner.query(sql, new BeanListHandler<Category>(Category.class));
		
		return categoryList;
	}

	public void deleteProductById(String pid) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from product where pid=?";
		 runner.update(sql, pid);
		
		
	}

	public Product findProductById(String pid) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where pid=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		
		return product;
	}

	public int updateProduct(Product product) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
		int row = runner.update(sql, product.getPname(),product.getMarket_price(),product.getShop_price(),
				product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid(),
				product.getPid());
		return row;
	}

	public List<Product> findProductListByCondition(Condition condition) throws SQLException {
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		StringBuffer sql=new StringBuffer("select * from product where 1=1 ");
		List<String> list=new ArrayList<String>();  
		if(condition.getPname()!=null&&!condition.getPname().trim().equals("")){
			sql.append(" and pname like ? ");
			list.add("%"+condition.getPname().trim()+"%");
		}
		if(condition.getIs_hot()!=null&&!condition.getIs_hot().trim().equals("")){
			sql.append(" and is_hot=? ");
			list.add(condition.getIs_hot());
		}
		if(condition.getCid()!=null&&!condition.getCid().trim().equals("")){
			sql.append(" and cid=? ");
			list.add(condition.getCid());
		}
		List<Product> productList=runner.query(sql.toString(), new BeanListHandler<Product>(Product.class),list.toArray() );
		return productList;
	}

}
