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
public class Choix {
    
    private int idChoix;
    private String libelle;
    private boolean estCorrect;
    private boolean estChoisi;

    public Choix() {
    }

    public Choix(String choix, boolean estCorrect) {
        this.libelle = choix;
        this.estCorrect = estCorrect;
    }

    
    
    public int getIdChoix() {
        return idChoix;
    }

    public void setIdChoix(int idChoix) {
        this.idChoix = idChoix;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public boolean isEstCorrect() {
        return estCorrect;
    }

    public void setEstCorrect(boolean estCorrect) {
        this.estCorrect = estCorrect;
    }

    public boolean isEstChoisi() {
        return estChoisi;
    }

    public void setEstChoisi(boolean estChoisi) {
        this.estChoisi = estChoisi;
    }
    
    
}
