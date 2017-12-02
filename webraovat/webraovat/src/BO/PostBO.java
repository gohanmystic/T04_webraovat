package BO;

import java.sql.SQLException;
import java.util.Date;

import DAO.PostDAO;

public class PostBO {
	PostDAO post = new PostDAO();
	public int insertPost(int postID, String title, Date creationDate, String content, long price, long userID, long postTypeID,
			long categoryID, Boolean status, String image){
		try {
			return post.insertPost(postID, title, creationDate, content, price, userID, postTypeID, categoryID, status, image);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int updatePost(int postID, String title, Date creationDate, String content, long price, long userID, long postTypeID,
			long categoryID, Boolean status, String image){		
		try {
			return post.updatePost(postID, title, creationDate, content, price, userID, postTypeID, categoryID, status, image);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deletePost(int postID){		
		try {
			return post.deletePost(postID);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
