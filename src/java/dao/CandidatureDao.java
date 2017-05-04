package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import model.Candidature;

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
    public boolean update(int idAncien, Candidature nouveau) throws SQLException {
        // TODO Auto-generated method stub
        return false;
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
                + " c.id_etat_candidature AS etat_candidature,"
                + " p.nom AS nom,"
                + " p.prenom AS prenom,"
                + " e.libelle AS statut,"
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
                + " ORDER BY " + order_by + " "
                + limit
        );
        while (resultSet.next()) {
            String personne = resultSet.getString("personne");
            String session = resultSet.getString("session");
            String etatCandidature = resultSet.getString("etat_candidature");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String statut = resultSet.getString("statut");
            String formationNom = resultSet.getString("formation_nom");            
            String effectue = resultSet.getString("effectue");

            HashMap<String, String> candidature = new HashMap<String, String>();
            candidature.put("idPersonne", personne);
            candidature.put("idSession", session);
            candidature.put("idEtatCandidature", etatCandidature);
            candidature.put("nom", nom);
            candidature.put("prenom", prenom);
            candidature.put("statut", statut);
            candidature.put("formationNom", formationNom);            
            candidature.put("effectue", effectue);
            listeMapCandidature.add(candidature);

        }
        
        return listeMapCandidature;
    }

}
