package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Note;


public class NoteDao implements NoteHome{
    
    private Connection connection;

    @Override
    public void insert(Note note) throws SQLException {
        try {
            connection = ConnectionBd.getConnection();
            // Commencer une transaction
            connection.setAutoCommit(false);
            String sql = "INSERT INTO note (id_note, note, commentaire)" + " VALUES (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, note.getId_note());
            stmt.setDouble(2, note.getNote());
            stmt.setString(5, note.getCommentaire());
            stmt.executeUpdate();
            // Recuperer le id
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
    public boolean update(int idAncien, Note nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public ArrayList<Note> findByIdNoteEval(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        EvaluationDao evaluationDao = new EvaluationDao();
        PersonneDao personneDao = new PersonneDao();
        ArrayList<Note> lesNotes = new ArrayList<>();
        String sql = "SELECT id_evaluation, note, commentaire FROM note WHERE id_personne = ? ";
        PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery();
        while(res.next()){
            Note laNote = new Note();
                    laNote.setUneEvaluation(evaluationDao.findById(res.getInt("id_evaluation")));
                    laNote.setNote(res.getDouble("note"));
                    laNote.setCommentaire(res.getString("commentaire"));
            lesNotes.add(laNote);
        }
        return lesNotes;
    }
    
}
