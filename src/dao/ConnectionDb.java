package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDb {

	static Connection connection = null;
	

	public void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/agriote2017","root","");
			connection = c;
			
			
//			Statement canal = c.createStatement();
//			ResultSet rslt = canal.executeQuery("select * from infos");
//			rslt.next();
//			System.out.println(rslt.getString("nom"));
//			System.out.println(rslt.getDouble("solde"));
//			
//			while(!rslt.isLast()) {
//				rslt.next();
//				System.out.println(rslt.getString("nom")+ " : " + rslt.getString("prenom"));
//			}
			
			System.out.println("connexion ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Erreur connexion");
		}
	}
	
}
