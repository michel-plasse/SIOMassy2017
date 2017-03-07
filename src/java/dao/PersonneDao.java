package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.Personne;

public class PersonneDao implements PersonneHome {
	private Connection connection;
	HashMap<Integer, Personne> liste;

	public PersonneDao() {
		/*
		 * liste = new HashMap<Integer, Personne>(); Personne p1 = new
		 * Personne(1, "leto@lechat.meow"); liste.put(1, p1);
		 */
	}

	@Override
	public void insert(Personne personne) throws SQLException {
		try {
			connection = ConnectionBd.getConnection();
			// Commencer une transaction
			connection.setAutoCommit(false);
			String sql = "INSERT INTO personne (nom, prenom, email, no_rue, rue, code_postal, ville, pays, mot_de_passe)"
					+ " VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, personne.getNom());
			stmt.setString(2, personne.getPrenom());
			stmt.setString(3, personne.getEmail());
			stmt.setString(4, personne.getNo_rue());
			stmt.setString(5, personne.getRue());
			stmt.setString(6, personne.getCode_postal());
			stmt.setString(7, personne.getVille());
			stmt.setString(8, personne.getPays());
			stmt.setString(9, personne.getMot_de_passe());
			stmt.executeUpdate();
			// Recuperer le id
			sql = "SELECT MAX(id_personne) AS id FROM personne";
			Statement lecture = connection.createStatement();
			ResultSet rs = lecture.executeQuery(sql);
			rs.next();
			personne.setId(rs.getInt("id"));
			System.out.println(personne);
			// Valider
			connection.commit();
			System.out.println("Personne insérée.");
		} catch (SQLException exc) {
			connection.rollback();
			System.out.println("Rollback.");
			throw exc;
		}
		finally {
			connection.setAutoCommit(true);
		}
	}

	/**
	 * Personne de id donné, ou null si pas trouvé.
	 */
	@Override
	public Personne findById(int id) throws SQLException {
		Personne result = null;
		connection = ConnectionBd.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM agriotes2017.personne WHERE id_personne =" + id + ";");
		if (rs.next()) {
			result = new Personne(id, 
					rs.getString("nom"), rs.getString("prenom"),
					rs.getString("email"), rs.getString("no_rue"), rs.getString("rue"), rs.getString("code_postal"),
					rs.getString("ville"), rs.getString("pays"), rs.getString("mot_de_passe"));
		}
		return result;
	}

	@Override
	public ArrayList<Personne> findAll() throws SQLException {
		Statement stmt;
		stmt = connection.createStatement();
		ResultSet resall = stmt.executeQuery("SELECT* FROM agriotes2017.personne");
		resall.next();
		return null;
	}

	@Override
	public boolean update(int ancien, Personne nouveau) throws SQLException {
		Statement stmt;
		String nom = nouveau.getNom();
		String prenom = nouveau.getPrenom();
		String email = nouveau.getEmail();
		String password = nouveau.getMot_de_passe();
		// int no_phone = nouveaur.getNo_phone();
		int no_rue = Integer.parseInt(nouveau.getNo_rue());
		String nom_rue = nouveau.getRue();
		int code_postal = Integer.parseInt(nouveau.getCode_postal());
		String ville = nouveau.getVille();
		String pays = nouveau.getPays();

		stmt = connection.createStatement();
		stmt.executeUpdate("UPDATE personne SET(" + nom + ",'" + prenom + ",'" + email + ",'" + password + ",'" + no_rue
				+ ",'" + nom_rue + ",'" + code_postal + ",'" + ville + ",'" + pays + ";");
		return false;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		Statement stmt;
		stmt = connection.createStatement();
		stmt.executeUpdate("DELETE FROM personne WHERE id_personne =" + id + ";");
		return false;
	}
	
	//Méthode qui retourne la liste des inscrits à une date précise

}

/*
 * package dao;
 * 
 * import java.sql.SQLException; import java.sql.Statement; import
 * java.util.ArrayList; import java.util.HashMap;
 * 
 * import model.Personne;
 * 
 * public class PersonneDao implements PersonneHome { HashMap<Integer, Personne>
 * liste;
 * 
 * 
 * public PersonneDao() { liste = new HashMap<Integer, Personne>(); Personne p1
 * = new Personne(1, "leto@lechat.meow"); liste.put(1, p1); }
 * 
 * @Override public int insert(Personne objetAInserer) throws SQLException {
 * ConnectionBd connection = new ConnectionBd();
 * 
 * String nom = objetAInserer.getNom(); String prenom =
 * objetAInserer.getPrenom(); String email = objetAInserer.getEmail(); String
 * password = objetAInserer.getMot_de_passe(); //int no_phone =
 * objetAInserer.getNo_phone(); String no_rue = objetAInserer.getNo_rue();
 * String nom_rue = objetAInserer.getRue(); String code_postal =
 * objetAInserer.getCode_postal(); String ville = objetAInserer.getVille();
 * String pays = objetAInserer.getPays();
 * 
 * Statement stmt = ConnectionBd.connection.createStatement();
 * stmt.executeUpdate(
 * "INSERT INTO personne (nom, prenom, email, no_rue, rue, code_postal, ville, pays, mot_de_passe) VALUES ("
 * +nom+","+prenom+","+email+","+no_rue+","+nom_rue+","+code_postal+","+ville+
 * ","+pays+","+password+");"); return 0; }
 * 
 * 
 * @Override public boolean delete(int id) { // TODO Auto-generated method stub
 * return false; }
 * 
 * 
 * @Override //Renvoie la personne de id = id ou null si pas trouvée. public
 * Personne findById(int id) { return liste.get(id); }
 * 
 * 
 * @Override public ArrayList<Personne> findAll() { // TODO Auto-generated
 * method stub return null; }
 * 
 * 
 * @Override public boolean update(int ancien, Personne nouveau) { // TODO
 * Auto-generated method stub return false; }
 * 
 * 
 * }
 */