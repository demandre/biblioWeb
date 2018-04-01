package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Utilisateur;

public class EmpruntsServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1519964840125168326L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException
	    { 
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        HttpSession session = request.getSession(true);
	        out.println("<html>");
	        out.println("<head>");
	        String title = "BiblioWeb";
	        out.println("<title>" + title  + "</title>");
	        out.println("</head>");
	        out.println("<body bgcolor=\"grey\">");
	        Utilisateur user = (Utilisateur)session.getAttribute("user");
	        if(user==null) {
	        	response.sendRedirect("../");
	        }
	        else if(user.getType()==Utilisateur.BIBLIOTHECAIRE) {
	        	out.println("<p>Bonjour " + user.toString()+ ". Vous n'avez pas le droit d'être ici.</p>");
		        out.println("<a href=\"../bibliothecaire\">Retourner au menu Bibliothecaire</a>");
	        }
	        else {
	        	out.println("<p>Liste de vos emprunts:</p>");
	            out.println("<a href=\"../abonne\">Retourner au menu principal</a>");
	        }
	        out.println("</body>");
	        out.println("</html>");
	    }
}
