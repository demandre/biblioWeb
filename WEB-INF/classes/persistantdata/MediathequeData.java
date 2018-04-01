package persistantdata;

import java.util.List;

import mediatheque.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
		DocumentRequete.init("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/biblioweb");
		UtilisateurRequete.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/biblioweb");
	}

	private MediathequeData() {
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public synchronized List<Document> tousLesDocuments() {
		return DocumentRequete.getAllDocuments();
	}
	
	public synchronized List<Document> getUserDocuments(Utilisateur a) {
		return DocumentRequete.getDocumentEmprunteParUser(a.getId());
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		synchronized(this) {
			if( UtilisateurRequete.verifLogin(login,password) ) {
				Integer type = UtilisateurRequete.getUserType(login);
				Integer id = UtilisateurRequete.getIdByUsername(login);
				if(type == null || id == null) {
					return null;
				}
				else return new Utilisateur(login,type,id);
			}
			else return null;
		}
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public synchronized Document getDocument(int numDocument) {
		return DocumentRequete.getDocumentById(numDocument);
	}

	@Override
	public synchronized void nouveauDocument(int type, Object... args) {
		DocumentRequete.insertDocument(type,(String)args[0],(String)args[1]);
	}

	@Override
	public synchronized boolean emprunter(Document d, Utilisateur a) {
		return DocumentRequete.emprunter(d.getId(),a.getId());
	}

	@Override
	public synchronized void retour(Document d) {
		DocumentRequete.retourner(d.getId());
	}

}
