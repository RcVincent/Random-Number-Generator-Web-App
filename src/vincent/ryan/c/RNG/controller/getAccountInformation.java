package vincent.ryan.c.RNG.controller;

import java.util.ArrayList;
import java.util.List;

import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;
import vincent.ryan.c.RNG.model.User;

public class getAccountInformation {
	private IDatabase db = null;
	public getAccountInformation() {
		
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	
	}
	public ArrayList<User> getInfo(String name){
		List<User> userList = db.matchUsernameWithPassword(name);
		ArrayList<User> users = null;
		users = new ArrayList<User>();
		for (User user : userList) {
			users.add(user);
		}
	
	return users;
	}
}