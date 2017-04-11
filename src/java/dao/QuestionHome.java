/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Question;

/**
 *
 * @author admin
 */
public interface QuestionHome<T> extends Dao<T>{
    
    public void insert(int id, T nouvelleQuestion) throws SQLException;
}
