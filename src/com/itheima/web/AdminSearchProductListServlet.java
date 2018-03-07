package com.itheima.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.AdminProductService;
import com.itheima.vo.Condition;

public class AdminSearchProductListServlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		//1手机表单数据
		Map<String, String[]> parameterMap = req.getParameterMap();
		//2将散装的查询数据封装到一个vo实体中
		Condition condition=new Condition();
		try {
			BeanUtils.populate(condition, parameterMap);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3将实体传递给service层
		AdminProductService service=new AdminProductService();
		List<Product> productList=null;
		try {
			productList=service.findProductListByCondition(condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	List<Category> categoryList=null;
		try {
			categoryList=service.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		req.setAttribute("condition", condition);
		req.setAttribute("productList", productList);
		/*req.setAttribute("categoryList", categoryList);*/
		req.getRequestDispatcher("/admin/product/list.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}