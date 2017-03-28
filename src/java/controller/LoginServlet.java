/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersonneDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Personne;

/**
 *
 * @author nate
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pessimiste : on suppose saisie incorrecte
        String vue = "WEB-INF/login.jsp";
        boolean champsrenseignes = true;

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("loginMsg", "Veuillez entrer votre identifiant.");
        }

        if (password.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("passwordMsg", "Veuillez entrer votre mot de passe.");
        }

        PersonneDao dao = new PersonneDao();
        if (champsrenseignes) {
            System.out.println("champ renseignes");
            try {
                System.out.println("login " + login);
                System.out.println("password " + password);
                Personne personne = dao.getByLoginPassword(login, password);
                if (personne != null) {
                    HttpSession maSession = request.getSession();
                    maSession.setAttribute("user", personne);
                    vue = "WEB-INF/connecte.jsp";
                }
                else {
                    request.setAttribute("passwordMsg", "Utilisateur inconnu ou mot de passe invalide");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("loginMsg", ex.getMessage());
            }
        }
        request.getRequestDispatcher(vue).forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
