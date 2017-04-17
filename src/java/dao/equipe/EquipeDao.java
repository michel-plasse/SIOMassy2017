package dao.equipe;

import dao.ConnectionBd;
import static dao.DAOUtilitaire.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import model.Equipe;
import model.Personne;

public class EquipeDao implements EquipeHome<Equipe> {

    private Connection connection;

    //private static final String SQL_UPDATE_EQUIPE = "UPDATE equipe"
    @Override
    public int insertReturnId(int idProjet, Equipe uneEquipe) throws SQLException {

        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        String sqlInsert = "INSERT INTO equipe(id_createur, id_projet, date_creation) VALUES (?,?,NOW())";
        PreparedStatement preparedStatement = null;
        ResultSet idGenerated = null;
        Integer idEquipeNv = null;

        try {
            preparedStatement = initialisationRequetePreparee(connection, sqlInsert, true,
                    uneEquipe.getCreateur().getId(),
                    idProjet);
            preparedStatement.executeUpdate();

            idGenerated = preparedStatement.getGeneratedKeys();

            if (idGenerated.next()) {
                idEquipeNv = idGenerated.getInt(1);
                System.out.println("Nouvelle equipe créée : " + idEquipeNv);
            }
            connection.commit();
            System.out.println("Nouvelle equipe insérée en BDD..");

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        } finally {
            connection.setAutoCommit(true);
            fermeturesSilencieuses(idGenerated, preparedStatement, connection);

        }
        return idEquipeNv;
    }

    @Override
    public boolean delete(int id) throws SQLException {

        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        String sqlDelete = "DELETE FROM equipe WHERE id_equipe = ?";
        PreparedStatement preparedStatement = null;
        boolean isDelete = false;

        try {
            preparedStatement = initialisationRequetePreparee(connection, sqlDelete, false, id);
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                connection.commit();
                isDelete = true;
                System.out.println("Equipe supprimée...");
            }

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        } finally {
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
    public Equipe findById(int idEquipe) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sqlFindById = "SELECT e.id_equipe,"
                + "e.id_projet,"
                + "e.date_creation,"
                + "p.id_personne,"
                + "p.nom,"
                + "p.prenom,"
                + "p.email,"
                + "p.no_tel,"
                + "p.photo,"
                + "pc.id_personne,"
                + "pc.nom,"
                + "pc.prenom,"
                + "pc.email,"
                + "pc.no_tel,"
                + "pc.photo"
                + " FROM equipe AS e"
                + " INNER JOIN personne AS pc ON e.id_createur = pc.id_personne"
                + " INNER JOIN membre_equipe AS me ON e.id_equipe = me.id_equipe"
                + " INNER JOIN"
                + " personne AS p ON me.id_personne = p.id_personne"
                + " WHERE e.id_equipe = ?";

        PreparedStatement preparedStatementInfoEquipe = null;
        ResultSet result = null;
        Equipe equipe;
        HashMap<Integer, Personne> lesMembres;

        try {
            preparedStatementInfoEquipe = initialisationRequetePreparee(connection, sqlFindById, false, idEquipe);

            result = preparedStatementInfoEquipe.executeQuery();

            equipe = new Equipe();
            lesMembres = new HashMap<>();

            while (result.next()) {
                Personne unMembre = new Personne();
                unMembre.setId(result.getInt("p.id_personne"));
                unMembre.setNom(result.getString("p.nom"));
                unMembre.setPrenom(result.getString("p.prenom"));
                unMembre.setEmail(result.getString("p.email"));
                unMembre.setNo_tel(result.getString("p.no_tel"));
                unMembre.setPhoto(result.getString("p.photo"));

                lesMembres.put(unMembre.getId(), unMembre);

                if (result.isLast()) {
                    Personne createur = new Personne();
                    createur.setId(result.getInt("pc.id_personne"));
                    createur.setNom(result.getString("pc.nom"));
                    createur.setPrenom(result.getString("pc.prenom"));
                    createur.setEmail(result.getString("pc.email"));
                    createur.setNo_tel(result.getString("pc.no_tel"));
                    createur.setPhoto(result.getString("pc.photo"));
                    equipe.setCreateur(createur);
                    equipe.setDateCreation(result.getDate("e.date_creation"));
                    equipe.setId(result.getInt("e.id_equipe"));
                    equipe.setIdProjet(result.getInt("e.id_projet"));
                    equipe.setLesMembres(lesMembres);
                }
            }
            System.out.println("Récupération des infos de l'équipe ok...");
            
        } catch (SQLException e) {
            System.out.println("Probleme avec la récupération des infos de l'équipe...");
            throw e;
        } finally {
            fermeturesSilencieuses(result, preparedStatementInfoEquipe, connection);
        }
        return equipe;
    }

    @Override
    public ArrayList<Equipe> findAll() throws SQLException {
        return null;
    }

    @Override
    public boolean ajouterMembre(int idEquipe, int idPersonne) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        String sqlInsertMember = "INSERT INTO membre_equipe(id_equipe,id_personne) VALUES (?,?)";
        PreparedStatement preparedStatement = null;
        boolean isAdd = false;

        try {
            preparedStatement = initialisationRequetePreparee(connection, sqlInsertMember, false,
                    idEquipe,
                    idPersonne);
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                connection.commit();
                isAdd = true;
                System.out.println(idPersonne + " ajouté à l'équipe " + idEquipe);
            }

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        } finally {
            connection.setAutoCommit(true);
            fermeturesSilencieuses(preparedStatement, connection);

        }
        return isAdd;
    }

    @Override
    public boolean retirerMembre(int idEquipe, int idPersonne) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        String sqlDeleteMember = "DELETE FROM membre_equipe WHERE ( id_equipe = ? AND id_personne = ? )";
        PreparedStatement preparedStatement = null;
        boolean isDelete = false;

        try {
            preparedStatement = initialisationRequetePreparee(connection, sqlDeleteMember, false,
                    idEquipe,
                    idPersonne);
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                connection.commit();
                isDelete = true;
                System.out.println(idPersonne + " retiré de l'équipe de " + idEquipe);
            }

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème SQL => Rollback.");
            throw e;
        } finally {
            connection.setAutoCommit(true);
            fermeturesSilencieuses(preparedStatement, connection);

        }
        return isDelete;
    }

    @Override
    public ArrayList<Equipe> findAll(int idProjet) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sqlFindAllByProject = "SELECT e.id_equipe,"
                + "e.id_projet,"
                + "e.date_creation,"
                + "p.id_personne,"
                + "p.nom,"
                + "p.prenom,"
                + "p.email,"
                + "p.no_tel,"
                + "p.photo,"
                + "pc.id_personne,"
                + "pc.nom,"
                + "pc.prenom,"
                + "pc.email,"
                + "pc.no_tel,"
                + "pc.photo"
                + " FROM equipe AS e"
                + " INNER JOIN personne AS pc ON e.id_createur = pc.id_personne"
                + " INNER JOIN membre_equipe AS me ON e.id_equipe = me.id_equipe"
                + " INNER JOIN"
                + " personne AS p ON me.id_personne = p.id_personne"
                + " WHERE e.id_projet = ?"
                + " ORDER BY e.id_equipe";

        PreparedStatement preparedStatementGetEquipe = null;
        ResultSet result = null;
        ArrayList<Equipe> lesEquipes;

        try {
            preparedStatementGetEquipe = initialisationRequetePreparee(connection, sqlFindAllByProject, false, idProjet);
            result = preparedStatementGetEquipe.executeQuery();
            lesEquipes = new ArrayList<>();
            HashMap<Integer, Personne> lesMembres;
            Equipe equipe = null;
            int idEquipe = -1;

            while (result.next()) {
                if (idEquipe != result.getInt("e.id_equipe")) {
                    equipe = new Equipe();
                    Personne createur = new Personne();
                    createur.setId(result.getInt("pc.id_personne"));
                    createur.setNom(result.getString("pc.nom"));
                    createur.setPrenom(result.getString("pc.prenom"));
                    createur.setEmail(result.getString("pc.email"));
                    createur.setNo_tel(result.getString("pc.no_tel"));
                    createur.setPhoto(result.getString("pc.photo"));

                    lesMembres = new HashMap<>();

                    equipe.setCreateur(createur);
                    equipe.setDateCreation(result.getDate("e.date_creation"));
                    equipe.setId(result.getInt("e.id_equipe"));
                    equipe.setLesMembres(lesMembres);

                    idEquipe = result.getInt("e.id_equipe");
                    
                    lesEquipes.add(equipe);
                }

                Personne unMembre = new Personne();
                unMembre.setId(result.getInt("p.id_personne"));
                unMembre.setNom(result.getString("p.nom"));
                unMembre.setPrenom(result.getString("p.prenom"));
                unMembre.setEmail(result.getString("p.email"));
                unMembre.setNo_tel(result.getString("p.no_tel"));
                unMembre.setPhoto(result.getString("p.photo"));

                if (equipe != null) {
                    equipe.getLesMembres().put(unMembre.getId(), unMembre);
                }
            }

        } catch (SQLException e) {
            System.out.println("Probleme avec la récupération des équipes");
            throw e;
        } finally {
            fermeturesSilencieuses(result, preparedStatementGetEquipe, connection);
        }

        return lesEquipes;
    }

    @Override
    public ArrayList<Personne> findAllNotInTeam(int idProjet) throws SQLException {

        connection = ConnectionBd.getConnection();
        String sqlGetFreePeople = "SELECT id_personne, nom, prenom, email FROM membre_promotion WHERE id_session = (SELECT id_session FROM projet WHERE id_projet = ? ) "
                + "AND ( id_personne <> ALL "
                + "(SELECT id_personne FROM projet as p "
                + "INNER JOIN equipe as e ON p.id_projet = e.id_projet "
                + "INNER JOIN membre_equipe as me ON e.id_equipe = me.id_equipe "
                + "WHERE p.id_projet = ? ) "
                + "AND id_personne <> ALL "
                + "(SELECT id_createur FROM projet as p "
                + "INNER JOIN equipe as e ON p.id_projet = e.id_projet "
                + "WHERE p.id_projet = ? ))";

        PreparedStatement preparedStatementGetFreePeople = null;
        ResultSet result = null;
        ArrayList<Personne> personnesLibres = new ArrayList<>();

        try {
            preparedStatementGetFreePeople = initialisationRequetePreparee(connection, sqlGetFreePeople, false,
                    idProjet,
                    idProjet,
                    idProjet);

            result = preparedStatementGetFreePeople.executeQuery();

            while (result.next()) {
                //récupération des informations sur les stagiaires sans equipe
                Personne unePersonne = new Personne();
                unePersonne.setId(result.getInt("id_personne"));
                unePersonne.setNom(result.getString("nom"));
                unePersonne.setPrenom(result.getString("prenom"));
                unePersonne.setEmail(result.getString("email"));
                personnesLibres.add(unePersonne);
            }

            System.out.println("Récupération des stagiaires sans équipe : OK ..");

        } catch (SQLException e) {
            System.out.println("Probleme avec la récupération des stagiaires sans équipe...");
            throw e;
        } finally {
            fermeturesSilencieuses(result, preparedStatementGetFreePeople, connection);
        }

        return personnesLibres;
    }

    @Override
    public void insert(Equipe objetAInserer) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
