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
            String sql = "INSERT INTO note (nom, prenom, email, no_rue, rue, code_postal, ville, pays, mot_de_passe)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, note.getIdNote());
            stmt.setInt(2, note.getIdEvaluation());
            stmt.setInt(3, note.getIdEleve());
            stmt.setDouble(4, note.getValeurNote());
            stmt.setString(5, note.getCommentaire());
            stmt.executeUpdate();
            // Recuperer le id
            sql = "SELECT MAX(id_personne) AS id FROM personne";
            Statement lecture = connection.createStatement();
            ResultSet rs = lecture.executeQuery(sql);
            rs.next();
            note.setIdNote(rs.getInt("id"));
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
    
    
    
}
