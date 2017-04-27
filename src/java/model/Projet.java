/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author admin
 */
public class Projet {
    private int id;
    private String sessionFormation;
    private String sujet;
    private String description;
    private String dateLimite;
    private String dateCreation;

    public Projet(int id, String sessionFormation, String sujet, String description, String dateLimite, String dateCreation) {
        this.id = id;
        this.sessionFormation = sessionFormation;
        this.sujet = sujet;
        this.description = description;
        this.dateLimite = dateLimite;
        this.dateCreation = dateCreation;
    }

    public Projet(int i, String sessionFormation, String sujet, String dateLimite, String dateCreation, String dateFin, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionFormation() {
        return sessionFormation;
    }

    public void setSessionFormation(String sessionFormation) {
        this.sessionFormation = sessionFormation;
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

    public String getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(String dateLimite) {
        this.dateLimite = dateLimite;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

  


    
}
