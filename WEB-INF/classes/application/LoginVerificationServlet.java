package application;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;



public class LoginVerificationServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    { 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String sessionVar = (String)request.getSession(true).getAttribute("user");
        out.println("<html>");
        out.println("<head>");

	    String title = "";
	    out.println("<title>" + title  + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

        out.println("<h1>" + "you are" + sessionVar + " !!!!!</h1>");
        out.println("</body>");
        out.println("</html>");
    }

}