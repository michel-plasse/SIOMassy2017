/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Projet {
    private int id;
    private int idSession;
     private int idFormateur;
     private String nomFormateur;
     private String prenomFormateur;
    
    private String sujet;
    private String description;
    private Date dateLimite;
    private Date dateCreation;

    public Projet(int id, int idSession, int idFormateur, String nomFormateur, String prenomFormateur, String sujet, String description, Date dateLimite, Date dateCreation) {
        this.id = id;
        this.idSession = idSession;
        this.idFormateur = idFormateur;
        this.nomFormateur = nomFormateur;
        this.prenomFormateur = prenomFormateur;
        this.sujet = sujet;
        this.description = description;
        this.dateLimite = dateLimite;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public String getNomFormateur() {
        return nomFormateur;
    }

    public void setNomFormateur(String nomFormateur) {
        this.nomFormateur = nomFormateur;
    }

    public String getPrenomFormateur() {
        return prenomFormateur;
    }

    public void setPrenomFormateur(String prenomFormateur) {
        this.prenomFormateur = prenomFormateur;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

 

    public Projet(int id, String string, String string0, String string1, java.sql.Date date, java.sql.Date date0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
} 

    @Override
    public String toString() {
        return "Projet{" + "id=" + id + ", idSession=" + idSession + ", idFormateur=" + idFormateur +", nomFormateur=" + nomFormateur +", prenomFormateur=" + prenomFormateur + ", sujet=" + sujet + ", description=" + description + ", dateLimite=" + dateLimite + ", dateCreation=" + dateCreation + '}';
    }
    

}