package com.itheima.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.vo.PageBean;

public class ProductListServlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ProductService service=new ProductService();
		/*//模拟当前是第一页
		int currentPage=1;
		//认为每页显示12条
		int currentCount=12;*/
		String currentPageStr = req.getParameter("currentPage");
		
		int currentCount=12;
		if(currentPageStr==null){
			currentPageStr="1";
		}
		int currentPage=Integer.parseInt(currentPageStr);
		PageBean<Product> pageBean=null;
		try {
			pageBean=service.findPageBean(currentPage,currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("pageBean", pageBean);
		req.getRequestDispatcher("/product_list.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}