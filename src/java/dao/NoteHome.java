package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Note;

public interface NoteHome extends Dao <Note> {
    
    public ArrayList<Note> findByIdEleveNoteEval(int id) throws SQLException;
    public boolean checkIdFormateur (int idEval, int idFormateur)throws SQLException;
    
}
