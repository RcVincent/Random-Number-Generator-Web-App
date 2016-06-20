package vincent.ryan.c.RNG.model;

import java.util.Random;

public class User {
	private String username;
	private String password;
	private String email;
	private int userID;
	private String Fname;
	private String Lname;
	private String type;
	private String accountType;
	private boolean  status = false;
	private Random rand;
	private boolean isAdmin;
	private final String adminType = "admin";
	private final String userType = "user"; 
	
	public User(){
		this.rand = new Random();
		setUserID(rand.nextInt(1000));
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}
	
	public boolean logIn(String username, String password) {
		if(!status)
			status = true;
		return status;
	}

	public boolean authenticate(String username, String password) {
		return false;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getAccountType(){
		return accountType;
	}
	
	public void setAccountType(String t){
		this.accountType = t;
	}
	
	public void setAccountInfo(String fn, String ln, String email){
		this.Fname = fn;
		this.Lname = ln;
		this.email = email;
	}
	
	public void isAdmin(boolean o) {
		this.isAdmin = o;
		if(o) {
			this.accountType = adminType;
		}
		else {
			this.accountType = userType;
		}
	}
	
	
}
