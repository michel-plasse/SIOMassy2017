/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

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

    public Choix(int idChoix, String libelle, boolean estCorrect) {
        this.idChoix = idChoix;
        this.libelle = libelle;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idChoix;
        hash = 59 * hash + Objects.hashCode(this.libelle);
        hash = 59 * hash + (this.estCorrect ? 1 : 0);
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
        final Choix other = (Choix) obj;
        if (this.idChoix != other.idChoix) {
            return false;
        }
        if (this.estCorrect != other.estCorrect) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }
    
    
}
