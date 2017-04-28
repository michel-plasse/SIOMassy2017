/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Qcm {
    
    private int idQcm;
    private String intitule;
    private boolean valide;
    private ArrayList<Question> lesQuestions;
    private String nomModule ;
    private boolean archive;

    public Qcm() {
    }

    public Qcm(int idQcm, String intitule, boolean valide, ArrayList<Question> lesQuestions) {
        this.idQcm = idQcm;
        this.intitule = intitule;
        this.valide = valide;
        this.lesQuestions = lesQuestions;
    }

    public Qcm(int idQcm, String intitule, boolean valide, String nomModule) {
        this.idQcm = idQcm;
        this.intitule = intitule;
        this.valide = valide;
        this.nomModule = nomModule;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }
    
    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }
    

    public int getIdQcm() {
        return idQcm;
    }

    public void setIdQcm(int idQcm) {
        this.idQcm = idQcm;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public ArrayList<Question> getLesQuestions() {
        return lesQuestions;
    }

    public void setLesQuestions(ArrayList<Question> lesQuestions) {
        this.lesQuestions = lesQuestions;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
    
    

}
