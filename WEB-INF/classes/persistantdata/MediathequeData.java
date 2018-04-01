package persistantdata;

import java.util.List;

import mediatheque.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Fran�ois Brette 01/01/2018
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
		DocumentRequete.init("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/biblioweb");
		UtilisateurRequete.init("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/biblioweb");
	}

	private MediathequeData() {
	}

	// renvoie la liste de tous les documents de la biblioth�que
	@Override
	public List<Document> tousLesDocuments() {
		return null;
	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
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

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return null;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
	}

}
