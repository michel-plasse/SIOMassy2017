/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.qcm;

import dao.Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import model.Question;


/**
 *
 * @author admin
 */
public interface QcmHome<T> extends Dao<T> {
    
    public void insert(int idFormateur, int idModule, T nouveauQcm) throws SQLException;
    
    public int insertLight(int idFormateur, int idModule, T nouveauQcm) throws SQLException;
    
    public void updateLight(int idModule, T editQcm) throws SQLException;
    
    public void insertPassage (int idUser, T qcmRepsChoisies) throws SQLException;
    
    public void deleteQuestion(int idQuestion) throws SQLException;
    
    public boolean isCheckedQcmAndUser(int idQcm, int idFormateur) throws SQLException;;
    
    public void insertQuestion(int idQcm, Question uneQuestion) throws SQLException;
    
    public HashSet<Integer> findAnsByIdPassage (int idUser, int idQcm) throws SQLException;
    
    public int isAlreadyDone(int idUser, int idQcm) throws SQLException;
    
    @Override
    public ArrayList<T> findAll() throws SQLException;
    
}
