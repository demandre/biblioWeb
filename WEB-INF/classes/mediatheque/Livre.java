package mediatheque;

public class Livre implements Document{
	
	private Integer id;
	private String nom;
	private String auteur;
	private Utilisateur possesseur = null;
	
	public Livre(int id, String nom, String auteur) {
		this.id=id;
		this.nom=nom;
		this.auteur=auteur;
	}

	@Override
	public boolean emprunter(Utilisateur a){
		Mediatheque mediatheque = Mediatheque.getInstance();
		try{
			mediatheque.emprunt(this, a);
			this.possesseur = a;
			return true;
		} catch(EmpruntException e) {
			return false;
		}
	}

	@Override
	public void retour() {
		Mediatheque.getInstance().retour(this);
		this.possesseur=null;
	}

	@Override
	public Object[] affiche() {
		if(possesseur != null) {
			String[] tab = {id.toString(),"livre",nom,auteur,possesseur.toString()};
			return tab;
		}
		else {
			String[] tab = {id.toString(),"livre",nom,auteur,"Aucun possesseur"};
			return tab;
		}
		
	}

	@Override
	public int getId() {
		return this.id;
	}

}

