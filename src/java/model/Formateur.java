/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Xavier Claude PASSER
 */
public class Formateur extends Personne {
    
    private String matiereEnseignee;
    
    public Formateur(int id, String nom, String prenom, String email, String no_rue, String nom_rue, String code_postal, String ville, String pays, String password, String matiereEnseignee) {
        super(id, nom, prenom, email, no_rue, nom_rue, code_postal, ville, pays, password);
        this.matiereEnseignee = matiereEnseignee;
    }
    
    public Formateur(String nom, String prenom, String matiereEnseignee) {
        super(nom, prenom);
        this.matiereEnseignee = matiereEnseignee;
    }
    
    public Formateur() {
        
    }

    public String getMatiereEnseignee() {
        return matiereEnseignee;
    }

    public void setMatiereEnseignee(String matiereEnseignee) {
        this.matiereEnseignee = matiereEnseignee;
    }
    
    
    
    
}
