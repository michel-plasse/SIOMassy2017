/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FormateurDao;
import dao.ProjetDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Personne;
import model.Projet;
import model.SessionFormation;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProjetServlet", urlPatterns = {"/creerProjet"})
public class CreerProjetServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Personne user = (Personne) request.getSession(true).getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else if (!user.isEst_formateur()) {
            request.setAttribute("message", "Vous devez être formateur pour consulter cette page");
            request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
        } else {
            request.setAttribute("title", "Créer un projet");
            request.getRequestDispatcher("/WEB-INF/creerProjet.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //verification que le personne est un formateur (recuperation de l'id de formateur
        Personne user = (Personne) request.getSession(true).getAttribute("user");
        user = new Personne();
        //ona precier l'id de formateur 12 pour que quand je teste je dois pas identifier de nouveau
        //user.setId(12);
        //user.setEst_formateur(true);
        if (user == null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else if (!user.isEst_formateur()) {
            request.setAttribute("message", "Vous devez être formateur pour consulter cette page");
            request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
        } else {
            boolean champsrenseignes = true;
            System.out.println("post creerProjet");
            String sidSession = request.getParameter("idSession");
            String sujet = request.getParameter("sujet");
            String sDateLimite = request.getParameter("dateLimite");
            String description = request.getParameter("description");
            Date dateLimite = null;
            int idSession = 0;
            int idFormateur = user.getId();

            if (sidSession == null || sidSession.isEmpty()) {
                champsrenseignes = false;
                request.setAttribute("idSession", "Veuillez selectionner une session de formation.");
            }
            else {
                try {
                    idSession = Integer.parseInt(sidSession);
                    System.out.println("idSession = " + idSession);
                } catch (NumberFormatException exc) {
                    champsrenseignes = false;
                    request.setAttribute("idSession", "La session de formation doit être un entier positif.");
                }

            }
            if (sujet == null || sujet.isEmpty()) {
                champsrenseignes = false;
                request.setAttribute("sujet", "Veuillez entrer le nom de projet..");
                System.out.println("Rentre dans if condition");
            }
            if (description == null || description.isEmpty()) {
                champsrenseignes = false;
                request.setAttribute("description", "Veuillez entrer une description..");
                System.out.println("Rentre dans if condition");
            }
            if (sDateLimite == null || sDateLimite.isEmpty()) {
                champsrenseignes = false;
                request.setAttribute("dateLimite", "Veuillez choisir une date limite..");
                System.out.println("Rentre dans if condition");
            } else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-DD");
                try {
                    dateLimite = df.parse(sDateLimite);
                } catch (ParseException ex) {
                    Logger.getLogger(CreerProjetServlet.class.getName()).log(Level.SEVERE, null, ex);
                    champsrenseignes = false;
                    request.setAttribute("dateLimite", "Veuillez saisir une date valide (aaaa-mm-jj)");
                }
            }
            if (champsrenseignes) {
                try {
                    Date dateCreation = new Date();
                    Projet projetAjoutee = new Projet(0, idSession, idFormateur, sujet, description, dateLimite, dateCreation);
                    ProjetDao dao = new ProjetDao();
                    dao.insert(projetAjoutee);
                    request.setAttribute("message", "Projet créé sous le n° " + projetAjoutee.getId());
                    request.getRequestDispatcher("/WEB-INF/creerProjet.jsp").forward(request, response);
                } catch (SQLException e) {
                    request.setAttribute("message", "Pb avec la base de données");
                    request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
                }

            } else {
                request.getRequestDispatcher("/WEB-INF/creerProjet.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
