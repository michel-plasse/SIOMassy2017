package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Personne;
import model.Formateur;

public class FormateurDao implements FormateurHome {

    private Connection connection;

    public FormateurDao() {
    }
    
    @Override
    public void insert(Formateur formateur) throws SQLException {
        try {
            connection = ConnectionBd.getConnection();
            // Commencer une transaction
            connection.setAutoCommit(false);
            String sql = "INSERT INTO personne (nom, prenom, email, no_rue, rue, code_postal, ville, pays, mot_de_passe, no_tel, token)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, formateur.getNom());
            stmt.setString(2, formateur.getPrenom());
            stmt.setString(3, formateur.getEmail());
            stmt.setString(4, formateur.getNo_rue());
            stmt.setString(5, formateur.getRue());
            stmt.setString(6, formateur.getCode_postal());
            stmt.setString(7, formateur.getVille());
            stmt.setString(8, formateur.getPays());
            stmt.setString(9, formateur.getMot_de_passe());
            stmt.setString(10, formateur.getNo_tel());
            stmt.setString(11, formateur.getToken());
            stmt.executeUpdate();
            // Recuperer le id
            sql = "SELECT MAX(id_personne) AS id FROM personne";
            Statement lecture = connection.createStatement();
            ResultSet rs = lecture.executeQuery(sql);
            rs.next();
            formateur.setId(rs.getInt("id"));
            System.out.println(formateur);
            // Valider
            connection.commit();
            System.out.println("Formateur insérée.");
        } catch (SQLException exc) {
            connection.rollback();
            System.out.println("Rollback.");
            throw exc;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Personne de id donnée, ou null si pas trouvée.
     */
    @Override
    public Formateur findById(int id) throws SQLException {
        Formateur result = null;
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM formateur "
                + "INNER JOIN personne ON personne.id_personne = formateur.id_personne"
                + "WHERE id_personne =" + id + ";");
        if (rs.next()) {
            result = new Formateur(id,
                    rs.getString("nom"), 
                    rs.getString("prenom"),
                    rs.getString("email"), 
                    rs.getString("no_rue"), 
                    rs.getString("rue"), 
                    rs.getString("code_postal"),
                    rs.getString("ville"), 
                    rs.getString("pays"), 
                    rs.getString("mot_de_passe"),
                    rs.getString("no_tel"),
                    rs.getString("photo"));
        }
        return result;
    }

    @Override
    public ArrayList<Formateur> findAll() throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement canal = connection.createStatement();
        ArrayList<Formateur> lesFormateurs = new ArrayList();
        ResultSet resultat = canal.executeQuery("SELECT id_personne, nom, prenom, email, no_rue, rue, code_postal, ville, pays, mot_de_passe, no_tel, photo FROM formateur"
                + "INNER JOIN personne ON personne.id_personne = formateur.id_personne");
        while (!resultat.isLast()) {
            resultat.next();
            Formateur f = new Formateur(
                    resultat.getInt("id_personne"), 
                    resultat.getString("nom"), 
                    resultat.getString("prenom"), 
                    resultat.getString("email"), 
                    resultat.getString("no_rue"), 
                    resultat.getString("rue"), 
                    resultat.getString("code_postal"), 
                    resultat.getString("ville"), 
                    resultat.getString("pays"), 
                    resultat.getString("mot_de_passe"), 
                    resultat.getString("no_tel"),
                    resultat.getString("photo"));
            lesFormateurs.add(f);
        }
        return lesFormateurs;
    }

//    @Override
//    public boolean update(int ancien, Personne personne) throws SQLException {
//        connection = ConnectionBd.getConnection();
            // Commencer une transaction
//            String sql = "UPDATE personne SET nom = ?, prenom = ?, email = ?, no_rue = ?, rue = ?, code_postal = ?, ville = ?, pays = ?, mot_de_passe = ?, no_tel = ?, photo = ?)"
//                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?) WHERE id_personne = "+ ancien + ";";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(1, personne.getNom());
//            stmt.setString(2, personne.getPrenom());
//            stmt.setString(3, personne.getEmail());
//            stmt.setString(4, personne.getNo_rue());
//            stmt.setString(5, personne.getRue());
//            stmt.setString(6, personne.getCode_postal());
//            stmt.setString(7, personne.getVille());
//            stmt.setString(8, personne.getPays());
//            stmt.setString(9, personne.getMot_de_passe());
//            stmt.setString(10, personne.getNo_tel());
//            stmt.setString(11, personne.getPhoto());
//            stmt.executeUpdate();

//        return false;
//    }

    @Override
    public boolean delete(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM formateur WHERE id_personne =" + id + ";");
        return false;
    }

    //Méthode qui retourne la liste des inscrits à une date précise
    /**
     * Renvoie la personne de login et mot de passe donné, ou null si elle
     * n'existe pas.
     *
     * @param login
     * @param password
     * @return
     * @throws SQLException
     */
    public Personne getByLoginPassword(String login, String password) throws SQLException {
        Formateur result = null;
        connection = ConnectionBd.getConnection();
        String sql = "SELECT * FROM membre WHERE email=? AND mot_de_passe=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            result = new Formateur(
                    rs.getInt("id_personne"),
                    rs.getString("nom"), 
                    rs.getString("prenom"),
                    rs.getString("email"), 
                    rs.getString("no_rue"), 
                    rs.getString("rue"), 
                    rs.getString("code_postal"),
                    rs.getString("ville"), 
                    rs.getString("pays"), 
                    rs.getString("mot_de_passe"),
                    rs.getString("no_tel"),
                    rs.getString("photo"));
        }
        return result;
    }
    
//    public ArrayList<Personne> findByEtat() throws SQLException {
//        connection = ConnectionBd.getConnection();
//        Statement canal = connection.createStatement();
//        ArrayList<Personne> lesPersonnes = new ArrayList();
//        ResultSet resultat = canal.executeQuery("SELECT * FROM membre_promotion WHERE id_etat_candidature = 6");
//        while (!resultat.isLast()) {
//            resultat.next();
//            Personne p = new Personne(
//                    resultat.getString("nom"), 
//                    resultat.getString("prenom"));
            
//            p.setId(resultat.getInt("id_personne"));
            
//            lesPersonnes.add(p);
//        }
//        return lesPersonnes;
//    }
    
//    public ArrayList<Formateur> findBySession() throws SQLException {
//        connection = ConnectionBd.getConnection();
//        Statement canal = connection.createStatement();
//        ArrayList<Formateur> lesFormateur = new ArrayList();
//        ResultSet resultat = canal.executeQuery("SELECT p.nom, p.prenom FROM personne p"
//                + "INNER JOIN candidature c ON p.id_personne = c.id_personne"
//                + "INNER JOIN session_formation sf ON c.id_session = sf.id_session;");
//        while (!resultat.isLast()) {
//            resultat.next();
//            Personne p = new Personne(
//                resultat.getString("nom"),
//                resultat.getString("prenom"));
            
//            p.setId(resultat.getInt("id_personne"));
            
//            lesPersonnes.add(p);
//        }
//        return lesPersonnes;
//    }

    @Override
    public int findIdFromToken(String token) throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT id_personne FROM personne WHERE token='" + token + "';");
        if (res != null) {
            res.next();
            return res.getInt("id_personne");
        }
        return -1;
    }

    @Override
    public boolean activeUser(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        int res = stmt.executeUpdate("UPDATE personne SET estValide=1, token='0' WHERE id_personne=" + id + ";");
        if (res != 0) {
            return true;
        }
        return false;

    }

//    @Override
//    public ArrayList<Personne> findBySession(int id) throws SQLException {
//        connection = ConnectionBd.getConnection();
//        Statement canal = connection.createStatement();
//        ArrayList<Personne> lesPersonnes = new ArrayList();
//        ResultSet resultat = canal.executeQuery("SELECT personne.id_personne, nom, prenom, email, no_rue, rue, code_postal, ville, pays, mot_de_passe, no_tel, photo FROM personne "
//                + "INNER JOIN candidature ON personne.id_personne = candidature.id_personne "
//                + "INNER JOIN session_formation ON candidature.id_session = session_formation.id_session "
//                + "WHERE session_formation.id_session = " + id + " AND candidature.id_etat_candidature = 6;");
//        while (!resultat.isLast()) {
//            resultat.next();
//            Personne p = new Personne(
//                    resultat.getInt("id_personne"), 
//                    resultat.getString("nom"), 
//                    resultat.getString("prenom"), 
//                    resultat.getString("email"), 
//                    resultat.getString("no_rue"), 
//                    resultat.getString("rue"), 
//                    resultat.getString("code_postal"), 
//                    resultat.getString("ville"), 
//                    resultat.getString("pays"), 
//                    resultat.getString("mot_de_passe"), 
//                    resultat.getString("no_tel"),
//                    resultat.getString("photo"));
//            lesPersonnes.add(p);
//        }
//        return lesPersonnes;
//    }

    @Override
    public boolean update(int idAncien, Formateur nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}