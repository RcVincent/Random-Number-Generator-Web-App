package vincent.ryan.c.RNG.controller;

import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;

public class ChangeUsername {
	private IDatabase db = null;
	public ChangeUsername() {
		
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	
	}
	public void changeUsername(String name, String newName, String pswd){
		
		db.changeUsername(name, newName, pswd);
	}
}

