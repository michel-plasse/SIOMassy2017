/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersonneDao;
import dao.SessionFormationDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Personne;
import model.SessionFormation;

/**
 *
 * @author nate
 */
@WebServlet(name = "TrombinoscopeServlet", urlPatterns = {"/trombinoscope"})
public class TrombinoscopeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SessionFormationDao dao = new SessionFormationDao();
        try {
            ArrayList<SessionFormation> lesSessions = dao.getSessionsOuvertes();
            request.setAttribute("lesSessions", lesSessions);
        } catch (SQLException e) {
            System.out.println("Probleme recuperation liste session : " + e);
        }

        Integer idSession = null;
        boolean parsable = false;
        if (request.getParameter("idSession") != null) {
            try {
                idSession = Integer.parseInt(request.getParameter("idSession"));
                parsable = true;

            } catch (NumberFormatException e) {
                System.err.println("Erreur id session");
            }

            if (parsable && idSession != null) {

                PersonneDao pdao = new PersonneDao();

                try {

                    ArrayList<Personne> lesPersonnes = pdao.findBySession(idSession);
                    request.setAttribute("lesPersonnes", lesPersonnes);
                } catch (SQLException ex) {
                    Logger.getLogger(TrombinoscopeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("msgerreur", "Problème avec la base de données");
                }

                this.getServletContext().getRequestDispatcher("/WEB-INF/trombinoscope.jsp").forward(request, response);

            }

            this.getServletContext().getRequestDispatcher("/trombinoscope?idSession=1").forward(request, response);

        }

        this.getServletContext().getRequestDispatcher("/trombinoscope?idSession=1").forward(request, response);

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
        HttpSession maSession = request.getSession(true);
        Personne user = (Personne) maSession.getAttribute("user");
        
        if (user == null) {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        } else {
            String[] email = request.getParameterValues("email");
            request.getRequestDispatcher("WEB-INF/emailTrombinoscope.jsp").forward(request, response);
        }

    }

}
