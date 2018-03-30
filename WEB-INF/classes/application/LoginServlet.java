package application;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import mediatheque.*;

public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    { 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        session.setAttribute("user", "");
        System.out.println("ok:");
        // lauchn mediatheque data static to initialize mediatheque class
        try {
			Class.forName(this.getInitParameter("data class"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        Utilisateur user = Mediatheque.getInstance().getUser(request.getParameter("login"), request.getParameter("passwd"));
        
        out.println("<html>");
        out.println("<head>");

	    String title = "BiblioWeb login";
	    out.println("<title>" + title  + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"grey\">");

        out.println("<p>" + "Bonjour "+ user.toString() +"!</p>");
        out.println("<a href=\"./verification\">Cliques ici pour vérifier les variables session</a>");
        out.println("</body>");
        out.println("</html>");
    }
	
}