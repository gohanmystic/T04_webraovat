package Bean;

public class Category {
	private String CategoryID;
	private String Name;
	
	public String getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(String categoryID) {
		CategoryID = categoryID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Category(String categoryID, String name) {
		super();
		CategoryID = categoryID;
		Name = name;
	}
	
}
