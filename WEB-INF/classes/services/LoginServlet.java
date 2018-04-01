package services;
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
        out.println("<html>");
        out.println("<head>");
        String title = "BiblioWeb login";
        out.println("<title>" + title  + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"grey\">");
        if(request.getParameter("login").equals(this.getInitParameter("admin login")) && request.getParameter("passwd").equals(this.getInitParameter("admin password"))) {
        	try {
        		// Si les identitifiants de l'admin sont entrés, initialise la classe de persistance
    			Class.forName(this.getInitParameter("data class"));
    		} catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		}
        	out.println("<p>Bonjour admin. La dataclass a été initialisée. Bonne journée!</p>");
        	out.println("<a href=\"./\">Log out</a>");
        }
        else {
        	try {
	        	Utilisateur user = Mediatheque.getInstance().getUser(request.getParameter("login"), request.getParameter("passwd"));
	            session.setAttribute("user", user);
	            if(user == null) {
	            	out.println("<p> Nous n'avons pas réussi à vous connecter... </p>");
	            	out.println("<a href=\"./\">Revenir sur la page de log in</a>");
	            }
	            else if(user.getType() == Utilisateur.BIBLIOTHECAIRE) {
	            	response.sendRedirect("./bibliothecaire");
	            }
	            else if(user.getType() == Utilisateur.ABONNE) {
	            	response.sendRedirect("./abonne");
	            }
        	} catch(NullPointerException e) {
        		out.println("<p> Le serveur de la bibliothèque n'est pas parametré. Veuillez contacter l'administrateur.</p>");
        		out.println("<a href=\"./\">Revenir sur la page de log in</a>");
        	}
        }
        out.println("</body>");
        out.println("</html>");
    }
	
}