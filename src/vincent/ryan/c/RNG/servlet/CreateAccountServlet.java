package vincent.ryan.c.RNG.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vincent.ryan.c.RNG.controller.DBMethodsController;

public class CreateAccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private DBMethodsController c = new DBMethodsController();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		req.getRequestDispatcher("/_view/CreateAccount.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String Firstname = null;
		String Lastname = null;
		String Username = null;
		String Password = null;
		String email = null;
		String accountType = null;
		
		Firstname = req.getParameter("first name");
		Lastname = req.getParameter("Lastname");
		Username = req.getParameter("Username");
		Password = req.getParameter("Password");
		email = req.getParameter("email");
		accountType = req.getParameter("accountType"); 
		
		//set session username
		req.getSession().setAttribute("username", Username);
				
		
		//Adds the user to the database
		c.AddUser(Username, Password, email, accountType, Firstname, Lastname);
		//Redirect to login page
		req.getRequestDispatcher("/_view/Login.jsp").forward(req, resp);
	}
}
