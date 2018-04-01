package mediatheque;

public class Utilisateur {
	
	public final static int BIBLIOTHECAIRE=1;
	public final static int ABONNE=2;
	
	private String login;
	private int type;
	private int id;
	
	public Utilisateur(String login, int type, int id){
		this.type=type;
		this.login=login;
		this.id=id;
	}
	
	public int getType(){
		return this.type;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String toString(){
		return this.login;
	}
	
}
