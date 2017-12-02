package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DB;

public class BaseDAO {
	
	public ResultSet findAllByTableName(String tenBang) throws SQLException {
		ResultSet rs = null;
		try {
			DB.Instance().Connect();
			String query = "select * from " + tenBang;
			
			PreparedStatement cmd = DB.Instance().cn.prepareStatement(query);
			
			rs = cmd.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}
