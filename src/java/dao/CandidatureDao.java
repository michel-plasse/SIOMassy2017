package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.Candidature;

public class CandidatureDao implements CandidatureHome<Candidature> {

    @Override
    public int insert(Candidature objetAInserer) throws SQLException {
        // TODO Auto-generated method stub
        Connection connection = ConnectionBd.getConnection();
        return 0;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(int idAncien, Candidature nouveau) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Candidature findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Candidature> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ArrayList<HashMap<String, String>> mapCandidatures()throws SQLException{
            ArrayList<HashMap<String, String>> listeMapCandidature = new ArrayList<HashMap<String, String>>();
            try {
                    Connection connection = ConnectionBd.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(
                            "SELECT"
                                            + " p.nom AS nom,"
                                        + " p.prenom AS prenom,"
                                        + " e.libelle AS statut,"
                                        + " s.date_debut AS debut,"
                                        + " s.date_fin AS fin,"
                                        + " f.nom AS formation_nom,"
                                        + " c.date_effectue AS effectue"
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
                            + " ORDER BY date_effectue DESC"
                            );
                    while(resultSet.next()){
                            String nom = resultSet.getString("nom");
                            String prenom = resultSet.getString("prenom");
                            String statut = resultSet.getString("statut");
                            String debut  = resultSet.getString("debut");
                            String fin  = resultSet.getString("fin");
                            String formationNom  = resultSet.getString("formation_nom");
                            String effectue  = resultSet.getString("effectue");

                            HashMap<String, String>	candidature = new HashMap<String, String>();
                            candidature.put("nom", nom);
                            candidature.put("prenom", prenom);
                            candidature.put("statut", statut);
                            candidature.put("debut", nom);
                            candidature.put("fin", nom);
                            candidature.put("formationNom", formationNom);
                            candidature.put("effectue", effectue);
                            listeMapCandidature.add(candidature);

                    }
            } catch (SQLException e) {

                    e.printStackTrace();
            }
            return listeMapCandidature;
    }

}
