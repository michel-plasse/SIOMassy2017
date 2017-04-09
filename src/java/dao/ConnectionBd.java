package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBd {
	// private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://51.254.34.242:3306/agriotes2017_sandbox", "remoteninja", "aze123qsd+");
                        //connection = DriverManager.getConnection("jdbc:mysql://localhost/agriotes2017", "root", "");
			//System.out.println("Je suis connecté à la base de données.");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Je ne suis pas connecté à la base de données.");
		}
		return connection;
	}

	private ConnectionBd() {

	}
}
