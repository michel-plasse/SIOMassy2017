/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.qcm;

import dao.Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Choix;

/**
 *
 * @author admin
 */
public interface ChoixHome extends Dao <Choix>{
    
    public ArrayList<Choix> findByIdQuestion(int idQuestion)throws SQLException;
    
}
