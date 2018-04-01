package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatheque.Cd;
import mediatheque.Document;
import mediatheque.Hebdomadaire;
import mediatheque.Livre;


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
	
	public static List<Document> getDocumentEmprunteParUser(int id) {
		try {
			String sql = "select * from document where empruntor = ?";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			req.setInt(1,id);
			ResultSet ans = req.executeQuery();
			List<Document> docList = new ArrayList<Document>();
			while(ans.next()) {
				int type = ans.getInt("type");
				if(type == Document.LIVRE) {
					docList.add(new Livre(ans.getInt("id"),ans.getString("titre"),ans.getString("auteur")));
				}
				else if(type == Document.HEBDOMADAIRE) {
					docList.add(new Hebdomadaire(ans.getInt("id"),ans.getString("titre"),ans.getString("auteur")));
				}
				else if(type == Document.CD) {
					docList.add(new Cd(ans.getInt("id"),ans.getString("titre"),ans.getString("auteur")));
				}
			}
			req.close();
			ans.close();
			return docList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Document> getAllDocuments() {
		try {
			String sql = "select * from document";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			ResultSet ans = req.executeQuery();
			List<Document> docList = new ArrayList<Document>();
			while(ans.next()) {
				int type = ans.getInt("type");
				if(type == Document.LIVRE) {
					docList.add(new Livre(ans.getInt("id"),ans.getString("titre"),ans.getString("auteur")));
				}
				else if(type == Document.HEBDOMADAIRE) {
					docList.add(new Hebdomadaire(ans.getInt("id"),ans.getString("titre"),ans.getString("auteur")));
				}
				else if(type == Document.CD) {
					docList.add(new Cd(ans.getInt("id"),ans.getString("titre"),ans.getString("auteur")));
				}
			}
			req.close();
			ans.close();
			return docList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Document getDocumentById(int id) {
		try {
			String sql = "select * from document where id = ?";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			req.setInt(1,id);
			ResultSet ans = req.executeQuery();
			Document doc = null;
			while(ans.next()) {
				int type = ans.getInt("type");
				if(type == Document.LIVRE) {
					doc = new Livre(ans.getInt("id"),ans.getString("titre"),ans.getString("auteur"));
				}
				else if(type == Document.HEBDOMADAIRE) {
					doc = new Hebdomadaire(ans.getInt("id"),ans.getString("titre"),ans.getString("auteur"));
				}
				else if(type == Document.CD) {
					doc = new Cd(ans.getInt("id"),ans.getString("titre"),ans.getString("auteur"));
				}
			}
			req.close();
			ans.close();
			return doc;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void insertDocument(int type, String nom, String auteur) {
		try {
			String sql = "insert into document (type,titre,auteur) values(?,?,?)";
			PreparedStatement req = CONNECT.prepareStatement(sql);
			req.setInt(1,type);
			req.setString(2,nom);
			req.setString(3,auteur);
			req.executeUpdate();
			req.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
