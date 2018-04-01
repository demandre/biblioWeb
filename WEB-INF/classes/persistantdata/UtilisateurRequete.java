package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRequete {
	static Connection CONNECT;

	public static void init(String driver, String url){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			CONNECT = DriverManager.getConnection(url, "biblioweb", "biblioweb");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connected to database");
	}
	
	public static Integer getUserType(String username){
		Integer response = null;
		try {
			String sql = "select type from utilisateur where username = ?";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			req.setString(1,username);
			ResultSet ans = req.executeQuery();
			ans.next();
			response=ans.getInt("type");
			req.close();
			ans.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static boolean verifLogin(String username, String password) {
		try {
			String sql = "select id from utilisateur where username = ? and password = ?";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			req.setString(1,username);
			req.setString(2,password);
			ResultSet ans = req.executeQuery();
			ans.next();
			ans.getInt("id");
			boolean wasNull = ans.wasNull();
			req.close();
			ans.close();
			if(wasNull) {
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("tentative de connection non fructueuse.");
			return false;
		}
	}
	
	public static Integer getIdByUsername(String username) {
		try {
			String sql = "select id from utilisateur where username = ?";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			req.setString(1,username);
			ResultSet ans = req.executeQuery();
			ans.next();
			Integer id = ans.getInt("id");
			req.close();
			ans.close();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
