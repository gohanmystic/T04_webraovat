package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import Database.DB;

public class PostDAO {
	public int insertPost(int postID, String title, Date creationDate, String content, long price, long userID, long postTypeID,
			long categoryID, Boolean status, String image) throws SQLException{
		
		DB.Instance().Connect();
		String sql="insert into POST values(?,?,?,?,?,?,?,?,?,?)";	
		PreparedStatement cmd= DB.Instance().cn.prepareStatement(sql);
		
		cmd.setInt(1, postID);
		cmd.setString(2, title);
		cmd.setDate(3, new java.sql.Date(creationDate.getTime()));
		cmd.setString(4, content);
		cmd.setLong(5, price);
		cmd.setLong(6, userID);
		cmd.setLong(7, postTypeID);
		cmd.setLong(8, categoryID);
		cmd.setBoolean(9, status);
		cmd.setString(10, image);

		int r= cmd.executeUpdate();		
		
		DB.Instance().cn.close();
		return r;
		}
	public int updatePost(int postID, String title, Date creationDate, String content, long price, long userID, long postTypeID,
			long categoryID, Boolean status, String image) throws SQLException{
		DB.Instance().Connect();
		String sql="update POST set Title=?, CreationDate=?,[Content]=?,Price=?,UserID=?,PostTypeID=?,CategoryID=?,Status=?,Image=? where PostID=?";	
		PreparedStatement cmd= DB.Instance().cn.prepareStatement(sql);
		
		cmd.setString(1, title);
		cmd.setDate(2, new java.sql.Date(creationDate.getTime()));
		cmd.setString(3, content);
		cmd.setLong(4, price);
		cmd.setLong(5, userID);
		cmd.setLong(6, postTypeID);
		cmd.setLong(7, categoryID);
		cmd.setBoolean(8, status);
		cmd.setString(9, image);
		cmd.setInt(10, postID);
		
		int r= cmd.executeUpdate();	
		DB.Instance().cn.close();
		return r;
		}
	public int deletePost(int postID) throws SQLException{
		DB.Instance().Connect();
		String sql="delete from POST where PostID = ?";	
		PreparedStatement cmd= DB.Instance().cn.prepareStatement(sql);
		cmd.setInt(1, postID);
		
		int r= cmd.executeUpdate();		
		DB.Instance().cn.close();
		return r;
		}
}
