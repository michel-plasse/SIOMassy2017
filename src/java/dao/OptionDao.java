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
import model.Option;
import static dao.DAOUtilitaire.*;

public class OptionDao implements OptionHome{
    
    private Connection connection ;
    private static String CHAMP_ID_OPTION = "id_option";
    private static String CHAMP_ID_QUESTION = "id_question";
    private static String CHAMP_OPTION = "option";
    private static String CHAMP_EST_CORRECTE = "est_correcte";
            
    private static String SQL_INSERT_OPTION = "INSERT INTO question ('id_option','id_question','option','est_correcte') VALUES(?,?,?,?)";
    private static String SQL_SELECT_OPTION_BY_QUESTION = "SELECT id_option,id_question,option,est_correcte FROM option WHERE id_question=?";

    @Override
    public void insert(Option objetAInserer) throws SQLException {
        
        connection.setAutoCommit(false);
        connection = ConnectionBd.getConnection();
        PreparedStatement stmt = null;
        try {
            initialisationRequetePreparee(connection, SQL_INSERT_OPTION, false,
                    objetAInserer.getIdOption(),
                    objetAInserer.getQuestion().getIdQuestion(),
                    objetAInserer.getOption(),
                    objetAInserer.isEstCorrecte());
            stmt.executeUpdate();
        } catch (Exception e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        }
        
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int idAncien, Option nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Option findById(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        Option option = null;
        QuestionDao questionDao = new QuestionDao();
         String sqlstmt = "SELECT id_option, id_question, option, est_correcte FROM option"
                + "WHERE id_option = ? ";
        PreparedStatement stmt = connection.prepareStatement(sqlstmt);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        ResultSet res = stmt.executeQuery(sqlstmt);
        if (res.next()){
            option = new Option(
                    questionDao.findById(res.getInt("id_question")),
                    res.getString("option"),
                    res.getBoolean("est_correcte"));
        }
        return option;
    }

    @Override
    public ArrayList<Option> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Option> findByIdQuestion(int idQuestion) throws SQLException {
        connection = ConnectionBd.getConnection();
        PreparedStatement stmt = null;
        ResultSet res = null;
        ArrayList<Option> lesReponses = new ArrayList<>();
        QuestionDao questionDao = new QuestionDao();
        
        try {
            
            stmt = initialisationRequetePreparee(connection, SQL_SELECT_OPTION_BY_QUESTION, false, idQuestion);
            res = stmt.executeQuery();
            while(res.next()){
               Option option = new Option(questionDao.findById(idQuestion), res.getString(CHAMP_OPTION), res.getBoolean(CHAMP_EST_CORRECTE));
               option.setIdOption(res.getInt(CHAMP_ID_OPTION));
               lesReponses.add(option);
            }
            
        } catch (SQLException e) {
            System.out.println("probleme récupération des reponses pour la question...");
            throw e;
        } finally{
            fermeturesSilencieuses(res, stmt, connection);
        }
        return lesReponses;
    }
    
}
