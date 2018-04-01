package mediatheque;

import java.util.List;

public interface PersistentMediatheque {
// Jean-François Brette 01/01/2018
	List<Document> tousLesDocuments();

	Document getDocument(int numDocument);

	Utilisateur getUser(String login, String password);
	
	void nouveauDocument(int type, Object... args );
	
	boolean emprunter(Document d, Utilisateur a);

	void retour(Document d);

	List<Document> getUserDocuments(Utilisateur a);
}
