package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import BO.CategoryBO;
import Bean.Category;
import Utils.CommonUtils;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	CategoryBO cateBO = new CategoryBO();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(request.getParameter("action").equals("create")) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		    Category cate = gson.fromJson(request.getReader().readLine(), Category.class);
			int result = cateBO.insertCate(cate);
			out.print((result == 1) ? true : false); 
		}
		if(request.getParameter("action").equals("getall")) {
			List<Category> result = new ArrayList<Category>();
			String searchKey = request.getParameter("searchKey");
			String filter = request.getParameter("filter");

			if(CommonUtils.isNotBlank(searchKey)) {
			result = cateBO.searchCateBySearchKey(searchKey, filter);
			} else {
				result = cateBO.findAll();
			}			
			out.print(new Gson().toJson(result));
		}
		
		if(request.getParameter("action").equals("update")) {
			 Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			 Category cate = gson.fromJson(request.getReader().readLine(), Category.class);
			 int result = cateBO.updateCate(cate);
			 out.print((result == 1) ? true : false); 
		}
		if(request.getParameter("action").equals("delete")) {
			if(request.getParameter("CategoryID") != null) {
				long CategoryID = Long.parseLong(request.getParameter("CategoryID"));
				int result = cateBO.deleteCateById(CategoryID);
				out.print((result == 1) ? true : false); 
			}
		}
		if(request.getParameter("action").equals("detail")) {
			if(request.getParameter("CategoryID") != null) {
				long CategoryID = Long.parseLong(request.getParameter("CategoryID"));
				Category result = cateBO.getCateById(CategoryID);
				out.print(new Gson().toJson(result));
			}
		}

}
	}
