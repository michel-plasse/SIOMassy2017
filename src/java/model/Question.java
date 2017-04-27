/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author admin
 */
public class Question {
    
    private int idQuestion;
    private String question;
    private HashMap<Integer,Choix> lesChoix;

    public Question() {
    }

    public Question(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Question(int idQuestion, String question, HashMap<Integer,Choix> lesChoix) {
        this.idQuestion = idQuestion;
        this.question = question;
        this.lesChoix = lesChoix;
    }

    public Question(String question, HashMap<Integer, Choix> lesChoix) {
        this.question = question;
        this.lesChoix = lesChoix;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashMap<Integer,Choix> getLesChoix() {
        return lesChoix;
    }

    public void setLesChoix(HashMap<Integer,Choix> lesChoix) {
        this.lesChoix = lesChoix;
    }
    
    public int getNbBonnesRep(){
        int nbBonnesRep = 0;
        for(Choix c : lesChoix.values()){
            if(c.isEstCorrect()) {
                nbBonnesRep++;
            }
        }
        return nbBonnesRep;
    }
}
