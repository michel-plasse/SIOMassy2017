/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Evaluation;
import model.Note;

/**
 *
 * @author admin
 */
public interface EvaluationHome extends Dao<Evaluation> {
    
    public ArrayList<Evaluation> findAllEvalFormateur(int idFormateur)throws SQLException ;
    public ArrayList<Evaluation> findPastEvalFormateur(int idFormateur) throws SQLException;
    public ArrayList<Note> findByEval(int idEvaluation) throws SQLException;
    
}
