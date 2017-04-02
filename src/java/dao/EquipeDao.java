package dao;

import static dao.DAOUtilitaire.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import model.Equipe;
import model.Personne;
import model.Projet;
import org.apache.jasper.tagplugins.jstl.core.ForEach;


public class EquipeDao implements EquipeHome<Equipe> {
    
    private Connection connection;
    
    private static final String CHAMP_ID_EQUIPE = "id_equipe";
    private static final String CHAMP_ID_CREATEUR = "id_createur";
    private static final String CHAMP_ID_PERSONNE = "id_personne";
    private static final String CHAMP_ID_PROJET = "id_projet";
    private static final String CHAMP_DATE = "date_creation";

    private static final String SQL_INSERT = "INSERT INTO equipe(id_createur, id_projet, date_creation) VALUES (?,?,NOW())";
    private static final String SQL_DELETE = "DELETE FROM equipe WHERE id_equipe = ?";
    private static final String SQL_SELECT_TEAM = "SELECT id_equipe, id_createur, id_projet, date_creation FROM equipe WHERE id_equipe = ?";
    
    private static final String SQL_INSERT_MEMBER = "INSERT INTO membre_equipe(id_equipe,id_personne) VALUES (?,?)";
    private static final String SQL_DELETE_MEMBER = "DELETE FROM membre_equipe WHERE ( id_equipe = ? AND id_personne = ? )";
    
    private static final String SQL_SELECT_ALL_BYPROJECT = "SELECT id_equipe, id_createur, id_projet, date_creation FROM equipe WHERE id_projet = ?";
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

    @Override
    public ArrayList<Equipe> findAll(Projet unProjet) throws SQLException {
        connection = ConnectionBd.getConnection();
        PersonneDao personneDao = new PersonneDao();
        PreparedStatement preparedStatementGetEquipe = null;
        ResultSet result;
        ArrayList<Equipe> lesEquipes = new ArrayList<Equipe>();
        
        try {
            preparedStatementGetEquipe = initialisationRequetePreparee(connection, SQL_SELECT_ALL_BYPROJECT, false, 
                                                                       unProjet.getId());
            result = preparedStatementGetEquipe.executeQuery();
            
            while(result.next()) {
                HashMap<Integer,Personne> lesMembres = new HashMap<Integer,Personne>();
                PreparedStatement preparedStatementGetMembre = null;
                ResultSet resultIdMembre;
                
                try{
                    //récupération de l'ensemble des id des membres de l'équipe
                    preparedStatementGetMembre = initialisationRequetePreparee(connection, SQL_SELECT_MEMBERS_BYTEAM, false,
                                                                               result.getInt(CHAMP_ID_EQUIPE));
                    resultIdMembre = preparedStatementGetMembre.executeQuery();
                    
                    while(resultIdMembre.next()) {
                        //récupération des informations sur chaque membre
                        Personne unMembreDeLequipe = personneDao.findById(resultIdMembre.getInt("id_personne"));
                        
                        //ajout du membre récupéré à la liste des membres de l'equipe
                        lesMembres.put(unMembreDeLequipe.getId(), unMembreDeLequipe);       
                    }
                }catch (SQLException e) {
                    System.out.println("problème sql rencontré lors de la récupération des membres de l'équipe : id = " +result.getInt(CHAMP_ID_EQUIPE));
                    throw e;
                }finally{
                    fermetureSilencieuse(preparedStatementGetMembre);
                }
                
                //récupération des informations du createur
                Personne createur = personneDao.findById(result.getInt(CHAMP_ID_CREATEUR));
                
                Equipe uneEquipe = new Equipe();
                
                uneEquipe.setId(result.getInt(CHAMP_ID_EQUIPE));
                uneEquipe.setCreateur(createur);
                uneEquipe.setUnProjet(unProjet);
                uneEquipe.setLesMembres(lesMembres);
                uneEquipe.setDateCreation(result.getDate(CHAMP_DATE));
                System.out.println("Récupération des données relatives à l'équipe (id = " +uneEquipe.getId()+ " ) : OK...");
                lesEquipes.add(uneEquipe);
            }
            
        }catch (SQLException e) {
            System.out.println("Probleme avec la récupération des équipes");
            throw e;
        }finally{
            fermeturesSilencieuses(preparedStatementGetEquipe, connection);
        }
        
        return lesEquipes;
    }
    
}