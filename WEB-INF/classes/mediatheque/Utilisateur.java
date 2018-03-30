package mediatheque;

public class Utilisateur {
	
	final int BIBLIOTHECAIRE=1;
	final int ABONNE=2;
	
	private String login;
	private int type;
	
	public Utilisateur(String login, int type){
		this.type=type;
		this.login=login;
	}
	
	public int getType(){
		return this.type;
	}
	
	public String toString(){
		return this.login;
	}
	
}
