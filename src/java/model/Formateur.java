/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Xavier Claude PASSER
 */
public class Formateur extends Personne {
    
    public Formateur(int id, String nom, String prenom, String email, String no_rue, String nom_rue, String code_postal, String ville, String pays, String password, String no_tel, String photo) {
        super(id, nom, prenom, email, no_rue, nom_rue, code_postal, ville, pays, password, no_tel, photo);
    }
    
    public Formateur(String nom, String prenom) {
        super(nom, prenom);
    }
    
    public Formateur() {
        
    }
       public Formateur(int id,String nom, String prenom) {
        super(id, nom, prenom);
    }
    


}
