package vincent.ryan.c.RNG.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vincent.ryan.c.RNG.controller.GeneratorController;

public class RNGAjaxServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doRequest(req, resp);
	}

	private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Use a controller to process the request
		GeneratorController controller = new GeneratorController();
		Double result = controller.start();
		
		// Send back a response
		resp.setContentType("text/plain");
		resp.getWriter().println(result.toString());
	}
}


