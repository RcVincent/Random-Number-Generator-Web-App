package vincent.ryan.c.RNG.model;

import java.util.Random;

public class User {
	private String Username;
	private String password;
	private String email;
	private int userID;
	private String Fname;
	private String Lname;
	private boolean  status = false;
	private Random rand;
	public User(){
		this.rand = new Random();
		setUserID(rand.nextInt(1000));
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		this.Username = username;
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
}
