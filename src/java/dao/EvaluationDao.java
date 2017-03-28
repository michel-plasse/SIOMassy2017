/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Evaluation;

/**
 *
 * @author admin
 */
public class EvaluationDao implements EvaluationHome{
    
    private Connection connection ; 

    @Override
    public void insert(Evaluation objetAInserer) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sqlstmt = "INSERT INTO evaluation ('id_session','date_evaluation','id_formateur','intitule') VALUES(?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sqlstmt);
        stmt.setInt(1, objetAInserer.getId_session());
        stmt.setDate(2, (Date) objetAInserer.getDateDebutEval());
        stmt.setInt(3, objetAInserer.getIdFormateur());
        stmt.setString(4, objetAInserer.getIntitulé());
        stmt.executeUpdate();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM evaluation WHERE id_evaluation = "+id+";");
        return false;
    }

    @Override
    public boolean update(int idAncien, Evaluation nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Evaluation findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Evaluation> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Evaluation> findAllEleve(int idEleve,int scope) throws SQLException {
        ArrayList<Evaluation> lesEvalDeEleve = new ArrayList<Evaluation>();
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("SELECT * FROM evaluation WHERE id_evaluation = :");
        return lesEvalDeEleve;
    }

    @Override
    public ArrayList<Evaluation> findAllFormateur(int idFormateur,int scope) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
