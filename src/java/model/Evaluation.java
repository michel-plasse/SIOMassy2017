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
public class Evaluation {
    private int idEvaluation;
    private int id_session;
    private Date dateDebutEval;
    private int idFormateur;
    private String intitulé;

    public Evaluation() {
    }

    public Evaluation(int id_session, Date dateDebutEval, int idFormateur, String intitulé) {
        this.id_session = id_session;
        this.dateDebutEval = dateDebutEval;
        this.idFormateur = idFormateur;
        this.intitulé = intitulé;
    }
    
    

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
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

    public String getIntitulé() {
        return intitulé;
    }

    public void setIntitulé(String intitulé) {
        this.intitulé = intitulé;
    }
    
    
    
}
