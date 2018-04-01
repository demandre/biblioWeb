// Jean-François Brette 01/01/2018

package mediatheque;

public interface Document {
// Jean-François Brette 01/01/2018

	static final int LIVRE = 1;
	static final int HEBDOMADAIRE = 2;
	static final int CD = 3;
	
	boolean emprunter(Utilisateur a);
	void retour();
	Object[] affiche();
	int getId();
}
