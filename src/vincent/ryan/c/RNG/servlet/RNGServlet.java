package vincent.ryan.c.RNG.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vincent.ryan.c.RNG.controller.GeneratorController;
import vincent.ryan.c.RNG.model.RandomNumberGenerator;
public class RNGServlet {
	private static final long serialVersionUID = 1L; 
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		req.getRequestDispatcher("/_view/RNG.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	RandomNumberGenerator model = new RandomNumberGenerator();
	GeneratorController c = new GeneratorController(); 
	
	c.setModel(model);
	
	if(req.getParameter("Generate") != null) {
		c.start();
	}
	else {
		//double 
	}
	
	req.setAttribute("Generator", model);
	
	req.getRequestDispatcher("/_view/RNG.jsp").forward(req, resp);
	}
	
	/*private int getInteger(HttpServletRequest req, String name){
		return Integer.parseInt(req.getParameter(name));
	}*/
	
}

