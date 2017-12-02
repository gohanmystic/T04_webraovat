package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.Category;
import Database.DB;
import Utils.CommonUtils;

public class CategoryDAO extends BaseDAO {
   public int insertCate(Category cate) throws SQLException{
		
		DB.Instance().Connect();
		String sql="insert into [dbo].[CATEGORY] (Name)" +"values (?)";	
		PreparedStatement cmd= DB.Instance().cn.prepareStatement(sql);		
		cmd.setString(1, cate.getName());
		int r= cmd.executeUpdate();		
		
		DB.Instance().cn.close();
		return r;
		}
   
  	public int updateCate(Category cate) throws SQLException{
		
		DB.Instance().Connect();
		String sql="update  [dbo].[CATEGORY] set  Name=? where CategoryID=?";	
		PreparedStatement cmd= DB.Instance().cn.prepareStatement(sql);		
		cmd.setString(1, cate.getName());
		cmd.setString(2, cate.getCategoryID());
		int r= cmd.executeUpdate();				
		DB.Instance().cn.close();
		return r;
		}
  	
  	public int deletePost(long cate) throws SQLException{
		DB.Instance().Connect();
		String sql="delete from [dbo].[CATEGORY] where CategoryID=?";	
		PreparedStatement cmd= DB.Instance().cn.prepareStatement(sql);
		cmd.setLong(1, cate);
		
		int r= cmd.executeUpdate();		
		DB.Instance().cn.close();
		return r;
		}	
  	
  	public Category findCateByResultSet(ResultSet rs) {
		Category cate = new Category();
		try {
			cate.setCategoryID(rs.getString("CategoryID"));
			cate.setName(rs.getString("Name"));				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cate;
	}
  	public List<Category> findAll() throws SQLException{
		List<Category> result = new ArrayList<Category>();
		
			DB.Instance().Connect();
			String query = "SELECT * FROM [dbo].[CATEGORY] ORDER BY CategoryID DESC";
			PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
			ResultSet rs = cmd.executeQuery();
			
			while(rs.next()) {
				Category cate = findCateByResultSet(rs);
				result.add(cate);
			}
			
			rs.close();
			DB.Instance().cn.close();
		return result;
	}
  	
  	public Category getCateById(long CategoryID) throws SQLException {
		Category result = new Category();
		DB.Instance().Connect();
		String query = "SELECT * FROM [dbo].[CATEGORY] WHERE CategoryID = ?";
		PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
		cmd.setLong(1,CategoryID);
		
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next()) {
			result = findCateByResultSet(rs);
		}
		return result;
	}
  	public List<Category> searchCateBySearchKey(String searchKey, String filter) throws SQLException{
		List<Category> result = new ArrayList<Category>();
			
			DB.Instance().Connect();
			String query = "";
			PreparedStatement cmd = null;
			ResultSet rs = null;
			if (CommonUtils.isNotBlank(filter)) {
				if (filter.equals("filterTittle")) {
					System.out.println(filter);
					System.out.println(searchKey);
					query = "SELECT * FROM [dbo].[CATEGORY] u WHERE u.Name LIKE ? ORDER BY CategoryID DESC";
					cmd = DB.Instance().cn.prepareStatement(query);
					cmd.setString(1, "%" + searchKey + "%");
					rs = cmd.executeQuery();
				}			
			} 
			
			while(rs.next()) {
				Category cate = findCateByResultSet(rs);
				result.add(cate);
			}
			
			rs.close();
			DB.Instance().cn.close();
		return result;
	}
}
