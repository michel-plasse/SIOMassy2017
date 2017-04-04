/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Qcm;

public class QcmDao implements QcmHome{
    
    private Connection connection ; 

    @Override
    public void insert(Qcm objetAInserer) throws SQLException {
        connection = ConnectionBd.getConnection();
         String sqlstmt = "INSERT INTO qcm ('id_qcm','id_formateur,'id_module','intitule')"
                + "VALUES(?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sqlstmt);
        stmt.setInt(1, objetAInserer.getIdQcm());
        stmt.setInt(2, objetAInserer.getIdFormateur());
        stmt.setInt(3, objetAInserer.getIdModule());
        stmt.setString(4, objetAInserer.getIntitule());
        stmt.executeUpdate();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int idAncien, Qcm nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Qcm findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Qcm> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
