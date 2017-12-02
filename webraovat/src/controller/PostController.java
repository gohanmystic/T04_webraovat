package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import BO.PostBO;
import Bean.Post;
import Bean.User;
import Utils.CommonUtils;;

/**
 * Servlet implementation class PostController
 */
@WebServlet("/PostController")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostController() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	PostBO postBO = new PostBO();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(request.getParameter("action").equals("create")) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		    Post post = gson.fromJson(request.getReader().readLine(), Post.class);
			int result = postBO.insertPost(post);
			out.print((result == 1) ? true : false); 
		}
		if(request.getParameter("action").equals("update")) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			Post post = gson.fromJson(request.getReader().readLine(), Post.class);
			int result = postBO.updatePost(post);
			out.print((result == 1) ? true : false); 
		}
		if(request.getParameter("action").equals("delete")) {
			if(request.getParameter("postID") != null) {
				long postID = Long.parseLong(request.getParameter("postID"));
				int result = postBO.deletePost(postID);
				out.print((result == 1) ? true : false); 
			}
		}
		if(request.getParameter("action").equals("detail")) {
					
				try {
					if(request.getParameter("postID") != null) {	
						long postID = Long.parseLong(request.getParameter("postID"));
						Post result;
						result = postBO.getPostById(postID);
						out.print(new Gson().toJson(result));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
		}
		if(request.getParameter("action").equals("getall")) {
			List<Post> result = new ArrayList<Post>();
			String searchKey = request.getParameter("searchKey");
			String filter = request.getParameter("filter");
			try {
					if(CommonUtils.isNotBlank(searchKey)) {
						result = postBO.searchPostBySearchKey(searchKey, filter);
					} else {
						result = postBO.findAll();
					}
					
					out.print(new Gson().toJson(result));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
		}
		if(request.getParameter("action").equals("updateStatus")) {
			if(request.getParameter("postID") != null) {
				long postID = Long.parseLong(request.getParameter("postID"));
				int status = Integer.parseInt(request.getParameter("status"));
				int result = postBO.updateStatusById(postID, status);
				out.print((result == 1) ? true : false); 
			}
		}
		
	}

}
