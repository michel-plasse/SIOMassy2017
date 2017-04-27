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


/**
 *
 * @author admin
 */
public interface QcmHome<T> extends Dao<T> {
    
    public int insert(int idFormateur, int idModule, T nouveauQcm) throws SQLException;
    public void insertPassage (int idUser, T qcmRepsChoisies) throws SQLException;

    /**
     *
     * @param idUser
     * @param idQcm
     * @return
     * @throws SQLException
     */
    public HashSet<Integer> findAnsByIdPassage (int idUser, int idQcm) throws SQLException;
    public int isAlreadyDone(int idUser, int idQcm) throws SQLException;
    public ArrayList<T> findAllByFormateur(int idFormateur) throws SQLException;
    public boolean rendValideQcm(int idQcm) throws SQLException;
    public boolean rendArchiveQcm(int idQcm) throws SQLException;
}
