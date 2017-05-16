/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author admin
 */
public class Qcm {
    
    private int idQcm;
    private int idModule;
    private String intitule;
    private boolean valide;
    private ArrayList<Question> lesQuestions;
    private String nomModule ;
    private boolean archive;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.idQcm;
        hash = 31 * hash + this.idModule;
        hash = 31 * hash + Objects.hashCode(this.intitule);
        hash = 31 * hash + (this.valide ? 1 : 0);
        hash = 31 * hash + Objects.hashCode(this.lesQuestions);
        hash = 31 * hash + Objects.hashCode(this.nomModule);
        hash = 31 * hash + (this.archive ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Qcm other = (Qcm) obj;
        if (this.idQcm != other.idQcm) {
            return false;
        }
        if (this.idModule != other.idModule) {
            return false;
        }
        if (this.valide != other.valide) {
            return false;
        }
        if (this.archive != other.archive) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        if (!Objects.equals(this.nomModule, other.nomModule)) {
            return false;
        }
        if (!Objects.equals(this.lesQuestions, other.lesQuestions)) {
            return false;
        }
        return true;
    }

    public Qcm() {
    }

    public Qcm(int idQcm, String intitule, boolean valide, ArrayList<Question> lesQuestions) {
        this.idQcm = idQcm;
        this.intitule = intitule;
        this.valide = valide;
        this.lesQuestions = lesQuestions;
    }

    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
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
