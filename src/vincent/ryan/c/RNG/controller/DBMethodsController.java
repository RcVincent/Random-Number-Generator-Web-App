package vincent.ryan.c.RNG.controller;

import java.util.ArrayList;
import java.util.List;

import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;
import vincent.ryan.c.RNG.model.User;


public class DBMethodsController {
	private IDatabase db = null;
	
	public DBMethodsController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	//Add a user to the system
		public void AddUser(String username, String password, String email, String type, String firstname, String lastname){
			db.addUserToDatabase(username, password, email, type, firstname, lastname);
		}
		
		//Change a username 
		public void changeUsername(String name, String newname, String password) {
			db.changeUserName(name, newname, password);
		}
		
		//Remove a user from the system
		public void DeleteUser(String username, String Password) {
			db.DeleteUserFromDatabase(username, Password);
		}
		
		//Match users with their entered password 
		public ArrayList<User> matchUser(String name){
			List<User> userList = db.matchUsernameWithPassword(name);
			ArrayList<User> users = null;
			users = new ArrayList<User>();
			for (User user : userList) {
				users.add(user);
			}			

		return users;
		}
		
		//Return a users account information
		public ArrayList<User> getInfo(String name){
			List<User> userList = db.getAccountInformation(name);
			ArrayList<User> users = null;
			users = new ArrayList<User>();
			for (User user : userList) {
				users.add(user);
			}

		return users;
		}
}
