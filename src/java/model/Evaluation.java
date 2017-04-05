/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class Evaluation {
    private int idEvaluation;
    private Date dateDebutEval;
    private String intitule;
    private Module leModule;
    private Personne leFormateur;
    private SessionFormation laSession;

    public Evaluation() {
    }

    public Evaluation(Date dateDebutEval, String intitule, Module leModule, Personne leFormateur, SessionFormation laSession) {
        this.dateDebutEval = dateDebutEval;
        this.intitule = intitule;
        this.leModule = leModule;
        this.leFormateur = leFormateur;
        this.laSession = laSession;
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
