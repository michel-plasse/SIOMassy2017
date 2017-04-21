/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjetDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Projet;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProjetServlet", urlPatterns = {"/ProjetServlet"})
public class ProjetServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProjetServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProjetServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
       
            request.getRequestDispatcher("/WEB-INF/projet.jsp").forward(request, response);

        }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        boolean champsrenseignes = true;
        
        String sessionFormation  = request.getParameter("sessionFormation");
        String sujet = request.getParameter("sujet");
        String dateLimite = request.getParameter("dateLimite");
        String dateCreation = request.getParameter("dateCreation");
        String dateFin = request.getParameter("dateCreation");
        String description = request.getParameter("ameliorer");
        
        
        
        
        if (sessionFormation.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("sessionFormation", "Veuillez selectionner une session de formation.");
            System.out.println("Rentre dans if condition");
        }
        if (sujet.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("sujet", "Veuillez entrer le nom de projet..");
            System.out.println("Rentre dans if condition");
        }
        if (dateLimite.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("dateLimite", "Veuillez choisir une date limite..");
            System.out.println("Rentre dans if condition");
        }
        if (dateCreation.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("dateCreation", "Veuillez choisir une date de creation .");
            System.out.println("Rentre dans if condition");
        }
        if (dateFin.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("dateFin", "Veuillez choisir une date de fin .");
            System.out.println("Rentre dans if condition");
        }
            if (champsrenseignes) {
            try {
                Projet projetAjoutee = new Projet(0, sessionFormation, sujet, dateLimite, dateCreation, dateFin,description);
                ProjetDao dao = new ProjetDao();
                dao.insert(projetAjoutee);
                request.getRequestDispatcher("/WEB-INF/projet.jsp").forward(request, response);
           } catch (SQLException e) {
              request.setAttribute("message", "Pb avec la base de données");
              request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
           }

    }
             
          
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
