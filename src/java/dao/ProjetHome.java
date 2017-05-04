/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Projet;

public interface ProjetHome<T> extends Dao <T> {
    public ArrayList<Projet> findAllByIdFormateur(int idFormateur) throws SQLException;
    public ArrayList<Projet> findAll(int idPersonne) throws SQLException;
    
}
