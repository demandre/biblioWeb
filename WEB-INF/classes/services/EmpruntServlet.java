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

public class EmpruntServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6392127897722390291L;

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
	        	out.println("<p>Emprunter un document:</p>");
	        	out.println("Cochez le document que vous voulez emprunter, puis cliquez sur envoyer.");
	        	out.println("Id | Type | Nom | Auteur");
	        	out.println("<form action=\"./emprunter\" method=\"post\">");
	        	out.println("<fieldset>");
	        	for (Document doc : Mediatheque.getInstance().tousLesDocuments()) {
	        		String[] docString = (String[])doc.affiche();
	        		if(docString[4].equals("Aucun possesseur")) {
		        		out.println("<input type=\"radio\" name=\"doc\" value=\""+ docString[0] +"\">" + docString[0] + " " + docString[1] + " "  + docString[2] + " "  + docString[3] +"<br>");
	        		}
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
	        HttpSession session = request.getSession(true);
	        out.println("<html>");
	        out.println("<head>");
	        String title = "BiblioWeb login";
	        out.println("<title>" + title  + "</title>");
	        out.println("</head>");
	        out.println("<body bgcolor=\"grey\">");
	        Utilisateur user = (Utilisateur)session.getAttribute("user");
	        if(!new Livre(Integer.parseInt(request.getParameter("doc")),"","").emprunter(user)) {
	        	out.println("<p>Le document n'a pas été emprunté... veuillez reesayer.");
	        }
	        else {
	        	out.println("<p>Le document a bien été emprunté.");
	        }
            out.println("<a href=\"../abonne/emprunter\">Retourner a la page d'emprunt</a>");
	        out.println("</body>");
	        out.println("</html>");
	    }
}
