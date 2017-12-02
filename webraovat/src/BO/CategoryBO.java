package BO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.Category;
import DAO.CategoryDAO;

public class CategoryBO {
	public CategoryDAO cateDao = new CategoryDAO();
	public int insertCate(Category cate){
		try {
			return cateDao.insertCate(cate);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int updateCate(Category cate){
		try {
			return cateDao.updateCate(cate);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int deleteCateById(long CategoryID) {
		try {
			return cateDao.deletePost(CategoryID);
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public List<Category> findAll(){
		try {
			return cateDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Category> searchCateBySearchKey(String searchKey, String filter){
		try {
			return cateDao.searchCateBySearchKey(searchKey, filter);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Category getCateById(long CategoryID) {
		try {
			return cateDao.getCateById(CategoryID);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
