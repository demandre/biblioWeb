package data;

public class User {
	String login="toto";
	String password="toto";
	
	public User(String login, String password){
		this.login=login;
		this.password=password;
	}
	
	public String toString(){
		return this.login+this.password;
	}
}
