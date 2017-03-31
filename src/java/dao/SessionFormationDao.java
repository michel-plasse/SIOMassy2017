package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.SessionFormation;

public class SessionFormationDao implements SessionHome {
    private Connection connection;

    @Override
    public void insert(SessionFormation objetAInserer) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public SessionFormation findById(int id) throws SQLException {
        
        connection = ConnectionBd.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultat = statement.executeQuery("SELECT id_session,nom,description,date_debut,date_fin,est_ouverte FROM formation INNER JOIN session_formation "
        + "ON session_formation.id_formation = formation.id_formation WHERE id_session="+id+";");

        if(resultat != null) {
            resultat.next();
            SessionFormation a = new SessionFormation(resultat.getInt("id_session"), resultat.getString("nom"), resultat.getString("description"), resultat.getDate("date_debut"),
                        resultat.getDate("date_fin"), resultat.getBoolean("est_ouverte"));

            return a;
        }
        return null;
    }

    @Override
    public ArrayList<SessionFormation> getSessionsOuvertes() throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement canal = connection.createStatement();
        ArrayList<SessionFormation> sessionOuvertes = new ArrayList<SessionFormation>();
        ResultSet resultat = canal.executeQuery("SELECT id_session,nom,description,date_debut,date_fin,est_ouverte FROM formation INNER JOIN session_formation "
        + "ON session_formation.id_formation = formation.id_formation WHERE est_ouverte = 1 ORDER BY date_debut;");
        
        while (!resultat.isLast()) {
            resultat.next();
            SessionFormation a = new SessionFormation(resultat.getInt("id_session"), resultat.getString("nom"), resultat.getString("description"), resultat.getDate("date_debut"),
                    resultat.getDate("date_fin"), resultat.getBoolean("est_ouverte"));
            sessionOuvertes.add(a);
        }
        return sessionOuvertes;
    }

    @Override
    public boolean update(int ancien, SessionFormation nouveau) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<SessionFormation> findAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean isExistAndOpen(int id) throws SQLException {

        connection = ConnectionBd.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT est_ouverte FROM session_formation WHERE id_session = ?" );
        preparedStatement.setInt(1, id);
        
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        return result.getBoolean("est_ouverte");
    }

}
