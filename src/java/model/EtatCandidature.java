/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author AveigA
 */
public class EtatCandidature {
    private int idEtatCandidature;
    private String libelle;

    public EtatCandidature(int idEtatCandidature, String libelle) {
        this.idEtatCandidature = idEtatCandidature;
        this.libelle = libelle;
    }

    public int getIdEtatCandidature() {
        return idEtatCandidature;
    }

    public void setIdEtatCandidature(int idEtatCandidature) {
        this.idEtatCandidature = idEtatCandidature;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
}
