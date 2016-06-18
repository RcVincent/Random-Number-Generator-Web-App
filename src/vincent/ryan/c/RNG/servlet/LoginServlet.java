package vincent.ryan.c.RNG.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vincent.ryan.c.RNG.model.User;
import vincent.ryan.c.RNG.controller.*;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//private matchUsernameWithPassword match = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/Login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Decode form parameters and dispatch to controller
		String errorMessage = null;
		String username = null;
		String password = null;
		username = req.getParameter("username");
		password = req.getParameter("password");
		//match = new matchUsernameWithPassword();
		ArrayList<User> user = null;
		//user = match.matchUser(username);
		if(user.size()>0){
			User u = user.get(0);
			GeneratorController controller = new GeneratorController();
			
			if(controller.authenticate(u, password)){
				//Set the session true and set their username
				req.getSession(true).setAttribute("username", username);
				
				req.getSession().setAttribute("userID", u.getUserID());
				//If user is an owner send them to a page of their restaurants
				//if(u.getAccountType().equals("owner")){
				//	resp.sendRedirect(req.getContextPath() + "/OwnerPage");
				//}
				//If user is a patron send to the homepage
				
				resp.sendRedirect(req.getContextPath() + "/Homepage");
				

			}
			//display error meassage for incorrect username or password
			else{
				errorMessage = "Incorrect Username or Password";
				req.setAttribute("errorMessage", errorMessage);
				req.getRequestDispatcher("/_view/Login.jsp").forward(req, resp);
			}
		}
		//otherwise, print an error message
		else{
			errorMessage = "Incorrect Username or Password";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/Login.jsp").forward(req, resp);
		}
	
}
}
	