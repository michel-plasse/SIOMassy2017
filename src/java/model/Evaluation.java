/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

public class Evaluation {
    private int idEvaluation;
    private Module leModule;
    private Personne leFormateur;
    private SessionFormation laSession;
    private Date dateDebutEval;
    private String intitule;
    

    public Evaluation() {
    }

    public Evaluation(int idEvaluation, Module leModule, Personne leFormateur, Date dateDebutEval, String intitule) {
        this.idEvaluation = idEvaluation;
        this.leModule = leModule;
        this.leFormateur = leFormateur;
        this.dateDebutEval = dateDebutEval;
        this.intitule = intitule;
    }
    
    
    public Evaluation(Module leModule, Personne leFormateur, SessionFormation laSession, Date dateDebutEval, String intitule) {
        this.leModule = leModule;
        this.leFormateur = leFormateur;
        this.laSession = laSession;
        this.dateDebutEval = dateDebutEval;
        this.intitule = intitule;
    }

    public Evaluation(int idEvaluation, Module leModule, Personne leFormateur, SessionFormation laSession, Date dateDebutEval, String intitule) {
        this.idEvaluation = idEvaluation;
        this.leModule = leModule;
        this.leFormateur = leFormateur;
        this.laSession = laSession;
        this.dateDebutEval = dateDebutEval;
        this.intitule = intitule;
    }

    public SessionFormation getLaSession() {
        return laSession;
    }

    public void setLaSession(SessionFormation laSession) {
        this.laSession = laSession;
    }
    
    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public Date getDateDebutEval() {
        return dateDebutEval;
    }

    public void setDateDebutEval(Date dateDebutEval) {
        this.dateDebutEval = dateDebutEval;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Module getLeModule() {
        return leModule;
    }

    public void setLeModule(Module leModule) {
        this.leModule = leModule;
    }

    public Personne getLeFormateur() {
        return leFormateur;
    }

    public void setLeFormateur(Personne leFormateur) {
        this.leFormateur = leFormateur;
    }

}
