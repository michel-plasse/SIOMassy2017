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
public class Question {
    
    private int idQuestion;
    private String question;
    private ArrayList<Option> lesOptions;

    public Question() {
    }

    public Question(int idQuestion, String question, ArrayList<Option> lesOptions) {
        this.idQuestion = idQuestion;
        this.question = question;
        this.lesOptions = lesOptions;
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

    public ArrayList<Option> getLesOptions() {
        return lesOptions;
    }

    public void setLesOptions(ArrayList<Option> lesOptions) {
        this.lesOptions = lesOptions;
    }
    
}
