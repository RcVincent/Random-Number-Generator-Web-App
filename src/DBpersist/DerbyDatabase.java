package DBpersist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBpersist.DBUtil;

import vincent.ryan.c.RNG.model.User;


public class DerbyDatabase implements IDatabase {
		static {
			try {
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			} catch (Exception e) {
				throw new IllegalStateException("Could not load Derby driver");
			}
		}

		private interface Transaction<ResultType> {
			public ResultType execute(Connection conn) throws SQLException;
		}

		private static final int MAX_ATTEMPTS = 100;
		
		@Override
		public List<User> addUserToDatabse(final String name, final String pass, final String email, final String type, final String firstN, final String lastN) {
			return executeTransaction(new Transaction<List<User>>() {
				
				@Override
				public List<User> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					PreparedStatement stmt2 = null;
					ResultSet resSet = null;
					
					try{
						stmt = conn.prepareStatement(
								"insert into users(user_username, user_password, user_email, user_accountType, user_Fname, user_Lname)" + 
										" values(?,?,?,?,?,?)"  
								);
								
						stmt.setString(1, name);
						stmt.setString(2, pass);
						stmt.setString(3, email);
						stmt.setString(4, type);
						stmt.setString(5, firstN);
						stmt.setString(6, lastN);
						
						stmt2 = conn.prepareStatement(
								"select * " +
										" from users " + 
										" where user_Username = ?"
								);
						stmt2.setString(1, name);
						resSet = stmt2.executeQuery();
						
						Boolean found = false;
						List<User> result = new ArrayList<User>();
						
						while(resSet.next()) {
							found = true;
							User u = new User();
							loadUser(u, resSet, 1);
							result.add(u);
						}
						
						// check if the title was found
						if (!found) {
							System.out.println("<" + name + "> was not found in the users table");
						}

						return result;
					
					}
					finally {
						DBUtil.closeQuietly(resSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
					}
				}
					
			
			});
		
		}
		
		public List<User> DeleteUserFromDatabase(final String username, final String password){
			return executeTransaction(new Transaction<List<User>>() {
				public List<User> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					PreparedStatement stmt2 = null;
					ResultSet resSet = null; 
					
					try {
						stmt = conn.prepareStatement(
								"delete from users" +
										"where user_username = ?" +
										"and where user_password = ?"
								);
						stmt.setString(1, username);
						stmt.setString(2, password);
						stmt.executeUpdate();
						
						stmt2 = conn.prepareStatement(
								"select * from users"
								);
						resSet = stmt2.executeQuery();
						Boolean found = false;
						List<User> result = new ArrayList<User>();
						
						while(resSet.next()) {
							found = true;
							User u = new User();
							loadUser(u, resSet, 1);
							result.add(u);
						}
					
						if (!found) {
						System.out.println("<" + username + "> users list is empty");
						}

						return result;
					}
					finally {
						DBUtil.closeQuietly(conn);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
					}
				}
				
			});
		
}
		
		@Override
		public List<User> matchUsernameWithPassword(final String name) {
			
			return executeTransaction(new Transaction<List<User>>() {
				@Override
				public List<User> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;

					try {


						stmt = conn.prepareStatement(
								"select * from Users " +
										" where user_username = ? "
								);
						stmt.setString(1, name);
						List<User> result = new ArrayList<User>();
						resultSet = stmt.executeQuery();

						// for testing that a result was returned
						Boolean found = false;

						while (resultSet.next()) {
							found = true;

							User u = new User();
							loadUser(u, resultSet, 1);
							result.add(u);
						}

						// check if the title was found
						if (!found) {
							System.out.println("<" + name + "> was not found in the Users table");
						}

						return result;


					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
		}
		
		public List<User> changeUserName(final String username, final String newUserName, final String password) {
			return executeTransaction(new Transaction<List<User>>() {
				
				public List<User> execute(Connection conn) throws SQLException {
					
				
					PreparedStatement stmt = null;
					PreparedStatement stmt2 = null;
					ResultSet resSet = null;
				
					try{
						stmt = conn.prepareStatement(
								"update Users" + 
										"set user_username = ?" +
 										"where user_username = ? " +
										"and user_password = ?"
								);
						
						stmt.setString(1, newUserName);
						stmt.setString(2, username);
						stmt.setString(3, password);
						stmt.executeUpdate();
						
						stmt2 =  conn.prepareStatement(
								"select * from users" +
										"where user_username = ?"
								);
						stmt.setString(1, newUserName);
						
					}
					finally {
						//DBUtil.closeQuietly(conn);
						DBUtil.closeQuietly(resSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
					}
				}
			});
			
		}
		
		
		public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
			try {
				return doExecuteTransaction(txn);
			} catch (SQLException e) {
				throw new PersistenceException("Transaction failed", e);
			}
		}

		public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
			Connection conn = connect();

			try {
				int numAttempts = 0;
				boolean success = false;
				ResultType result = null;

				while (!success && numAttempts < MAX_ATTEMPTS) {
					try {
						result = txn.execute(conn);
						conn.commit();
						success = true;
					} catch (SQLException e) {
						if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
							// Deadlock: retry (unless max retry count has been reached)
							numAttempts++;
						} else {
							// Some other kind of SQLException
							throw e;
						}
					}
				}

				if (!success) {
					throw new SQLException("Transaction failed (too many retries)");
				}

				// Success!
				return result;
			} finally {
				DBUtil.closeQuietly(conn);
			}
		}

		private Connection connect() throws SQLException {
			Connection conn = DriverManager.getConnection("jdbc:derby:H:/workspace.newDBarea;create=true");

			// Set autocommit to false to allow multiple the execution of
			// multiple queries/statements as part of the same transaction.
			conn.setAutoCommit(false);

			return conn;
		}
		//these build the collections to return to the servlets, controlles
		private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
			user.setUserID(resultSet.getInt(index++));
			user.setUsername(resultSet.getString(index++));
			user.setPassword(resultSet.getString(index++));
			user.setEmail(resultSet.getString(index++));
			user.setAccountType(resultSet.getString(index++));
			user.setFname(resultSet.getString(index++));
			user.setLname(resultSet.getString(index++));
		}
		
		
		//creating the tables
		public void createTables() {
			executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					try {
						stmt1 = conn.prepareStatement(
								"create table users (" +
										"	user_id integer primary key " +
										"		generated always as identity (start with 1, increment by 1), " +									
										"	user_userName varchar(40),"     +
										"	user_passWord varchar(40), "     +
										"   user_email varchar(40), "        +
										"   user_accountType varchar(30), " +
										"    user_firstName varchar(50), "  +
										"    user_lastNAme varchar(50) "    +
										")"
								);	
						stmt1.executeUpdate();

					
						return true;
					} finally {
						DBUtil.closeQuietly(stmt1);
						
					}
				}
			});
		}
		
		//loading initial data for basic website navigation
		public void loadInitialData() {
			executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					List<User> userList;
					try {	
						userList = InitialData.getUsers();
						
					} catch (IOException e) {
						throw new SQLException("Couldn't read initial data", e);
					}

					PreparedStatement insertRestaurants = null;
					PreparedStatement insertUsers = null;
					PreparedStatement insertMenus = null;
					try {


						insertUsers = conn.prepareStatement("insert into users (user_userName, user_passWord, user_email, user_accountType, user_firstName, user_Lastname) "
								+ "		values (?, ?, ?, ?, ?, ?)");
						for (User u : userList) {
							insertUsers.setString(1, u.getUsername());
							insertUsers.setString(2, u.getPassword());
							insertUsers.setString(3, u.getEmail());
							insertUsers.setString(4, u.getAccountType());
							insertUsers.setString(5, u.getFname());
							insertUsers.setString(6, u.getLname());
							insertUsers.addBatch();
						}
						insertUsers.executeBatch();
						System.out.println("Users table populated");


						
						return true;
					} finally {
						DBUtil.closeQuietly(insertUsers);
						
						
						
					}
				}
			});
		}

		// The main method creates the database tables and loads the initial data.
		public static void main(String[] args) throws IOException {
			System.out.println("Creating tables...");
			DerbyDatabase db = new DerbyDatabase();
			db.createTables();
			System.out.println("Loading initial data...");
			db.loadInitialData();
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
