package model;

import java.sql.Date;
import java.util.HashMap;

public class Equipe {
    private Integer id;
    private Integer idProjet;
    private Personne createur;
    private HashMap<Integer, Personne> lesMembres;
    private Date dateCreation;

    public Equipe() {
    }

    public Equipe(Integer id, Personne createur,Date dateCreation) {
        this.id = id;
        this.createur = createur;
        this.dateCreation = dateCreation;
    }
 
    public Equipe(Integer id, Personne createur, HashMap<Integer, Personne> lesMembres) {
        this.id = id;
        this.createur = createur;
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

    public Integer getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(Integer idProjet) {
        this.idProjet = idProjet;
    }


    @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", createur=" + createur + ", lesMembres=" + lesMembres + ", dateCreation=" + dateCreation + '}';
    }

}
