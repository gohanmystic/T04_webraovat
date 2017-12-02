package BO;

import java.sql.SQLException;
import java.util.List;

import Bean.User;
import DAO.UserDAO;

public class UserBO {
	public UserDAO userDAO = new UserDAO();
	
	public int createUser(User user){
		try {
			return userDAO.createUser(user);
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public int updateUser(User user) {
		try {
			return userDAO.updateUser(user);
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public List<User> findAll(){
		try {
			return userDAO.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public User getUserById(long userID) {
		try {
			return userDAO.getUserById(userID);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int deleteUserById(long userID) {
		try {
			return userDAO.deleteUserById(userID);
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public int updateStatusById(long userID, int status) {
		try {
			return userDAO.updateStatusById(userID, status);
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public List<User> searchUserBySearchKey(String searchKey, String filter){
		try {
			return userDAO.searchUserBySearchKey(searchKey, filter);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
