package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import BO.UserBO;
import Bean.User;
import Utils.CommonUtils;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
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

	UserBO userBO = new UserBO();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(request.getParameter("action").equals("create")) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		    User user = gson.fromJson(request.getReader().readLine(), User.class);
			int result = userBO.createUser(user);
			out.print((result == 1) ? true : false); 
		}
		if(request.getParameter("action").equals("getall")) {
			List<User> result = new ArrayList<User>();
			String searchKey = request.getParameter("searchKey");
			String filter = request.getParameter("filter");

			if(CommonUtils.isNotBlank(searchKey)) {
				result = userBO.searchUserBySearchKey(searchKey, filter);
			} else {
				result = userBO.findAll();
			}
			
			out.print(new Gson().toJson(result));
		}
		if(request.getParameter("action").equals("detail")) {
			if(request.getParameter("userID") != null) {
				long userID = Long.parseLong(request.getParameter("userID"));
				User result = userBO.getUserById(userID);
				out.print(new Gson().toJson(result));
			}
		}
		if(request.getParameter("action").equals("update")) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		    User user = gson.fromJson(request.getReader().readLine(), User.class);
			int result = userBO.updateUser(user);
			out.print((result == 1) ? true : false); 
		}
		if(request.getParameter("action").equals("delete")) {
			if(request.getParameter("userID") != null) {
				long userID = Long.parseLong(request.getParameter("userID"));
				int result = userBO.deleteUserById(userID);
				out.print((result == 1) ? true : false); 
			}
		}
		if(request.getParameter("action").equals("updateStatus")) {
			if(request.getParameter("userID") != null) {
				long userID = Long.parseLong(request.getParameter("userID"));
				int status = Integer.parseInt(request.getParameter("status"));
				int result = userBO.updateStatusById(userID, status);
				out.print((result == 1) ? true : false); 
			}
		}
	}
	
}
