/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.TokenGenerator;


/**
 *
 * @author admin
 */
public class Pg {
    public static void main(String[] args){
        TokenGenerator machin = new TokenGenerator();
        System.out.println(machin.nextSessionId());
        
    }
    
}
