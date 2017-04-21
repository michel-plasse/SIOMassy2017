/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.qcm;

import dao.ConnectionBd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Choix;
import static dao.DAOUtilitaire.*;
import java.util.HashMap;

public class ChoixDao implements ChoixHome {

    private Connection connection;
    private static String CHAMP_ID_OPTION = "id_choix";
    private static String CHAMP_ID_QUESTION = "id_question";
    private static String CHAMP_OPTION = "choix";
    private static String CHAMP_EST_CORRECTE = "est_correct";

    private static String SQL_SELECT_OPTION_BY_QUESTION = "SELECT id_choix,id_question,choix,est_correct FROM choix WHERE id_question=?";

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int idAncien, Choix nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Choix findById(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        Choix choix = null;
        String sqlstmt = "SELECT id_choix, id_question, choix, est_correct FROM choix"
                + "WHERE id_choix = ? ";
        PreparedStatement stmt = connection.prepareStatement(sqlstmt);
        stmt.setInt(1, id);
        stmt.executeQuery();
        ResultSet res = stmt.executeQuery(sqlstmt);
        if (res.next()) {
            choix = new Choix(
                    res.getString("choix"),
                    res.getBoolean("est_correct"));
        }
        return choix;
    }

    @Override
    public ArrayList<Choix> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Integer, Choix> findByIdQuestion(int idQuestion) throws SQLException {
        connection = ConnectionBd.getConnection();
        PreparedStatement stmt = null;
        ResultSet res = null;
        HashMap<Integer, Choix> lesReponses = new HashMap<>();
        int cle = 0;

        try {

            stmt = initialisationRequetePreparee(connection, SQL_SELECT_OPTION_BY_QUESTION, false, idQuestion);
            res = stmt.executeQuery();
            while (res.next()) {
                Choix choix = new Choix(res.getString(CHAMP_OPTION), res.getBoolean(CHAMP_EST_CORRECTE));
                lesReponses.put(cle, choix);
                cle += 1;
            }

        } catch (SQLException e) {
            System.out.println("probleme récupération des reponses pour la question...");
            throw e;
        } finally {
            fermeturesSilencieuses(res, stmt, connection);
        }
        return lesReponses;
    }

    @Override
    public void insert(Choix objetAInserer) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
