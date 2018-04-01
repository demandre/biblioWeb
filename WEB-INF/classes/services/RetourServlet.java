package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Document;
import mediatheque.Livre;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class RetourServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1119870331602516682L;

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
	        	out.println("<p>Retourner un document:</p>");
	        	out.println("Cochez les documents que vous voulez retourner, puis cliquez sur envoyer.");
	        	out.println("Id | Type | Nom | Auteur");
	        	out.println("<form action=\"./retourner\" method=\"post\">");
	        	out.println("<fieldset>");
	        	for (Document doc : Mediatheque.getInstance().getUserDocuments(user)) {
	        		String[] docString = (String[])doc.affiche();
		        	out.println("<input type=\"radio\" name=\"doc\" value=\""+ docString[0] +"\">" + docString[0] + " " + docString[1] + " "  + docString[2] + " "  + docString[3] +"<br>");
	        	}
	        	out.println(" <input type=\"submit\" name=\"submit\" value=\"Envoyer\" /></fieldset></form>");
	            out.println("<a href=\"../abonne\">Retourner au menu principal</a>");
	        }
	        out.println("</body>");
	        out.println("</html>");
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException
	    { 
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        String title = "BiblioWeb login";
	        out.println("<title>" + title  + "</title>");
	        out.println("</head>");
	        out.println("<body bgcolor=\"grey\">");
	        new Livre(Integer.parseInt(request.getParameter("doc")),"","").retour();
	        out.println("<p>Le document a bien été retourné");
            out.println("<a href=\"../abonne/retourner\">Retourner a la page de retour</a>");
	        out.println("</body>");
	        out.println("</html>");
	    }
}
