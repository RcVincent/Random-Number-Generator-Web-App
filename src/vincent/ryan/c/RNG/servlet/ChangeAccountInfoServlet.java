package vincent.ryan.c.RNG.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vincent.ryan.c.RNG.controller.GeneratorController;
import vincent.ryan.c.RNG.controller.DBMethodsController;
import vincent.ryan.c.RNG.model.User;


public class ChangeAccountInfoServlet {
	private static final long serialVersionUID = 1L;
	
	private DBMethodsController c = new DBMethodsController(); 
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = (String) req.getSession().getAttribute("username");
		if (user == null) {
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/Login");
			return;
		}
		req.getRequestDispatcher("/_view/ChangeUsername.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Decode form parameters and dispatch to controller
		String errorMessage = null;
		String username = null;
		String newUsername = null;
		String password = null;
		
		//get parameters from the jsp
		username = req.getParameter("username");
		newUsername = req.getParameter("newUsername");
		password = req.getParameter("password");
		
		//Validate login credentials
		ArrayList<User> user = null;
		user = c.matchUser(username);
		if(user.size()>0){
			User u = user.get(0);
			GeneratorController controller = new GeneratorController();
			
			//if user is authenticated, call change password
			if(controller.authenticate(u, password)){
				c.changeUsername(username, newUsername, password);
				
				//set session username to new username
				req.getSession().setAttribute("username", newUsername);
				errorMessage = "New username was successfully created";
				req.setAttribute("errorMessage", errorMessage);
				req.getRequestDispatcher("/_view/ChangeUsername.jsp").forward(req, resp);
			}
			else{
				
				errorMessage = "Incorrect Username or Password";
				req.setAttribute("errorMessage", errorMessage);
				req.getRequestDispatcher("/_view/ChangeUsername.jsp").forward(req, resp);
			}
		}
		//otherwise, print an error message
	}
}
