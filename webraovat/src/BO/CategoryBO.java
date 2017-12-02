package BO;

import java.util.ArrayList;
import java.util.List;

import Bean.Category;
import DAO.CategoryDAO;

public class CategoryBO {
	CategoryDAO dm = new CategoryDAO();
	
	public List<Category> getDanhMuc(){
		return dm.getDanhMuc();
	}
	//xoa
	public int xoadm(String categoryID){
		return dm.delete(categoryID);
	}
	//luu
	public int luudm(String categoryID, String name){
		return dm.Update(categoryID, name);
	}
	// them
	public int themdm(Category cate){
		return dm.Add(cate);
	}
	//select sach theo ma
	public Category gettheoma(String categoryID){
		return dm.selecttheoma(categoryID);
	}
}
