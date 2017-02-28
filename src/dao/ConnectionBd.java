package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBd {

	private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/agriote2017", "root", "");
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		}
		System.out.println("connexion ok");
		return connection;
	}

}
