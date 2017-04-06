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

public class OptionDao implements OptionHome{
    
    private Connection connection ;

    @Override
    public void insert(Option objetAInserer) throws SQLException {
        connection = ConnectionBd.getConnection();
         String sqlstmt = "INSERT INTO question ('id_option','id_question','option','est_correcte')"
                + "VALUES(?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sqlstmt);
        stmt.setInt(1, objetAInserer.getIdOption());
        stmt.setInt(2, objetAInserer.getQuestion().getIdQuestion());
        stmt.setString(3, objetAInserer.getOption());
        stmt.setBoolean(4, objetAInserer.isEstCorrecte());
        stmt.executeUpdate();
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
    
}
