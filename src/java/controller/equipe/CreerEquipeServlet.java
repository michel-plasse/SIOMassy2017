/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.equipe;

import dao.equipe.EquipeDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Equipe;
import model.Personne;

/**
 *
 * @author ghisfix
 */
@WebServlet(name = "CreerEquipeServlet", urlPatterns = {"/equipe/creer"})
public class CreerEquipeServlet extends HttpServlet {

    public static final String VUE = "/WEB-INF/equipe/creer.jsp";
    public static final String ATT_TITLE = "title";
    public static final String ATT_TITLE_VALUE = "Création d'une équipe";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Personne user = (Personne) session.getAttribute("user");
        String idProjetParam = request.getParameter("id");
        int idProjet = -1;
        try {
            idProjet = Integer.parseInt(idProjetParam);
        } catch (NumberFormatException e) {
            System.err.println("erreur id projet");
        }
        if (idProjet != -1) {
            EquipeDao equipeDao = new EquipeDao();
            int idEquipe = -1;
            try {
                idEquipe = equipeDao.isAlreadyInTeam(user.getId(), idProjet);
            } catch (SQLException ex) {
                Logger.getLogger(CreerEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (idEquipe == -1) {
                ArrayList<Personne> stagiairesSansEquipe = null;
                try {
                    stagiairesSansEquipe = equipeDao.findAllNotInTeam(idProjet);
                } catch (SQLException ex) {
                    Logger.getLogger(CreerEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("stagiairesSansEquipe", stagiairesSansEquipe);
                request.setAttribute("idProjet", idProjet);
                request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
                request.getServletContext().getRequestDispatcher(VUE).forward(request, response);

            } else {
                response.sendRedirect(this.getServletContext().getContextPath() + "/equipe/details?id=" + idEquipe);
            }

        } else {
            response.sendRedirect(this.getServletContext().getContextPath() + "/espacePersoEtudiant");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Personne user = (Personne) session.getAttribute("user");
        String idProjetParam = request.getParameter("id");
        int idProjet = -1;
        try {
            idProjet = Integer.parseInt(idProjetParam);
        } catch (NumberFormatException e) {
            System.err.println("erreur id projet");
        }
        if (idProjet != -1) {
            EquipeDao equipeDao = new EquipeDao();
            int idEquipe = -1;
            try {
                idEquipe = equipeDao.isAlreadyInTeam(user.getId(), idProjet);
            } catch (SQLException ex) {
                Logger.getLogger(CreerEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (idEquipe == -1) {
                Equipe nouvelleEquipe = new Equipe();
                nouvelleEquipe.setIdProjet(idProjet);
                nouvelleEquipe.setCreateur(user);
                nouvelleEquipe.setLesMembres(new HashMap<>());
                String[] ajouts = request.getParameterValues("add");
                
                if (ajouts != null) {
                    for (String ajout : ajouts) {
                        Personne p = new Personne();
                        p.setId(Integer.parseInt(ajout));
                        nouvelleEquipe.ajouterUnMembre(p);
                    }
                }
                
                try {
                    equipeDao.insert(nouvelleEquipe);
                    session.setAttribute("messageOk", "Equipe créée avec succès.");
                } catch (SQLException ex) {
                    Logger.getLogger(CreerEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    session.setAttribute("messageError", "Problème rencontré lors de la création de l'équipe");
                }
                response.sendRedirect(this.getServletContext().getContextPath() + "/equipe/index?id=" + idProjet);

            } else {
                response.sendRedirect(this.getServletContext().getContextPath() + "/equipe/details?id=" + idEquipe);
            }

        } else {
            response.sendRedirect(this.getServletContext().getContextPath() + "/espacePersoEtudiant");
        }

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
