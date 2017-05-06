package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import model.Candidature;
import model.EtatCandidature;
import model.Personne;
import model.SessionFormation;

public class CandidatureDao implements CandidatureHome<Candidature> {

    private Connection connection;
    
    @Override
    public void insert(Candidature objetAInserer) throws SQLException {
       
        try {
            Connection connection = ConnectionBd.getConnection();
                        
            String sql = "INSERT INTO candidature(id_personne, id_session, id_etat_candidature)"
                    + "VALUES (?. ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, objetAInserer.getPersonne().getId());
            stmt.setInt(2, objetAInserer.getSessionFomation().getId_session());
            stmt.setInt(3, objetAInserer.getEtatCandidature().getIdEtatCandidature());
            stmt.executeUpdate();
                                
        }
        catch (SQLException exc) {
            connection.rollback();
            System.out.println("Rollback.");
            throw exc;
        }
               
    }

    @Override
    public boolean delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(int idAncien, Candidature candidature) throws SQLException {
        Personne personne = candidature.getPersonne();
        SessionFormation sessionFormation = candidature.getSessionFomation();
        EtatCandidature etatCandidature = candidature.getEtatCandidature();
        
        connection = ConnectionBd.getConnection();
            // Commencer une transaction
            String sql = "UPDATE candidature SET id_etat_candidature = ?"
                    + " WHERE id_personne = "+ personne.getId() + " AND id_session =" + sessionFormation.getId_session();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, etatCandidature.getIdEtatCandidature());
            stmt.executeUpdate();

        return true;
    }

    @Override
    public Candidature findById(int id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Candidature> findAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }    

    @Override
    public ArrayList<HashMap<String, String>> mapCandidatures(ArrayList<String> conditions, String order_by, String limit) throws SQLException {
        String where = String.join(" AND ", conditions);
        if (!where.equals("")){
            where = " WHERE " + where;
        }
        
        ArrayList<HashMap<String, String>> listeMapCandidature = new ArrayList<HashMap<String, String>>();
        Connection connection = ConnectionBd.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT"                        
                + " c.id_personne AS personne,"
                + " c.id_session AS session,"
                + " f.id_formation AS formation,"
                + " c.id_etat_candidature AS etat_candidature,"
                + " p.nom AS nom,"
                + " p.prenom AS prenom,"
                + " e.libelle AS etat,"
                + " f.nom AS formation_nom,"
                + " DATE_FORMAT(c.date_effet, '%d/%m/%Y') AS effectue"
                + " FROM"
                + " candidature c"
                + " INNER JOIN"
                + " etat_candidature e ON c.id_etat_candidature = e.id_etat_candidature"
                + " INNER JOIN"
                + " personne p ON c.id_personne = p.id_personne"
                + " INNER JOIN"
                + " session_formation s ON c.id_session = s.id_session"
                + " INNER JOIN"
                + " formation f ON s.id_formation = f.id_formation"
                + where
                + order_by + " "
                + limit
        );
        while (resultSet.next()) {
            String personne = resultSet.getString("personne");
            String session = resultSet.getString("session");
            String formation = resultSet.getString("formation");
            String etatCandidature = resultSet.getString("etat_candidature");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String etat = resultSet.getString("etat");
            String formationNom = resultSet.getString("formation_nom");            
            String effectue = resultSet.getString("effectue");

            HashMap<String, String> candidature = new HashMap<String, String>();
            candidature.put("idPersonne", personne);
            candidature.put("idSession", session);
            candidature.put("idFormation", formation);
            candidature.put("idEtatCandidature", etatCandidature);
            candidature.put("nom", nom);
            candidature.put("prenom", prenom);
            candidature.put("etat", etat);
            candidature.put("formationNom", formationNom);            
            candidature.put("effectue", effectue);
            listeMapCandidature.add(candidature);

        }
        
        return listeMapCandidature;
    }

}
