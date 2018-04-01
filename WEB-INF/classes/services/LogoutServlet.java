package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4605467889823220171L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException
	    { 
	        HttpSession session = request.getSession(true);
	        session.setAttribute("user", null);
	        response.sendRedirect("./");
	    }

}
