/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAOUtilitaire.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Evaluation;
import model.Module;
import model.Note;
import model.Personne;

/**
 *
 * @author admin
 */
public class EvaluationDao implements EvaluationHome {

    private Connection connection;

    @Override
    public void insert(Evaluation objetAInserer) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sqlstmt = "INSERT INTO evaluation (id_module, id_formateur, id_session, intitule, date_effet) "
                + " VALUES(?,?,?,?,?)";
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
                + " WHERE id_evaluation = " + id + ";");
        return false;
    }

    @Override
    public boolean update(int idAncien, Evaluation nouveau) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sql = "UPDATE evaluation SET id_module = ?,id_formateur =?, id_session = ?, intitule = ?, date_effet = ?"
                + "VALUES (?,?,?,?,?) WHERE id_evaluation = " + idAncien;
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
        connection = ConnectionBd.getConnection();
        Evaluation evaluation = null;
        ModuleDao moduleDao = new ModuleDao();
        PersonneDao personneDao = new PersonneDao();
        SessionFormationDao sessionDao = new SessionFormationDao();
        String sql = "SELECT id_evaluation, id_module, id_formateur, id_session, intitule, date_effet FROM evaluation WHERE id_evaluation = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            evaluation = new Evaluation(
                    res.getInt("id_evaluation"),
                    moduleDao.findById(res.getInt("id_module")),
                    personneDao.findById(res.getInt("id_formateur")),
                    sessionDao.findById(res.getInt("id_session")),
                    res.getDate("date_effet"),
                    res.getString("intitule"));
        }
        return evaluation;
    }

    @Override
    public ArrayList<Evaluation> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Evaluation> findAllEvalFormateur(int idFormateur) throws SQLException {
        ArrayList<Evaluation> lesEvalDuFormateur = new ArrayList();
        ModuleDao moduleDao = new ModuleDao();
        PersonneDao personneDao = new PersonneDao();
        SessionFormationDao sessionDao = new SessionFormationDao();
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT id_module, id_formateur, id_session, date_effet, intitule FROM evaluation"
                + " WHERE id_formateur = " + idFormateur + " AND date_effet >= NOW() ;");
        while (res.next()) {
            Evaluation eval = new Evaluation(
                    moduleDao.findById(res.getInt("id_module")),
                    personneDao.findById(res.getInt("id_formateur")),
                    sessionDao.findById(res.getInt("id_session")),
                    res.getDate("date_effet"),
                    res.getString("intitule"));
            lesEvalDuFormateur.add(eval);
        }
        return lesEvalDuFormateur;
    }

    @Override
    public ArrayList<Note> findByEval(int idEvaluation) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sql = "SELECT n.id_evaluation, n.id_note, n.note, n.commentaire, p.id_personne, p.nom, p.prenom, e.intitule  FROM evaluation AS e"
                + " INNER JOIN note n ON e.id_evaluation = n.id_evaluation"
                + " INNER JOIN personne p ON n.id_personne = p.id_personne"
                + " WHERE n.id_evaluation = ?;";
        PreparedStatement PreparedStatement = connection.prepareStatement(sql);
        ArrayList<Note> lesNotes = new ArrayList();
        PreparedStatement.setInt(1, idEvaluation);
        ResultSet resultat = PreparedStatement.executeQuery();
        while (resultat.next()) {
            
            Personne p = new Personne();
            p.setId(resultat.getInt("p.id_personne"));
            p.setNom(resultat.getString("p.nom"));
            p.setPrenom(resultat.getString("p.prenom"));

            Evaluation e = new Evaluation();
            e.setIdEvaluation(resultat.getInt("n.id_evaluation"));
            e.setIntitule(resultat.getString("e.intitule"));
            
            Note n = new Note(
                    resultat.getInt("n.id_note"),
                    resultat.getDouble("n.note"),
                    resultat.getString("n.commentaire"));
            n.setEtudiant(p);
            n.setEvaluation(e);
            lesNotes.add(n);
        }
        return lesNotes;
    }

}
