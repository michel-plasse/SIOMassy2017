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
import model.Question;

/**
 *
 * @author admin
 */
public class QuestionDao implements QuestionHome{
    
    private Connection connection ;

    @Override
    public void insert(Question objetAInserer) throws SQLException {
        connection = ConnectionBd.getConnection();
         String sqlstmt = "INSERT INTO question ('id_question','id_qcm','question')"
                + "VALUES(?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sqlstmt);
        stmt.setInt(1, objetAInserer.getIdQuestion());
        stmt.setInt(2, objetAInserer.getQcm().getIdQcm());
        stmt.setString(3, objetAInserer.getQuestion());
        stmt.executeUpdate();
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
        connection = ConnectionBd.getConnection();
        Question question = null;
        QcmDao qcmDao = new QcmDao();
         String sqlstmt = "SELECT id_qcm, question FROM question"
                + "WHERE id_question = ? ";
        PreparedStatement stmt = connection.prepareStatement(sqlstmt);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        ResultSet res = stmt.executeQuery(sqlstmt);
        if (res.next()){
            question = new Question(qcmDao.findById(
                    res.getInt("id_qcm")),
                    res.getString("question"));
        }
        return question;
    }

    @Override
    public ArrayList<Question> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
