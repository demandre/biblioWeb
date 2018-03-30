package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Requete {
	
	static Connection CONNECT;

	public static void init(String driver, String url){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			CONNECT = DriverManager.getConnection(url, "DEMANDREJ", "DEMANDREJ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("dak");
	}
	
	public static int select(String table, String[] colonnes, String[] where){
		/*String query = "Select ";
		for (int i = 0; i < colonnes.length; i++) {
			query = query + colonnes[i] + ", ";
		}
		// remove the last coma
		query = query.substring(0,query.length()-2);
		query =query+" from "+ table;
		if( where.length != 0 ){
			query+=" where ";
			for (int i = 0; i < where.length; i++) {
				if( i%2 == 0){
					if( i > 2){
						query+=" and ";
					}
					query = query + where[i] + " = ";
				}
				else {
					query = query +"'" +where[i]+"'";
				}
			}
		}
		System.out.println(query);
		Statement req = null;
		try {
			req = CONNECT.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = req.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.next();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] response = new String[colonnes.length] ;
		for (int i = 0; i < colonnes.length; i++) {
			try {
				System.out.println(rs.getInt(colonnes[i]));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return response;*/
		int response = 0;
		try {
			Statement req = CONNECT.createStatement();
			ResultSet rs = req.executeQuery("select * from utilisateur");
			rs.next();
			response=rs.getInt("type");
			System.out.println(response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
}
