package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Evaluation;
import model.Formateur;
import model.Module;
import model.Note;

public class NoteDao implements NoteHome {

    private Connection connection;

    @Override
    public void insert(Note note) throws SQLException {
        try {
            connection = ConnectionBd.getConnection();
            // Commencer une transaction
            connection.setAutoCommit(false);
            String sql = "INSERT INTO note (id_personne, id_evaluation, note, commentaire) VALUES (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, note.getEtudiant().getId());
            stmt.setInt(2, note.getEvaluation().getIdEvaluation());
            stmt.setDouble(3, note.getNote());
            stmt.setString(4, note.getCommentaire());
            stmt.executeUpdate();
            // Recuperer l'id
            sql = "SELECT MAX(id_note) AS id FROM note";
            Statement lecture = connection.createStatement();
            ResultSet rs = lecture.executeQuery(sql);
            rs.next();
            note.setId_note(rs.getInt("id"));
            // Valider
            connection.commit();
        } catch (SQLException exc) {
            connection.rollback();
            throw exc;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int idAncien, Note note) throws SQLException {
        connection = ConnectionBd.getConnection();
            // Commencer une transaction
            String sql = "UPDATE personne SET note = ?, commentaire = ?)"
                    + " VALUES (?,?) WHERE id_personne = "+ idAncien + ";";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, note.getNote());
            stmt.setString(2, note.getCommentaire());
            stmt.executeUpdate();

        return false;
    }

    @Override
    public Note findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Note> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Note> findByIdEleveNoteEval(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        EvaluationDao evaluationDao = new EvaluationDao();
        ArrayList<Note> lesNotes = new ArrayList();
        String sql = "SELECT id_evaluation, note, commentaire FROM note WHERE id_personne = ? ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            Note laNote = new Note();
            laNote.setEvaluation(evaluationDao.findById(res.getInt("id_evaluation")));
            laNote.setNote(res.getDouble("note"));
            laNote.setCommentaire(res.getString("commentaire"));
            lesNotes.add(laNote);
        }
        return lesNotes;
    }

    public ArrayList<Note> findNoteById(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        ArrayList<Note> lesNotes = new ArrayList();
        String sql = "SELECT p.id_personne, p.prenom, p.nom, e.id_evaluation, e.date_effet, e.intitule, m.id_module, m.nom, n.commentaire, n.note FROM personne p"
                + " INNER JOIN formateur f ON p.id_personne = f.id_personne"
                + " INNER JOIN evaluation e ON f.id_personne = e.id_formateur"
                + " INNER JOIN module m ON e.id_module = m.id_module"
                + " INNER JOIN note n ON e.id_evaluation = n.id_evaluation"
                + " INNER JOIN personne pe ON n.id_personne = pe.id_personne"
                + " WHERE pe.id_personne = ?"
                + " ORDER BY date_effet DESC;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            Evaluation evaluation = new Evaluation(
                    res.getInt("e.id_evaluation"),
                    new Module(res.getInt("m.id_module"),res.getString("m.nom")),
                    new Formateur(res.getInt("p.id_personne"), res.getString("p.nom"),res.getString("p.prenom")),
                    res.getDate("e.date_effet"),
                    res.getString("e.intitule")
              );
            Note laNote = new Note();
            laNote.setEvaluation(evaluation);
            laNote.setNote(res.getDouble("n.note"));
            laNote.setCommentaire(res.getString("n.commentaire"));
            lesNotes.add(laNote);
        }
        return lesNotes;

    }
}
