package model;

import java.sql.Date;
import java.util.HashMap;

public class Equipe {
    private Integer id;
    private Personne createur;
    private Projet unProjet;
    private HashMap<Integer, Personne> lesMembres;
    private Date dateCreation;

    public Equipe() {
    }

    public Equipe(int id, Personne createur, Projet unProjet, HashMap<Integer, Personne> lesMembres) {
        this.id = id;
        this.createur = createur;
        this.unProjet = unProjet;
        this.lesMembres = lesMembres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personne getCreateur() {
        return createur;
    }

    public void setCreateur(Personne createur) {
        this.createur = createur;
    }

    public Projet getUnProjet() {
        return unProjet;
    }

    public void setUnProjet(Projet unProjet) {
        this.unProjet = unProjet;
    }

    public HashMap<Integer, Personne> getLesMembres() {
        return lesMembres;
    }

    public void setLesMembres(HashMap<Integer, Personne> lesMembres) {
        this.lesMembres = lesMembres;
    }
    
    public void ajouterUnMembre(Personne unePersonne) {
        lesMembres.put(unePersonne.getId(), unePersonne);
    }
    
    public void retirerUnMembre(Personne unePersonne) {
        lesMembres.remove(unePersonne.getId());
    }
    
    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", createur=" + createur + ", unProjet=" + unProjet + ", dateCreation=" + dateCreation + '}';
    }

    
}
