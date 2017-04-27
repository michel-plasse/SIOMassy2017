/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.qcm;

import dao.ConnectionBd;
import static dao.DAOUtilitaire.*;
import dao.ModuleDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import model.Choix;
import model.Module;
import model.Qcm;
import model.Question;

public class QcmDao implements QcmHome<Qcm> {

    private Connection connection;
    private static String SQL_DELETE_QUESTION = "DELETE FROM question WHERE id_qcm = ?";
    private static String SQL_DELETE_CHOIX = "DELETE FROM choix WHERE id_question = ?";
    private static String SQL_DELETE_QCM = "DELETE FROM qcm WHERE id_qcm = ?";

    @Override
    public ArrayList<Qcm> findAll() throws SQLException {
        connection = ConnectionBd.getConnection();
        String sql = "SELECT id_qcm, id_formateur, id_module, intitule, valide FROM qcm WHERE valide = 1 AND archive = 0";
        ResultSet res = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Qcm> lesQcm = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            res = preparedStatement.executeQuery();
            while (res.next()) {
                ModuleDao moduleDao = new ModuleDao();
                Module module = moduleDao.findById(res.getInt("id_module"));
                Qcm unQcm = new Qcm(
                        res.getInt("id_qcm"),
                        res.getString("intitule"),
                        res.getBoolean("valide"),
                        module.getNom());
                lesQcm.add(unQcm);
            }
        } catch (SQLException ex) {
            System.out.println("Problème avec findAllQcm");
            throw ex;
        }
        return lesQcm;
    }

    @Override
    public int insert(int idFormateur, int idModule, Qcm nouveauQcm) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO qcm (id_formateur,id_module,intitule,valide) VALUES (?,?,?,?)";
        ResultSet resQcm = null;
        PreparedStatement preparedStatement = null;
        int idGenere = -1;

        try {
            preparedStatement = initialisationRequetePreparee(connection, sql, true,
                    idFormateur,
                    idModule,
                    nouveauQcm.getIntitule(),
                    nouveauQcm.isValide());

            preparedStatement.executeUpdate();

            resQcm = preparedStatement.getGeneratedKeys();

            if (resQcm.next()) {
                idGenere = resQcm.getInt(1);
                if (nouveauQcm.getLesQuestions() != null) {
                    String sqlQuestion = "INSERT INTO question(id_qcm,question) VALUES(?,?)";
                    ResultSet resQuest = null;
                    PreparedStatement preparedStatementQuestion = connection.prepareStatement(sqlQuestion, Statement.RETURN_GENERATED_KEYS);
                    String sqlChoix = "INSERT INTO choix (id_question, libelle, est_correct) VALUES(?,?,?)";
                    PreparedStatement preparedStatementChoix = connection.prepareStatement(sqlChoix);

                    for (Question uneQuestion : nouveauQcm.getLesQuestions()) {
                        preparedStatementQuestion.setInt(1, idGenere);
                        preparedStatementQuestion.setString(2, uneQuestion.getQuestion());
                        preparedStatementQuestion.executeUpdate();
                        resQuest = preparedStatementQuestion.getGeneratedKeys();

                        if (resQuest.next()) {
                            for (Choix unChoix : uneQuestion.getLesChoix().values()) {
                                preparedStatementChoix.setInt(1, resQuest.getInt(1));
                                preparedStatementChoix.setString(2, unChoix.getLibelle());
                                preparedStatementChoix.setBoolean(3, unChoix.isEstCorrect());
                                preparedStatementChoix.executeUpdate();
                            }

                        }
                    }
                }

            }

            connection.commit();
            System.out.println("Ajout du QCM : " + nouveauQcm.getIntitule() + " -> Ok...");

        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème avec la BDD pour ajout QCM");
            throw e;
        } finally {
            fermeturesSilencieuses(resQcm, preparedStatement, connection);
        }
        return idGenere;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        boolean delete = false;
        QuestionDao questionDao = new QuestionDao();
        ArrayList<Question> lesQuestions = questionDao.findAll(id);

        try {
            if (!lesQuestions.isEmpty()) {
                for (Question laQuestion : lesQuestions) {
                    if (laQuestion.getLesChoix() != null) {
                        int idQuestion = laQuestion.getIdQuestion();
                        preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE_CHOIX, false, idQuestion);
                        preparedStatement.executeUpdate();
                    }
                }
                preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE_QUESTION, false, id);
                preparedStatement.executeUpdate();
            }
            preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE_QCM, false, id);
            preparedStatement.executeUpdate();
            delete = true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
            fermeturesSilencieuses(preparedStatement, connection);
        }
        return delete;
    }

    @Override
    public boolean update(int idAncien, Qcm nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Qcm findById(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sql = "SELECT qc.id_qcm, qc.id_formateur, qc.id_module, qc.intitule, qc.valide, qu.id_question, qu.question, ch.id_choix, ch.libelle, ch.est_correct "
                + "FROM qcm as qc "
                + "INNER JOIN question as qu ON qc.id_qcm = qu.id_qcm "
                + "LEFT OUTER JOIN choix as ch ON qu.id_question = ch.id_question "
                + "WHERE qc.id_qcm = ? "
                + "ORDER BY id_question";

        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        Qcm unQcm = null;

        try {
            preparedStatement = initialisationRequetePreparee(connection, sql, false, id);
            res = preparedStatement.executeQuery();

            int idQuestion = -1;
            Question uneQuestion = null;
            Choix unChoix;
            ArrayList<Question> questions = new ArrayList<>();

            while (res.next()) {
                if (res.getInt("qu.id_question") != idQuestion) {
                    uneQuestion = new Question();
                    uneQuestion.setIdQuestion(res.getInt("qu.id_question"));
                    uneQuestion.setQuestion(res.getString("qu.question"));
                    HashMap<Integer, Choix> lesChoix = new HashMap<>();
                    uneQuestion.setLesChoix(lesChoix);
                    questions.add(uneQuestion);
                    idQuestion = res.getInt("qu.id_question");
                }

                unChoix = new Choix();
                unChoix.setIdChoix(res.getInt("ch.id_choix"));
                unChoix.setLibelle(res.getString("ch.libelle"));
                unChoix.setEstCorrect(res.getBoolean("ch.est_correct"));

                if (uneQuestion != null) {
                    uneQuestion.getLesChoix().put(unChoix.getIdChoix(), unChoix);
                }

                if (res.isLast()) {
                    unQcm = new Qcm();
                    unQcm.setIdQcm(res.getInt("qc.id_qcm"));
                    unQcm.setIntitule(res.getString("qc.intitule"));
                    unQcm.setValide(res.getBoolean("qc.valide"));
                    unQcm.setLesQuestions(questions);
                }
            }

        } catch (SQLException e) {
            System.out.println("Problème SQL rencontré lors de la récupération des données du QCM");
            throw e;
        } finally {
            fermeturesSilencieuses(res, preparedStatement, connection);
        }
        return unQcm;
    }

    @Override
    public void insert(Qcm objetAInserer) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertPassage(int idUser, Qcm qcmRepsChoisies) throws SQLException {
        connection = ConnectionBd.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO passage_qcm(id_qcm,id_personne,date_passage, est_finie) VALUES (?,?,NOW(),?)";
        PreparedStatement preparedStatement = null;
        ResultSet idPassage = null;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, qcmRepsChoisies.getIdQcm());
            preparedStatement.setInt(2, idUser);
            preparedStatement.setBoolean(3, true);

            preparedStatement.executeUpdate();
            idPassage = preparedStatement.getGeneratedKeys();

            if (idPassage.next()) {
                String sqlChoix = "INSERT INTO reponse(id_passage_qcm,id_choix) VALUES (?,?)";
                PreparedStatement preparedStatementChoix = connection.prepareStatement(sqlChoix);
                for (Question q : qcmRepsChoisies.getLesQuestions()) {
                    for (Choix c : q.getLesChoix().values()) {
                        if (c.isEstChoisi()) {
                            preparedStatementChoix.setInt(1, idPassage.getInt(1));
                            preparedStatementChoix.setInt(2, c.getIdChoix());

                            preparedStatementChoix.executeUpdate();
                        }
                    }
                }
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Problème enregistrement des réponses du QCM");
            throw e;
        } finally {
            connection.setAutoCommit(true);
            fermeturesSilencieuses(idPassage, preparedStatement, connection);
        }

    }

    @Override
    public HashSet<Integer> findAnsByIdPassage(int idUser, int idQcm) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sql = "SELECT r.id_choix FROM passage_qcm AS pq "
                + "INNER JOIN reponse AS r ON pq.id_passage_qcm = r.id_passage_qcm "
                + "WHERE id_qcm = ? AND id_personne = ?";

        HashSet<Integer> returnListeIdChoix = new HashSet<>();
        ResultSet idChoix = null;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idQcm);
            preparedStatement.setInt(2, idUser);
            idChoix = preparedStatement.executeQuery();

            while (idChoix.next()) {
                returnListeIdChoix.add(idChoix.getInt("id_choix"));
            }

        } catch (SQLException e) {
            System.out.println("Problème récupération des réponses du QCM");
            throw e;
        } finally {
            fermeturesSilencieuses(idChoix, preparedStatement, connection);
        }

        return returnListeIdChoix;
    }

    @Override
    public int isAlreadyDone(int idUser, int idQcm) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sql = "SELECT id_passage_qcm FROM passage_qcm WHERE id_personne = ? AND id_qcm = ?";
        int rep = -1;
        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idQcm);

            res = preparedStatement.executeQuery();

            if (res.next()) {
                rep = res.getInt("id_passage_qcm");
            }
        } catch (SQLException e) {
            System.out.println("Probleme check isAlreadyDone QCM");
            throw e;
        } finally {
            fermeturesSilencieuses(res, preparedStatement, connection);
        }

        return rep;
    }

    @Override
    public ArrayList<Qcm> findAllByFormateur(int idFormateur) throws SQLException {
        connection = ConnectionBd.getConnection();
        String sql = "SELECT id_qcm, m.nom, intitule, valide ,archive FROM qcm "
                + " INNER JOIN module m ON qcm.id_module = m.id_module "
                + " WHERE id_formateur = ?";

        PreparedStatement preparedStatement = null;
        ResultSet res = null;
        Qcm unQcm = null;
        ArrayList<Qcm> lesQcm = new ArrayList<>();

        try {
            preparedStatement = initialisationRequetePreparee(connection, sql, false, idFormateur);
            res = preparedStatement.executeQuery();

            while (res.next()) {
                unQcm = new Qcm(res.getInt("id_qcm"), res.getString("intitule"), res.getBoolean("valide"), res.getString("m.nom"));
                unQcm.setArchive(res.getBoolean("archive"));
                lesQcm.add(unQcm);

            }
        } catch (SQLException e) {
            System.out.println("Probleme de findAllByFormateur");
            throw e;
        } finally {
            fermeturesSilencieuses(res, preparedStatement, connection);
        }
        return lesQcm;
    }

    @Override
    public boolean rendValideQcm(int idQcm) throws SQLException {
        boolean status = false;
        connection = ConnectionBd.getConnection();
        String sql = "UPDATE qcm SET valide = 1 WHERE id_qcm = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = initialisationRequetePreparee(connection, sql, false, idQcm);
            preparedStatement.executeUpdate();
            status = true;
        } catch (Exception e) {
            System.out.println("Probleme de rendre validé");
            throw e;
        } finally {
            fermeturesSilencieuses(preparedStatement, connection);
        }
        return status;
    }

    @Override
    public boolean rendArchiveQcm(int idQcm) throws SQLException {
        boolean status = false;
        connection = ConnectionBd.getConnection();
        String sql = "UPDATE qcm SET archive = 1 WHERE id_qcm = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = initialisationRequetePreparee(connection, sql, false, idQcm);
            preparedStatement.executeUpdate();
            status = true;
        } catch (Exception e) {
            System.out.println("Probleme de rendre archivé");
            throw e;
        } finally {
            fermeturesSilencieuses(preparedStatement, connection);
        }
        return status;
    }
}
