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
public class Note {
    private int idNote;
    private int idEvaluation;
    private int idEleve;
    private double valeurNote;
    private String commentaire;

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public int getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(int idEleve) {
        this.idEleve = idEleve;
    }

    public double getValeurNote() {
        return valeurNote;
    }

    public void setValeurNote(double valeurNote) {
        this.valeurNote = valeurNote;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Note(int idNote, int idEvaluation, int idEleve, double valeurNote, String commentaire) {
        this.idNote = idNote;
        this.idEvaluation = idEvaluation;
        this.idEleve = idEleve;
        this.valeurNote = valeurNote;
        this.commentaire = commentaire;
    }

    public Note() {
    }
    
}
