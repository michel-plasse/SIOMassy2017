/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersonneDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Personne;

/**
 *
 * @author admin
 */
@WebServlet(name = "ConfirmerInscription", urlPatterns = {"/ConfirmerInscription"})
public class ConfirmerInscriptionServlet extends HttpServlet {

      protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          PersonneDao pers = new PersonneDao();
          String token = request.getParameter("token");
          String pattern = "(^[0-9]{32}$)";
          Pattern r = Pattern.compile(pattern);
          Matcher m = r.matcher(token);
          if(m.find()){
              try {
                  if(pers.findTokenIsTrue(token)){
                      request.setAttribute("inscriptionValide", "Votre inscription à bien été validée, vous pouvez vous connectez.");
                      request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                      
                  }
              } catch (SQLException ex) {
                  Logger.getLogger(ConfirmerInscriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          else{
            //Demander à plaplasse !!!!!!
            request.setAttribute("messageErreurValidation", "Une erreur s'est produite lors de la confirmation, veuillez entrez a nouveau vos informations.");
            request.getRequestDispatcher("/WEB-INF/inscrire.jsp").forward(request, response);
          }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
