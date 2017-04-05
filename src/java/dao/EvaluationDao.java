/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Evaluation;
import model.Module;

/**
 *
 * @author admin
 */
public class EvaluationDao implements EvaluationHome{
    
    private Connection connection ; 

    @Override
    public void insert(Evaluation objetAInserer) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sqlstmt = "INSERT INTO evaluation ('id_module','id_formateur,'id_session','intitule','date_effet')"
                + "VALUES(?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sqlstmt);
        stmt.setInt(1, objetAInserer.getLeModule().getId());
        stmt.setInt(2, objetAInserer.getLeFormateur().getId());
        stmt.setInt(3, objetAInserer.getLaSession().getId_session());
        stmt.setString(4, objetAInserer.getIntitule());
        stmt.setDate(5, (Date) objetAInserer.getDateDebutEval());
        stmt.executeUpdate();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM evaluation"
                + " WHERE id_evaluation = "+id+";");
        return false;
    }

    @Override
    public boolean update(int idAncien, Evaluation nouveau) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sql = "UPDATE evaluation SET id_module = ?,id_formateur =?, id_session = ?, intitule = ?, date_effet = ?"
                + "VALUES (?,?,?,?,?) WHERE id_evaluation = " + idAncien ;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, nouveau.getLeModule().getId());
        stmt.setInt(2, nouveau.getLeFormateur().getId());
        stmt.setInt(3, nouveau.getLaSession().getId_session());
        stmt.setString(4, nouveau.getIntitule());
        stmt.setDate(5, (Date) nouveau.getDateDebutEval());
        stmt.executeUpdate();
        return true;
    }

    @Override
    public Evaluation findById(int id) throws SQLException {
        
    }

    @Override
    public ArrayList<Evaluation> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Evaluation> findAllEvalFormateur(int idFormateur) throws SQLException {
        ArrayList<Evaluation> lesEvalDuFormateur = new ArrayList();
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet res =  stmt.executeQuery("SELECT evaluation.id_evaluation, module.nom, formation.nom, evaluation.date_effet, evaluation.intitule FROM evaluation"
                + "INNER JOIN module ON evaluation.id_module = module.id_module"
                + "INNER JOIN session_formation ON evaluation.id_session = session_formation.id_session"
                + "INNER JOIN formation ON session_formation.id_formation = formation.id_formation"
		+ "WHERE id_formateur = "+idFormateur+" AND session_formation.est_ouverte = 0 ;");
        while (!res.isLast()) {
            res.next();
            Evaluation eval = new Evaluation(
                    res.getInt("evaluation.id-evaluation"),
                    res.getDate("evaluation.date_effet"),
                    res.getString("evaluation.intitule"),
                    res.getString("module.nom"),
                    res.getString("formation.nom"));
            lesEvalDuFormateur.add(eval);
        }
        return lesEvalDuFormateur;
    }
}
