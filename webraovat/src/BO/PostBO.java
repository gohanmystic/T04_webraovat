package BO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import DAO.PostDAO;
import Database.DB;
import Bean.Post;
import Bean.User;

public class PostBO {
	PostDAO postDAO = new PostDAO();
	public int insertPost(Post post){
		try {
			return postDAO.insertPost(post);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int updatePost(Post post){		
		try {
			return postDAO.updatePost(post);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deletePost(long postID){		
		try {
			return postDAO.deletePost(postID);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public Post getPostById(long postID) throws SQLException {	
		return postDAO.getPostById(postID);
	}
	public List<Post> findAll() throws SQLException{
		return postDAO.findAll();
	}
	public List<Post> searchPostBySearchKey(String searchKey, String filter){
		try {
			return postDAO.searchPostBySearchKey(searchKey, filter);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public int updateStatusById(long postID, int status) {
		try {
			return postDAO.updateStatusById(postID, status);
		} catch (SQLException e) {
			return 0;
		}
	}
}
