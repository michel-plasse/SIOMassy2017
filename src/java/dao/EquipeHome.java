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
    
    public int insertReturnId(T equipe) throws SQLException;
    public boolean ajouterMembre(int idEquipe, int idPeronne) throws SQLException;
    public boolean retirerMembre(int idEquipe, int idPeronne) throws SQLException;
    public ArrayList<T> findAll(Projet unProjet) throws SQLException;
    public ArrayList<Personne> findAllNotInTeam (Projet unProjet) throws SQLException; 
}
