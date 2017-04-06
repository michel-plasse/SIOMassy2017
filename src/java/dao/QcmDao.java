/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        stmt.setInt(2, objetAInserer.getFormateur().getId());
        stmt.setInt(3, objetAInserer.getModule().getId());
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
        connection = ConnectionBd.getConnection();
        Qcm qcm = null;
        PersonneDao personneDao = new PersonneDao();
        ModuleDao moduleDao = new ModuleDao();
        String sqlstmt = "SELECT id_qcm, id_formateur, id_module , intitule FROM qcm "
                 + "WHERE id_qcm = ? ";
        PreparedStatement stmt = connection.prepareStatement(sqlstmt);
        stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery(sqlstmt);
        if (res.next()){
            qcm = new Qcm(res.getInt("id_qcm"),
                    personneDao.findById(res.getInt("id_formateur")),
                    moduleDao.findById(res.getInt("id_module")),
                    res.getString("intitule"));
        }
        return qcm;
    }

    @Override
    public ArrayList<Qcm> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
