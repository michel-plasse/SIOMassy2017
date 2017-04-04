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
public class Option {
    
    private int idOption;
    private int idQuestion;
    private String option;
    private boolean estCorrecte;

    public Option() {
    }

    public Option(int idQuestion, String option, boolean estCorrecte) {
        this.idQuestion = idQuestion;
        this.option = option;
        this.estCorrecte = estCorrecte;
    }

    
    
    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isEstCorrecte() {
        return estCorrecte;
    }

    public void setEstCorrecte(boolean estCorrecte) {
        this.estCorrecte = estCorrecte;
    }
    
    
}
