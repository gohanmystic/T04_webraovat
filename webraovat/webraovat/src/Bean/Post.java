package Bean;

import java.util.Date;

public class Post {
	private int PostID;
	private String Title;
	private Date CreationDate;
	private String Content;
	private long Price;
	private long UserID;
	private long PostTypeID;
	private long CategoryID;
	private Boolean Status;
	private String Image;
	
	public Post(int postID, String title, Date creationDate, String content, long price, long userID, long postTypeID,
			long categoryID, Boolean status, String image) {
		super();
		PostID = postID;
		Title = title;
		CreationDate = creationDate;
		Content = content;
		Price = price;
		UserID = userID;
		PostTypeID = postTypeID;
		CategoryID = categoryID;
		Status = status;
		Image = image;
	}
	public int getPostID() {
		return PostID;
	}
	public void setPostID(int postID) {
		PostID = postID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Date getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public long getPrice() {
		return Price;
	}
	public void setPrice(long price) {
		Price = price;
	}
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}
	public long getPostTypeID() {
		return PostTypeID;
	}
	public void setPostTypeID(long postTypeID) {
		PostTypeID = postTypeID;
	}
	public long getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(long categoryID) {
		CategoryID = categoryID;
	}
	public Boolean getStatus() {
		return Status;
	}
	public void setStatus(Boolean status) {
		Status = status;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
}
