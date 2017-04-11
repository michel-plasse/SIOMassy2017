/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Option;

/**
 *
 * @author admin
 */
public interface OptionHome extends Dao <Option>{
    
    public ArrayList<Option> findByIdQuestion(int idQuestion)throws SQLException;
    
}
