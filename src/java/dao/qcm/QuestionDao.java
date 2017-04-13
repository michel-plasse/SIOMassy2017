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
import java.util.ArrayList;
import model.Choix;
import model.Question;

/**
 *
 * @author admin
 */
public class QuestionDao implements QuestionHome<Question>{
    
    private Connection connection ;

    @Override
    public void insert(int id, Question nouvelleQuestion) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO question(id_qcm,question) VALUES(?,?)";
        PreparedStatement preparedStatement = null;
        ResultSet idGenerated = null;
        
        try {
            preparedStatement = initialisationRequetePreparee(connection, sql, true,
                                                                            id,
                                                                            nouvelleQuestion.getQuestion());
        
            preparedStatement.executeUpdate();
            
            idGenerated = preparedStatement.getGeneratedKeys();
            
            if(idGenerated.next()) {
                ChoixDao optionDao = new ChoixDao();
                for(Choix uneOption : nouvelleQuestion.getLesChoix()) {
                    uneOption.setQuestion(new Question(idGenerated.getInt(1)));
                    optionDao.insert(uneOption);
                }
            }

            connection.commit();
            System.out.println("Nouvelle question insérée en BDD..");
            
        }catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        }finally{
            connection.setAutoCommit(true);
            fermeturesSilencieuses(idGenerated,preparedStatement, connection);
            
        }
        
        
    }
    
    

    @Override
    public void insert(Question objetAInserer) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            preparedStatement = initialisationRequetePreparee(connection, sql, false,idQcm);
        
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
            
        }catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème avec la récupération des questions du QCM : " +idQcm);
            throw e;
        }finally{
            fermeturesSilencieuses(preparedStatement, connection);
            
        }
        
        return lesQuestions;
    }
    

}