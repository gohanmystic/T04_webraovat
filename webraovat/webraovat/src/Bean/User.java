package Bean;

import java.util.Date;

public class User {
	private long userID;
	private String phone;
	private String address;
	private int gender;
	private Date birthday;
	private String fullName;
	private String email;
	private String userName;
	private String passWord;
	private int level;
	private int status;
	private int reportsNumber;
	
	public User() {
		super();
	}

	public User(long userID, String phone, String address, int gender, Date birthday, String fullName, String email,
			String userName, String passWord, int level, int status, int reportsNumber) {
		super();
		this.userID = userID;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.birthday = birthday;
		this.fullName = fullName;
		this.email = email;
		this.userName = userName;
		this.passWord = passWord;
		this.level = level;
		this.status = status;
		this.reportsNumber = reportsNumber;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getReportsNumber() {
		return reportsNumber;
	}

	public void setReportsNumber(int reportsNumber) {
		this.reportsNumber = reportsNumber;
	}
	
}
