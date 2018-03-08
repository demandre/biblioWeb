package application;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import data.User;


public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    { 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        User user = new data.User(request.getParameter("login"), request.getParameter("passwd"));
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        out.println("<html>");
        out.println("<head>");

	    String title = "";
	    out.println("<title>" + title  + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

        out.println("<h1>" + "confirmed, follow the link below to test session variable" +"</h1>");
        
        out.println("<a href=\"./verification\">Click here</a>");
        out.println("</body>");
        out.println("</html>");
    }

}