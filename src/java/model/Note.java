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
public class Note{
    private int id_note;
    private Evaluation uneEvaluation;
    private Personne laPersonne;
    private double note;
    private String commentaire;

    public int getId_note() {
        return id_note;
    }

    public double getNote() {
        return note;
    }

    public Evaluation getUneEvaluation() {
        return uneEvaluation;
    }

    public void setUneEvaluation(Evaluation uneEvaluation) {
        this.uneEvaluation = uneEvaluation;
    }

    public Personne getLaPersonne() {
        return laPersonne;
    }

    public void setLaPersonne(Personne laPersonne) {
        this.laPersonne = laPersonne;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Note(int id_note, double note, String commentaire) {
        this.id_note = id_note;
        this.note = note;
        this.commentaire = commentaire;
    }

    public Note() {
    }
    
}
