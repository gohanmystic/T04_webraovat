package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.Category;
import Database.DB;

public class CategoryDAO {
	public int Add(String categoryID, String name){
		try{
			DB.Instance().Connect();
			String sql="insert into dbo.CATEGORY (CategoryID,Name) values(?,?)";
			PreparedStatement cmd = DB.Instance().cn.prepareStatement(sql);
			cmd.setString(1, categoryID);	
			cmd.setString(2, name);
			
			int kq = cmd.executeUpdate();
			DB.Instance().cn.close();
			return kq;
		}catch(Exception tt){
			tt.printStackTrace();
		}
		return 0;
	}
	
	public int Update(String categoryID, String name){
		try{
			DB.Instance().Connect();
			String sql="update dbo.CATEGORY set Name=? where CategoryID=? ";
			PreparedStatement cmd = DB.Instance().cn.prepareStatement(sql);
			cmd.setString(2, categoryID);	
			cmd.setString(1, name);
			
			int kq = cmd.executeUpdate();
			DB.Instance().cn.close();
			return kq;
		}catch(Exception tt){
			tt.printStackTrace();
		}
		return 0;
	}
	public int delete(String categoryID ) {
		try{
			DB.Instance().Connect();
			String sql="delete from dbo.CATEGORY where CategoryID=?";
			PreparedStatement cmd = DB.Instance().cn.prepareStatement(sql);
			cmd.setString(1, categoryID);			
			int kq = cmd.executeUpdate();
			DB.Instance().cn.close();
			return kq;
		}catch(Exception tt){
			tt.printStackTrace();
		}
		return 0;
	}
	public Category selecttheoma (String categoryID){
		try {
			DB.Instance().Connect();
			String sql="select * from dbo.CATEGORY where CategoryID=?";
			PreparedStatement cmd = DB.Instance().cn.prepareStatement(sql);
			cmd.setString(1, categoryID);
			ResultSet rs= cmd.executeQuery();
			ArrayList<Category> ds=new ArrayList<Category>();
			while (rs.next()) {
				Category s = new Category(rs.getString("categoryID"), rs.getString("Name"));
				return s;
			}
			DB.Instance().cn.close();
			rs.close();
			return null;
		}catch (Exception tt) {
			tt.printStackTrace();
		}
			return null;
	}	
	public ArrayList<Category> getDanhMuc(){
		try {
			DB.Instance().Connect();
			String sql="select * from dbo.CATEGORY" ;
			PreparedStatement cmd = DB.Instance().cn.prepareStatement(sql);
			ResultSet rs= cmd.executeQuery();
			ArrayList<Category> ds=new ArrayList<Category>();
			while (rs.next()) {
	
				Category sach = new  Category(rs.getString("categoryID"),rs.getString("Name"));
					ds.add(sach);
			}
			DB.Instance().cn.close();
			rs.close();
			return ds;
		}catch (Exception e) {
			e.printStackTrace();}
			return null;
		}	
}
