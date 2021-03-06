/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Personne;
import outils.ServeurSMTP;

/**
 *
 * @author nate
 */
@WebServlet(name = "TrombinoscopeEmailServlet", urlPatterns = {"/Email"})
public class TrombinoscopeEmailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession maSession = request.getSession(true);
        Personne user = (Personne) maSession.getAttribute("user");
        
        String expediteur = user.getEmail();
        String emailExp = user.getNom()+" "+user.getPrenom()+" "+expediteur;
 
        String destinataire = request.getParameter("destinataire");
        String sujet = request.getParameter("sujet");
        String corps = request.getParameter("email");
        
        InternetAddress unExpediteur = new InternetAddress(expediteur, emailExp);

        try {
                    
            MimeMessage msg = ServeurSMTP.newEmail(expediteur, destinataire, sujet);
            msg.setContent(corps, "text/html; charset=utf-8");
            msg.setFrom(unExpediteur);
            javax.mail.Transport.send(msg);
            
            request.setAttribute("message", "Votre email a bien été envoyé");

        } catch (AuthenticationFailedException ex) {

            request.setAttribute("message", "Authentication failed");
            request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);

        } catch (AddressException ex) {
            request.setAttribute("message", "Wrong email address");
            request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);

        } catch (MessagingException ex) {
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
        }
        
        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
