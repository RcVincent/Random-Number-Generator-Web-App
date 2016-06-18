package DBpersist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vincent.ryan.c.RNG.model.User;

public class InitialData {
	//user db will be for authentication and hold a primary key for patrons and Owners
	public static List<User> getUsers() throws IOException {
		List<User> UserList = new ArrayList<User>();
		ReadCSV readUser = new ReadCSV("users.csv");
		try {
			// auto-generated primary key for table User
			Integer UserId = 1;
			while (true) {
				List<String> tuple = readUser.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User User = new User();

				// read User ID from CSV file, but don't use it
				// auto-generate User ID, instead
				User.setUserID(UserId++);				
				User.setUsername(i.next());
				User.setPassword(i.next());
				User.setEmail(i.next());
				//User.setAccountType(i.next());
				User.setFname(i.next());
				User.setFname(i.next());
				UserList.add(User);
			}
			System.out.println("UserList loaded from CSV file");			
			return UserList;
		} finally {
			readUser.close();
		}
	}
}
