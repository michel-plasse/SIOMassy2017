/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.qcm;

import dao.ConnectionBd;
import static dao.DAOUtilitaire.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Choix;
import model.Question;

/**
 *
 * @author admin
 */
public class QuestionDao implements QuestionHome<Question> {

    private Connection connection;
    private static String SQL_INSERT_CHOIX = "INSERT INTO choix (id_question, libelle, est_correct) VALUES(?,?,?)";
    private static String SQL_INSERT_QUESTION = "INSERT INTO question(id_qcm,question) VALUES(?,?)";
    private static String SQL_DELETE_QUESTION = "DELETE FROM question WHERE id_question = ?";
    private static String SQL_DELETE_CHOIX = "DELETE FROM choix WHERE id_question = ?";

    @Override
    public void insert(int id, Question nouvelleQuestion) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        ResultSet rsIdGenerated = null;

        try {
            preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_QUESTION, true,
                    id,
                    nouvelleQuestion.getQuestion());

            preparedStatement.executeUpdate();

            rsIdGenerated = preparedStatement.getGeneratedKeys();

            if (rsIdGenerated.next()) {
                int idQuestion = rsIdGenerated.getInt(1);
                HashMap<Integer, Choix> lesChoix = nouvelleQuestion.getLesChoix();
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_CHOIX);
                for (Map.Entry<Integer, Choix> uneOption : lesChoix.entrySet()) {
                    stmt.setInt(1, idQuestion);
                    stmt.setString(2, uneOption.getValue().getLibelle());
                    stmt.setBoolean(3, uneOption.getValue().isEstCorrect());
                    stmt.executeUpdate();

                }
                connection.commit();
                System.out.println("Nouvelle question " + idQuestion + " insérée en BDD..");
            } else {
                throw new SQLException("Id de la question pas généré");
            }

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        } finally {
            connection.setAutoCommit(true);
            fermeturesSilencieuses(rsIdGenerated, preparedStatement, connection);

        }

    }

    @Override
    public void insert(Question objetAInserer) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        boolean delete = false;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE_CHOIX, false, id);
            preparedStatement.executeUpdate();
            preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE_QUESTION, false, id);
            preparedStatement.executeUpdate();
            delete = true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
            fermeturesSilencieuses(preparedStatement, connection);
        }
        return delete;
    }

    @Override
    public boolean update(int idAncien, Question nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Question> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Question> findAll(int idQcm) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sql = "SELECT id_question, id_qcm, question FROM question WHERE id_qcm = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resQuestions = null;
        ArrayList<Question> lesQuestions = null;

        try {
            preparedStatement = initialisationRequetePreparee(connection, sql, false, idQcm);

            resQuestions = preparedStatement.executeQuery();

            lesQuestions = new ArrayList<>();

            while (resQuestions.next()) {
                ChoixDao optionDao = new ChoixDao();
                Question uneQuestion = new Question();
                uneQuestion.setIdQuestion(resQuestions.getInt("id_question"));
                uneQuestion.setQuestion(resQuestions.getString("question"));
                uneQuestion.setLesChoix(optionDao.findByIdQuestion(resQuestions.getInt("id_question")));
                lesQuestions.add(uneQuestion);
            }

            System.out.println("Récupération des Questions du QCM : OK..");

        } catch (SQLException e) {
            System.out.println("Problème avec la récupération des questions du QCM : " + idQcm);
            throw e;
        } finally {
            fermeturesSilencieuses(preparedStatement, connection);

        }

        return lesQuestions;
    }

}
