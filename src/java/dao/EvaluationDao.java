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
        stmt.setInt(1, objetAInserer.getIdModule());
        stmt.setInt(2, objetAInserer.getIdFormateur());
        stmt.setInt(3, objetAInserer.getIdSession());
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
        stmt.setInt(1, nouveau.getIdModule());
        stmt.setInt(1, nouveau.getIdFormateur());
        stmt.setInt(1, nouveau.getIdSession());
        stmt.setString(1, nouveau.getIntitule());
        stmt.setDate(1, (Date) nouveau.getDateDebutEval());
        stmt.executeUpdate();
        return true;
    }

    @Override
    public Evaluation findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Evaluation> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Evaluation> findByEval() throws SQLException {
        ArrayList<Evaluation> lesEleves = new ArrayList<Evaluation>();
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT id_evaluation FROM evaluation");
        
        return lesEleves;
    }

    @Override
    public ArrayList<Evaluation> findAllEvalEleve(int idEleve) throws SQLException {
        ArrayList<Evaluation> lesEvalDeEleve = new ArrayList();
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet res =  stmt.executeQuery("SELECT module.nom,personne.nom ,evaluation.date_effet, evaluation.intitule, note.note, note.commentaire FROM evaluation "
                + "+ INNER JOIN candidature ON evaluation.id_session = candidature.id_session"
                + "INNER JOIN module ON evaluation.id_module = module.id_module"
                + "INNER JOIN personne ON evaluation.id_formateur = personne.id_personne"
                + "INNER JOIN note ON evaluation.id_evaluation = note.id_evaluation"
		+ "WHERE candidature.id_personne = "+idEleve+" AND candidature.id_etat_candidature = 3 ;");
        while (!res.isLast()) {
            res.next();
            Evaluation eval = new Evaluation(
                    res.getDate("evaluation.date_effet"),
                    res.getString("evaluation.intitule"),
                    res.getString("module.nom"),
                    res.getString("personne.nom"),
                    res.getFloat("note.note"),
                    res.getString("note.commentaire"));
            lesEvalDeEleve.add(eval);
        }
        return lesEvalDeEleve;
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
