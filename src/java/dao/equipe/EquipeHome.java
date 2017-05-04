/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.equipe;

import dao.Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Equipe;
import model.Personne;
import model.Projet;

/**
 *
 * @author ghis_pcfixe
 * @param <T>
 */

public interface EquipeHome<T> extends Dao<T>{
    
    public int insertReturnId(int idProjet, T equipe) throws SQLException;
    public boolean ajouterMembre(int idEquipe, int idPeronne) throws SQLException;
    public boolean retirerMembre(int idEquipe, int idPeronne) throws SQLException;
    public int isAlreadyInTeam(int idPersonne, int idProjet) throws SQLException;
    public ArrayList<T> findAll(int idProjet) throws SQLException;
    public ArrayList<Personne> findAllNotInTeam (int idProjet) throws SQLException; 
}
