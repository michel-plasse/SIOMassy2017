/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Personne;

/**
 *
 * @author ghis_pcfixe
 * @param <T>
 */

public interface EquipeHome<T> extends Dao<T>{
    
    public boolean ajouterMembre(T object, Personne unePersonne) throws SQLException;
    public boolean retirerMembre(T object, Personne unePersonne) throws SQLException;

    
}
