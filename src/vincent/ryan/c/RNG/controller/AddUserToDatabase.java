package vincent.ryan.c.RNG.controller;

import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;

public class AddUserToDatabase {
	private IDatabase db = null;
	public AddUserToDatabase() {
		
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	
	}
	public void AddUser(String name, String pswd, String email, String type, String first, String last){
		
		db.addUserToDatabase(name, pswd, email, type, first, last);
	}
}
