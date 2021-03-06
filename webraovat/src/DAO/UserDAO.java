package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.User;
import Database.DB;
import Utils.CommonUtils;

public class UserDAO extends BaseDAO{
	
	public int createUser(User user) throws SQLException {
		int result = 0;
		
		DB.Instance().Connect();
		String query = "INSERT INTO dbo.[USER](Phone,Address,Gender,BirthDate,FullName,Email,UserName,PassWord,Level,Status,ReportsNumber) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
		cmd.setString(1, user.getPhone());
		cmd.setString(2, user.getAddress());
		cmd.setInt(3, user.getGender());
		if(user.getBirthday() != null)
			cmd.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
		else
			cmd.setDate(4, null);
		cmd.setString(5, user.getFullName());
		cmd.setString(6, user.getEmail());
		cmd.setString(7, user.getUserName());
		cmd.setString(8, user.getPassWord());
		cmd.setInt(9, user.getLevel());
		cmd.setInt(10, user.getStatus());
		cmd.setInt(11, user.getReportsNumber());
		
		result = cmd.executeUpdate();
		
		return result;
	}
	
	public int updateUser(User user) throws SQLException {
		int result = 0;
		
		DB.Instance().Connect();
		String query = "UPDATE dbo.[USER]\r\n" + 
				"SET Phone=?,Address=?,Gender=?,BirthDate=?,FullName=?,Email=?,UserName=?,Password=?,Level=?,Status=?,ReportsNumber=?\r\n" + 
				"WHERE UserID = ?";
		PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
		cmd.setString(1, user.getPhone());
		cmd.setString(2, user.getAddress());
		cmd.setInt(3, user.getGender());
		if(user.getBirthday() != null)
			cmd.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
		else
			cmd.setDate(4, null);
		cmd.setString(5, user.getFullName());
		cmd.setString(6, user.getEmail());
		cmd.setString(7, user.getUserName());
		cmd.setString(8, user.getPassWord());
		cmd.setInt(9, user.getLevel());
		cmd.setInt(10, user.getStatus());
		cmd.setInt(11, user.getReportsNumber());
		cmd.setLong(12, user.getUserID());
		result = cmd.executeUpdate();
		
		return result;
	}
	
	public User findUserByResultSet(ResultSet rs) {
		User user = new User();
		try {
			user.setUserID(rs.getLong("UserID"));
			user.setPhone(rs.getString("Phone"));
			user.setAddress(rs.getString("Address"));
			user.setGender(rs.getInt("Gender"));
			user.setBirthday(rs.getDate("BirthDate"));
			user.setFullName(rs.getString("FullName"));
			user.setEmail(rs.getString("Email"));
			user.setUserName(rs.getString("UserName"));
			user.setPassWord(rs.getString("Password"));
			user.setLevel(rs.getInt("Level"));
			user.setStatus(rs.getInt("Status"));
			user.setReportsNumber(rs.getInt("ReportsNumber"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public List<User> findAll() throws SQLException{
		List<User> result = new ArrayList<User>();
		
			DB.Instance().Connect();
			String query = "SELECT * FROM dbo.[USER] ORDER BY UserID DESC";
			PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
			ResultSet rs = cmd.executeQuery();
			
			while(rs.next()) {
				User user = findUserByResultSet(rs);
				result.add(user);
			}
			
			rs.close();
			DB.Instance().cn.close();
		return result;
	}
	
	public User getUserById(long userID) throws SQLException {
		User result = new User();
		DB.Instance().Connect();
		String query = "SELECT * FROM dbo.[USER] WHERE userID = ?";
		PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
		cmd.setLong(1, userID);
		
		ResultSet rs = cmd.executeQuery();
		
		while(rs.next()) {
			result = findUserByResultSet(rs);
		}
		return result;
	}
	
	public int deleteUserById(long userID) throws SQLException {
		int result = 0;

		DB.Instance().Connect();
		String query = "DELETE FROM dbo.[USER] WHERE userID = ?";
		PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
		cmd.setLong(1, userID);
		result = cmd.executeUpdate();
		
		return result;
	}
	
	public int updateStatusById(long userID, int status) throws SQLException {
		int result = 0;

		DB.Instance().Connect();
		String query = "UPDATE dbo.[USER]\r\n" + 
				"SET Status=?\r\n" + 
				"WHERE UserID = ?";
		PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
		cmd.setInt(1, status);
		cmd.setLong(2, userID);
		result = cmd.executeUpdate();
		
		return result;
	}
	
	public List<User> searchUserBySearchKey(String searchKey, String filter) throws SQLException{
		List<User> result = new ArrayList<User>();
			
			DB.Instance().Connect();
			String query = "";
			PreparedStatement cmd = null;
			ResultSet rs = null;
			if (CommonUtils.isNotBlank(filter)) {
				if (filter.equals("filterName")) {
					System.out.println(filter);
					System.out.println(searchKey);
					query = "SELECT * FROM dbo.[USER] u WHERE u.FullName LIKE ? ORDER BY UserID DESC";
					cmd = DB.Instance().cn.prepareStatement(query);
					cmd.setString(1, "%" + searchKey + "%");
					rs = cmd.executeQuery();
				}
				if (filter.equals("filterPhone")) {
					System.out.println(filter);
					System.out.println(searchKey);
					query = "SELECT * FROM dbo.[USER] u WHERE u.Phone LIKE ? ORDER BY UserID DESC";
					cmd = DB.Instance().cn.prepareStatement(query);
					cmd.setString(1, "%" + searchKey + "%");
					rs = cmd.executeQuery();
				}
			} else {
				System.out.println(filter);
				System.out.println(searchKey);
				query = "SELECT * FROM dbo.[USER] u WHERE u.Phone LIKE ? OR u.FullName LIKE ? OR u.Address LIKE ? ORDER BY UserID DESC";
				cmd = DB.Instance().cn.prepareStatement(query);
				cmd.setString(1, "%" + searchKey + "%");
				cmd.setString(2, "%" + searchKey + "%");
				cmd.setString(3, "%" + searchKey + "%");
				rs = cmd.executeQuery();
				
			}
			
			while(rs.next()) {
				User user = findUserByResultSet(rs);
				result.add(user);
			}
			
			rs.close();
			DB.Instance().cn.close();
		return result;
	}
}
