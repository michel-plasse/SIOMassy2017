/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Qcm;

/**
 *
 * @author admin
 */
public interface QcmHome<T> extends Dao<T> {
    
    public void insert(int idFormateur, int idModule, T nouveauQcm) throws SQLException;
    public void insertPassage (int idUser, T qcmRepsChoisies) throws SQLException;
    public ArrayList<Integer> findAnsByIdPassage (int idUser, int idQcm) throws SQLException;
    public int isAlreadyDone(int idUser, int idQcm) throws SQLException;
    @Override
    public ArrayList<T> findAll() throws SQLException;
    
}
