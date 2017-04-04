/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class Evaluation {
    private int idEvaluation;
    private int idModule;
    private int idSession;
    private Date dateDebutEval;
    private int idFormateur;
    private String intitule;
    private String nom_module;
    private String nom_formateur;
    private String nom_formation;
    private double note;
    private String commentaire;

    public Evaluation() {
    }

    public Evaluation(int idModule, int idFormateur, int idSession, String intitule, Date dateDebutEval) {
        this.idModule = idModule;
        this.idFormateur = idFormateur;
        this.idSession = idSession;
        this.intitule = intitule;
        this.dateDebutEval = dateDebutEval;  
    }

    public Evaluation(Date dateDebutEval, String intitule, String nom_module, String nom_formateur,double note, String commentaire) {
        this.dateDebutEval = dateDebutEval;
        this.intitule = intitule;
        this.nom_module = nom_module;
        this.nom_formateur = nom_formateur;
        this.note = note;
        this.commentaire = commentaire;
    }

    public Evaluation(int idEvaluation, Date dateDebutEval, String intitule, String nom_module, String nom_formation) {
        this.idEvaluation = idEvaluation;
        this.dateDebutEval = dateDebutEval;
        this.intitule = intitule;
        this.nom_module = nom_module;
        this.nom_formation = nom_formation;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNom_formation() {
        return nom_formation;
    }

    public void setNom_formation(String nom_formation) {
        this.nom_formation = nom_formation;
    }
    
    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public Date getDateDebutEval() {
        return dateDebutEval;
    }

    public void setDateDebutEval(Date dateDebutEval) {
        this.dateDebutEval = dateDebutEval;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    public String getNom_module() {
        return nom_module;
    }

    public void setNom_module(String nom_module) {
        this.nom_module = nom_module;
    }

    public String getNom_formateur() {
        return nom_formateur;
    }

    public void setNom_formateur(String nom_formateur) {
        this.nom_formateur = nom_formateur;
    }

}
