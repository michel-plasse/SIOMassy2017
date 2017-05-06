/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Formation;

/**
 *
 * @author admin
 */
public class FormationDao  implements FormationHome<Formation>{

    @Override
    public void insert(Formation objetAInserer) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int idAncien, Formation nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Formation findById(int id) throws SQLException {
        Connection connection = ConnectionBd.getConnection();
        Statement statement = connection.createStatement();
        Formation formation = null;
        ResultSet resultat = statement.executeQuery("SELECT * FROM formation WHERE id_formation  =" + id);

        if(resultat.next()) {
            formation = new Formation(
                    resultat.getInt("id_formation"),
                    resultat.getString("nom"),
                    resultat.getString("description"));
        }
        return formation;    
    }

    @Override
    public ArrayList<Formation> findAll() throws SQLException {
        ArrayList<Formation> lesFormations = new ArrayList<>();
        Connection connection = ConnectionBd.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM formation");
        
        while (resultSet.next()) {            
            int id = resultSet.getInt("id_formation");
            String nom = resultSet.getString("nom");
            String description = resultSet.getString("description");
            Formation formation = new Formation(id, nom, description);
            lesFormations.add(formation);
        }        
        return lesFormations;
        
    }
    
}
