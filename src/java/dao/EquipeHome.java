/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Personne;
import model.Projet;

/**
 *
 * @author ghis_pcfixe
 * @param <T>
 */

public interface EquipeHome<T> extends Dao<T>{
    
    public boolean ajouterMembre(T object, Personne unePersonne) throws SQLException;
    public boolean retirerMembre(T object, Personne unePersonne) throws SQLException;
    public ArrayList<T> findAll(Projet unProjet) throws SQLException;
    public ArrayList<Personne> findAllNotInTeam (Projet unProjet) throws SQLException; 
}
