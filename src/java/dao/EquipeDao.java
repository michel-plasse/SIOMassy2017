package dao;

import static dao.DAOUtilitaire.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Equipe;
import model.Personne;
import model.Projet;


public class EquipeDao implements EquipeHome<Equipe> {
    
    private Connection connection;

    private static final String SQL_INSERT = "INSERT INTO equipe(id_createur, id_projet, date_creation) VALUES (?,?,NOW())";
    private static final String SQL_DELETE = "DELETE FROM equipe WHERE id_equipe = ?";
    private static final String SQL_SELECT_TEAM = "SELECT id_equipe, id_createur, id_projet, date_creation FROM equipe WHERE id_equipe = ?";
    
    private static final String SQL_INSERT_MEMBER = "INSERT INTO membre_equipe(id_equipe,id_personne) VALUES (?,?)";
    private static final String SQL_DELETE_MEMBER = "DELETE FROM membre_equipe WHERE ( id_equipe = ? AND id_personne = ? )";
    
    private static final String SQL_SELECT_ALL_BYPROJECT = "SELECT id_equipe, id_personne, id_projet, date_creation FROM equipe WHERE id_projet = ?";
    private static final String SQL_SELECT_MEMBERS_BYTEAM = "SELECT id_personne FROM membre_equipe WHERE id_equipe = ? ";

    //private static final String SQL_UPDATE_EQUIPE = "UPDATE equipe"
    
    
    
    @Override
    public void insert(Equipe uneEquipe) throws SQLException {

        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        ResultSet idGenerated = null;
        
        try {
            preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT, true, 
                                                              uneEquipe.getCreateur().getId(),
                                                              uneEquipe.getUnProjet().getId());
            preparedStatement.executeUpdate();
            
            idGenerated = preparedStatement.getGeneratedKeys();
            
            if (idGenerated.next()) {
                uneEquipe.setId(idGenerated.getInt(1));
                uneEquipe.setDateCreation(idGenerated.getDate(4));
                System.out.println("Nouvelle equipe créée : " + uneEquipe);
            }
            connection.commit();
            System.out.println("Nouvelle equipe insérée en BDD..");
            
        }catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        }finally{
            connection.setAutoCommit(true);
            fermeturesSilencieuses(idGenerated, preparedStatement, connection);
            
        }
        
    }

    @Override
    public boolean delete(int id) throws SQLException {
        
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        boolean isDelete = false;
        
        try {
            preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE, false,id);
            int result = preparedStatement.executeUpdate();
            
            if (result != 0) {
                connection.commit();
                isDelete = true;
                System.out.println("Equipe supprimée...");
            }
            
        }catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        }finally{
            connection.setAutoCommit(true);
            fermeturesSilencieuses(preparedStatement, connection);
            
        }
        
        return isDelete;
    }

    @Override
    public boolean update(int idAncien, Equipe nouveau) throws SQLException {
        return false;
    }

    @Override
    public Equipe findById(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Equipe> findAll() throws SQLException {
        return null;
    }

    @Override
    public boolean ajouterMembre(Equipe uneEquipe, Personne unePersonne) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        boolean isAdd = false;
        
        try {
            preparedStatement = initialisationRequetePreparee(connection, SQL_INSERT_MEMBER, false, 
                                                              uneEquipe.getId(),
                                                              unePersonne.getId());
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                connection.commit();
                isAdd = true;
                System.out.println(unePersonne.getPrenom() + " ajouté à l'équipe de " +uneEquipe.getCreateur().getPrenom());
            }
            
        }catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        }finally{
            connection.setAutoCommit(true);
            fermeturesSilencieuses(preparedStatement, connection);
            
        }
        return isAdd;
    }

    @Override
    public boolean retirerMembre(Equipe uneEquipe, Personne unePersonne) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        boolean isDelete = false;
        
        try {
            preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE_MEMBER, false, 
                                                              uneEquipe.getId(),
                                                              unePersonne.getId());
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                connection.commit();
                isDelete = true;
                System.out.println(unePersonne.getPrenom() + " retiré de l'équipe de " +uneEquipe.getCreateur().getPrenom());
            }
            
        }catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        }finally{
            connection.setAutoCommit(true);
            fermeturesSilencieuses(preparedStatement, connection);
            
        }
        return isDelete;
    }
    
}
