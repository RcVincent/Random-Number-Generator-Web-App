package DBpersist;

import java.util.List;

import vincent.ryan.c.RNG.model.User;

public interface IDatabase  {
	 List<User> addUserToDatabase(final String name, final String pass, final String email, final String type, final String firstN, final String lastN);
	 List<User> matchUsernameWithPassword(String name);
	 List<User> DeleteUserFromDatabase(String username, String password);
	 List<User> changeUserName(final String username, final String newUserName, final String password);
	 List<User> getAccountInformation(String name); 	
	
}
