package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Database.DB;
import Utils.CommonUtils;
import Bean.Post;

public class PostDAO {
	public int insertPost(Post post) throws SQLException{
		
		DB.Instance().Connect();
		String sql="insert into POST(Title,CreationDate,[Content],Price,UserID,PostTypeID,CategoryID,Status,Image) values(?,?,?,?,?,?,?,?,?)";	
		PreparedStatement cmd= DB.Instance().cn.prepareStatement(sql);
		
		cmd.setString(1, post.getTitle());
		cmd.setDate(2, new java.sql.Date(post.getCreationDate().getTime()));
		cmd.setString(3, post.getContent());
		cmd.setLong(4, post.getPrice());
		cmd.setLong(5, post.getUserID());
		cmd.setLong(6, post.getPostTypeID());
		cmd.setLong(7, post.getCategoryID());
		cmd.setBoolean(8, false);
		cmd.setBinaryStream(9, null);

		int r= cmd.executeUpdate();		
		
		DB.Instance().cn.close();
		return r;
		}
	public int updatePost(Post post) throws SQLException{
		DB.Instance().Connect();
		String sql="update POST set Title=?, CreationDate=?,[Content]=?,Price=?,UserID=?,PostTypeID=?,CategoryID=?,Status=?,Image=? where PostID=?";	
		PreparedStatement cmd= DB.Instance().cn.prepareStatement(sql);
		
		cmd.setString(1, post.getTitle());
		cmd.setDate(2, new java.sql.Date(post.getCreationDate().getTime()));
		cmd.setString(3, post.getContent());
		cmd.setLong(4, post.getPrice());
		cmd.setLong(5, post.getUserID());
		cmd.setLong(6, post.getPostTypeID());
		cmd.setLong(7, post.getCategoryID());
		cmd.setBoolean(8, post.getStatus());
		cmd.setBinaryStream(9, null);
		cmd.setLong(10, post.getPostID());
		
		int r= cmd.executeUpdate();	
		DB.Instance().cn.close();
		return r;
		}
	public int deletePost(long postID) throws SQLException{
		DB.Instance().Connect();
		String sql="delete from POST where PostID = ?";	
		PreparedStatement cmd= DB.Instance().cn.prepareStatement(sql);
		cmd.setLong(1, postID);
		
		int r= cmd.executeUpdate();		
		DB.Instance().cn.close();
		return r;
		}
	public Post findPostByResultSet(ResultSet rs) {
		Post post = new Post();
		try {		
			post.setPostID(rs.getLong("PostID"));
			post.setTitle(rs.getString("Title"));
			post.setCreationDate(rs.getDate("CreationDate"));
			post.setContent(rs.getString("Content"));
			post.setPrice(rs.getLong("Price"));
			post.setUserID(rs.getLong("UserID"));
			post.setPostTypeID(rs.getLong("PostTypeID"));
			post.setCategoryID(rs.getLong("CategoryID"));
			post.setStatus(rs.getBoolean("Status"));
			post.setImage(null);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return post;
	}
	public Post getPostById(long postID) throws SQLException {
		Post result = new Post();
		DB.Instance().Connect();
		String query = "SELECT * FROM dbo.[POST] WHERE PostID = ?";
		PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
		cmd.setLong(1, postID);
		
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next()) {
			result = findPostByResultSet(rs);
		}
		return result;
	}
	public List<Post> findAll() throws SQLException{
		List<Post> result = new ArrayList<Post>();
		
			DB.Instance().Connect();
			String query = "SELECT * FROM dbo.[POST] ORDER BY PostID DESC";
			PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
			ResultSet rs = cmd.executeQuery();
			
			while(rs.next()) {
				Post post = findPostByResultSet(rs);
				result.add(post);
			}
			
			rs.close();
			DB.Instance().cn.close();
		return result;
	}
	public List<Post> searchPostBySearchKey(String searchKey, String filter) throws SQLException{
		List<Post> result = new ArrayList<Post>();
			
			DB.Instance().Connect();
			String query = "";
			PreparedStatement cmd = null;
			ResultSet rs = null;
			if (CommonUtils.isNotBlank(searchKey)) {
				query = "SELECT * FROM dbo.[POST] u WHERE u.Title LIKE ? ORDER BY PostID DESC";
				cmd = DB.Instance().cn.prepareStatement(query);
				cmd.setString(1, "%" + searchKey + "%");
				rs = cmd.executeQuery();
			} 
			
			while(rs.next()) {
				Post post = findPostByResultSet(rs);
				result.add(post);
			}
			
			rs.close();
			DB.Instance().cn.close();
		return result;
	}
	public int updateStatusById(long postID, int status) throws SQLException {
		int result = 0;

		DB.Instance().Connect();
		String query = "UPDATE dbo.[POST]\r\n" + 
				"SET Status=?\r\n" + 
				"WHERE PostID = ?";
		PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
		cmd.setInt(1, status);
		cmd.setLong(2, postID);
		result = cmd.executeUpdate();
		
		return result;
	}
}
