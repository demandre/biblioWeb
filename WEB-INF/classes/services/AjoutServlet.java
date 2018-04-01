package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Utilisateur;

public class AjoutServlet extends HttpServlet{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5991146000142469952L;

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
        else if(user.getType()==Utilisateur.ABONNE) {
        	out.println("<p>Bonjour " + user.toString()+ ". Vous n'avez pas le droit d'être ici.</p>");
	        out.println("<a href=\"../abonne\">Retourner au menu Abonné</a>");
        }
        else {
        	out.println("<form action=\"./bibliothecaire/ajout\" method=\"post\">");
        	out.println("<fieldset><legend>Ajoutez un document!</legend>");
        	out.println("<p>" + 
        			"                <label>type:</label>" + 
        			"                <input type=\"radio\" name=\"type\" value=\"1\" checked> Livre" + 
        			"                <input type=\"radio\" name=\"type\" value=\"2\"> Hebdo" + 
        			"                 <input type=\"radio\" name=\"type\" value=\"3\"> CD" + 
        			"                <label>nom:</label>" + 
        			"                <input name=\"nom\" type=\"text\" />" + 
        			"				<label>auteur</label>" + 
        			"                <input name=\"auteur\" type=\"text\" />" + 
        			"                <input type=\"submit\" name=\"submit\" value=\"Envoyer\" />" + 
        			"            </p>");
        	out.println("</fieldset></form>");
            out.println("<a href=\"../bibliothecaire\">Retourner au menu principal</a>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}