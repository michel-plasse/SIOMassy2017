/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Projet;

/**
 *
 * @author admin
 */
public class ProjetDao implements ProjetHome<Projet> {

    private Connection connection;

    public ProjetDao() {
    }

    @Override
    public void insert(Projet projet) throws SQLException {

        try {
            connection = ConnectionBd.getConnection();
            // Commencer une transaction
            connection.setAutoCommit(false);
            String sql = "INSERT INTO projet(id_formateur, id_session, sujet, description, date_limite, date_creation)"
                    + " VALUES (?,?,?,?,?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, projet.getIdFormateur());
            stmt.setInt(2, projet.getIdSession());
            stmt.setString(3, projet.getSujet());
            stmt.setString(4, projet.getDescription());
            stmt.setDate(5, new java.sql.Date(projet.getDateLimite().getTime()));
            stmt.setDate(6, new java.sql.Date(projet.getDateCreation().getTime()));
            System.out.println(projet);
            stmt.executeUpdate();
            // Recuperer le id
            sql = "SELECT MAX(id_projet) AS id from projet";
            Statement lecture = connection.createStatement();
            ResultSet rs = lecture.executeQuery(sql);
            rs.next();

            projet.setId(rs.getInt("id"));
            System.out.println(projet);
            // Valider
            connection.commit();
            System.out.println("Projet insérée.");
        } catch (SQLException exc) {
            exc.printStackTrace();
            connection.rollback();
            System.out.println("Rollback.");
            throw exc;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM projet WHERE id_projet =" + id + ";");
        return false;
    }

    @Override
    public boolean update(int idAncien, Projet nouveau) throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        int sessionFormation = nouveau.getIdSession();
        String sujet = nouveau.getSujet();
        Date dateCreation = nouveau.getDateCreation();
        Date dateLimite = nouveau.getDateLimite();
        String description = nouveau.getDescription();

        stmt.executeUpdate("UPDATE projet SET(" + sessionFormation + "," + sujet + ",'" + dateCreation + ",'" + dateLimite + ",'" + description + ";");
        return false;
    }

    @Override
    public Projet findById(int id) throws SQLException {
        Projet result = null;
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM agriotes2017.projet WHERE id_projet =" + id + ";");
        if (rs.next()) {
            result = new Projet(id,
                    rs.getString("sessionFormation"), rs.getString("sujet"), rs.getString("description"),
                    rs.getDate("dateCreation"), rs.getDate("dateLimite"));
        }
        return result;
    }

    @Override
    public ArrayList<Projet> findAll() throws SQLException {
        ArrayList<Projet> lesProjets = new ArrayList<Projet>();
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet resall = stmt.executeQuery("SELECT s.id_session as idSession,\n" +
"       p.sujet as sujet ,\n" +
"       p.date_creation as dateCreation ,\n" +
"       p.date_limite as dateLimite ,\n" +
"       p.description as description\n" +
"from\n" +
"	session_formation s\n" +
"		inner join\n" +
"	projet p on s.id_session=p.id_session ;");
        resall.next();
        return lesProjets;
    }
    
    @Override
    public ArrayList<Projet> findAllByIdFormateur(int idFormateur) throws SQLException {
        ArrayList<Projet> lesProjetsDuFormateur = new ArrayList<Projet>();
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet resall = stmt.executeQuery("SELECT s.id_session as idSession," +
            " p.sujet as sujet ," +
            " p.date_creation as dateCreation ," +
            " p.date_limite as dateLimite ," +
            " p.description as description" +
            "from\n" +
            " session_formation s\n" +
            " inner join" +
            " projet p on s.id_session=p.id_session "
                + "where p.id_formateur =" + idFormateur + ";");
        resall.next();
        return lesProjetsDuFormateur;
    }
    
}
