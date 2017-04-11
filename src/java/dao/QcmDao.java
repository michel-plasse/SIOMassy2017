/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAOUtilitaire.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Choix;
import model.Qcm;
import model.Question;

public class QcmDao implements QcmHome<Qcm> {

    private Connection connection;

    @Override
    public ArrayList<Qcm> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Qcm nouveauQcm) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO (id_formateur,id_module,intitule,valide) VALUES (?,?,?,?)";
        ResultSet resQcm = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = initialisationRequetePreparee(connection, sql, true,
                    nouveauQcm.getFormateur().getId(),
                    nouveauQcm.getModule(),
                    nouveauQcm.getIntitule(),
                    nouveauQcm.isValide());

            preparedStatement.executeUpdate();

            resQcm = preparedStatement.getGeneratedKeys();

            if (resQcm.next()) {
                String sqlQuestion = "INSERT INTO question(id_qcm,question) VALUES(?,?)";
                ResultSet resQuest = null;
                PreparedStatement preparedStatementQuestion = connection.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
                String sqlChoix = "INSERT INTO choix (id_question, libelle, est_correcte) VALUES(?,?,?)";
                PreparedStatement preparedStatementChoix = connection.prepareStatement(sqlChoix);

                for (Question uneQuestion : nouveauQcm.getLesQuestions()) {
                    preparedStatementQuestion.setInt(1, resQcm.getInt("id_qcm"));
                    preparedStatementQuestion.setString(2, uneQuestion.getQuestion());
                    preparedStatementQuestion.executeUpdate();
                    resQuest = preparedStatementQuestion.getGeneratedKeys();

                    if (resQuest.next()) {
                        for (Choix unChoix : uneQuestion.getLesOptions()) {
                            preparedStatementChoix.setInt(1, resQuest.getInt("id_question"));
                            preparedStatementChoix.setString(2, unChoix.getChoix());
                            preparedStatementChoix.setBoolean(3, unChoix.isEstCorrect());
                            preparedStatementChoix.executeUpdate();
                        }

                    }
                }

            }

            connection.commit();
            System.out.println("Ajout du QCM : " + nouveauQcm.getIntitule() + " -> Ok...");

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème avec la BDD pour ajout QCM");
            throw e;
        } finally {
            fermeturesSilencieuses(resQcm, preparedStatement, connection);
        }

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
        String sql = "SELECT qc.id_qcm, qc.id_formateur, qc.id_module, qc.intitule, qc.valide, qu.id_question, qu.question, ch.id_option, ch.option, ch.est_correcte "
                + "FROM qcm as qc "
                + "INNER JOIN question as qu ON qc.id_qcm = qu.id_qcm "
                + "INNER JOIN choix as ch ON qu.id_question = op.id_question "
                + "WHERE id_qcm = ?";

        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        ArrayList<Question> questions = new ArrayList<>();

        try {
            preparedStatement = initialisationRequetePreparee(connection, sql, false, id);
            int idQuestion = -1;
            Question uneQuestion = null;
            res = preparedStatement.executeQuery();
            while (res.next()) {
                if (res.getInt("qu.id_question") != idQuestion) {
                    uneQuestion = new Question();
                    uneQuestion.setIdQuestion(res.getInt("qu.id_question"));
                    uneQuestion.setQuestion(res.getString("qu.question"));
                    uneQuestion.setLesOptions(new ArrayList<>());
                    idQuestion = res.getInt("qu.id_question");
                }
                
                Choix uneOption = new Choix();
                uneOption.setIdOption(res.getInt("op.id_option"));
                uneOption.setChoix(res.getString("op.option"));
                uneOption.setEstCorrecte(res.getBoolean("op.est_correct"));
            }

        } catch (SQLException e) {

        }
    }

}
