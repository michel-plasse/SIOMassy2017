/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.EtatCandidature;
import model.SessionFormation;

/**
 *
 * @author admin
 */
public class EtatCandidatureDao implements EtatCandidatureHome<EtatCandidature>{

    @Override
    public void insert(EtatCandidature objetAInserer) throws SQLException {
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(int idAncien, EtatCandidature nouveau) throws SQLException {
        return false;
    }

    @Override
    public EtatCandidature findById(int id) throws SQLException {
        Connection connection = ConnectionBd.getConnection();
        Statement statement = connection.createStatement();
        EtatCandidature etatCandidature = null;
        ResultSet resultat = statement.executeQuery("SELECT * FROM etat_candidature WHERE id_etat_candidature = " + id);

        if(resultat.next()) {
            etatCandidature = new EtatCandidature(
                    resultat.getInt("id_etat_candidature"),
                    resultat.getString("libelle"));
        }
        return etatCandidature;
    }

    @Override
    public ArrayList<EtatCandidature> findAll() throws SQLException {
        ArrayList<EtatCandidature> lesEtat = new ArrayList<>();
        Connection connection = ConnectionBd.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM etat_candidature");
        
        while (resultSet.next()) {            
            int id = resultSet.getInt("id_etat_candidature");
            String libelle = resultSet.getString("libelle");
            EtatCandidature etatCandidature = new EtatCandidature(id, libelle);
            lesEtat.add(etatCandidature);
        }        
        return lesEtat;
    }
}
