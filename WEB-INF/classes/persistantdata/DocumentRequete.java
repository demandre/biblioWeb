package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DocumentRequete{
	
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
	
	public static boolean estEmprunte(int type, int id) {
		try {
			String sql = "select empruntor from document where id = ?";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			req.setInt(1,id);
			ResultSet ans = req.executeQuery();
			ans.next();
			ans.getInt("empruntor");
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
			e.printStackTrace();
			return true;
		}
	}
	
	public static boolean emprunter(int documentId, int userId) {
		try {
			String sql = "update document set empruntor = ? where id = ?";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			req.setInt(1,userId);
			req.setInt(2,documentId);
			int ans = req.executeUpdate();
			req.close();
			if(ans == 1) {
				return true;
			}
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean retourner(int documentId) {
		try {
			String sql = "update document set empruntor = null where id = ?";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			req.setInt(1,documentId);
			int ans = req.executeUpdate();
			req.close();
			if(ans == 1) {
				return true;
			}
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
