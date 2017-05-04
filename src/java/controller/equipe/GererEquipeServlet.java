/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.equipe;

import dao.equipe.EquipeDao;
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
import model.Equipe;
import model.Personne;

/**
 *
 * @author ghisfix
 */
@WebServlet(name = "GererEquipeServlet", urlPatterns = {"/equipe/gerer"})
public class GererEquipeServlet extends HttpServlet {

    public static final String VUE = "/WEB-INF/equipe/gerer.jsp";
    public static final String PARAM_ID_EQUIPE = "id";
    public static final String ATT_EQUIPE = "equipeGeree";
    public static final String ATT_STAGIAIRES_LIBRES = "stagiairesSansEquipe";
    public static final String ATT_TITLE = "title";
    public static final String ATT_TITLE_VALUE = "Gérer votre equipe";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Personne createurEquipe = (Personne) session.getAttribute("user");

        if (request.getParameter(PARAM_ID_EQUIPE) != null) {
            Integer idEquipe = null;
            try {
                idEquipe = Integer.parseInt(request.getParameter(PARAM_ID_EQUIPE));
            } catch (NumberFormatException e) {
                System.out.println("id equipe non valide : " + e);
            }

            if (idEquipe != null) {
                EquipeDao equipeDao = new EquipeDao();
                Equipe equipeAdministree = null;

                try {
                    equipeAdministree = equipeDao.findById(idEquipe);
                } catch (SQLException ex) {
                    Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (equipeAdministree != null) {
                    if (equipeAdministree.getCreateur().getId() == createurEquipe.getId()) {
                        ArrayList<Personne> stagiairesSansEquipe = null;

                        try {
                            stagiairesSansEquipe = equipeDao.findAllNotInTeam(equipeAdministree.getIdProjet());
                        } catch (SQLException ex) {
                            Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        request.setAttribute(ATT_EQUIPE, equipeAdministree);
                        request.setAttribute(ATT_STAGIAIRES_LIBRES, stagiairesSansEquipe);
                        request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);

                        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

                    }

                }

            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Personne createur = (Personne) session.getAttribute("user");

        if (request.getParameter(PARAM_ID_EQUIPE) != null) {
            Integer idEquipe = null;
            try {
                idEquipe = Integer.parseInt(request.getParameter(PARAM_ID_EQUIPE));
            } catch (NumberFormatException e) {
                System.out.println("id equipe non valide : " + e);
            }

            if (idEquipe != null) {
                EquipeDao equipeDao = new EquipeDao();
                Equipe equipe = null;

                try {
                    equipe = equipeDao.findById(idEquipe);
                } catch (SQLException ex) {
                    Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (equipe != null) {

                    if (equipe.getCreateur().getId() == createur.getId()) {

                        if (request.getParameter("modifier") != null) {
                            String[] ajouts = request.getParameterValues("add");
                            String[] suppressions = request.getParameterValues("delete");

                            try {
                                if (ajouts != null) {
                                    for (String ajout : ajouts) {
                                        equipeDao.ajouterMembre(idEquipe, Integer.parseInt(ajout));
                                    }
                                }
                                if (suppressions != null) {
                                    for (String suppression : suppressions) {
                                        equipeDao.retirerMembre(idEquipe, Integer.parseInt(suppression));
                                    }
                                }
                                session.setAttribute("messageOk", "Modification enregistrée avec succès..");
                            } catch (NumberFormatException | SQLException ex) {
                                Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                                session.setAttribute("messageError", "Problème rencontré, les modifications n'ont pas été prise en compte..");
                            }

                            response.sendRedirect(this.getServletContext().getContextPath() + "/equipe/gerer?id=" + idEquipe);
                        }

                        if (request.getParameter("supprimer") != null) {
                            try {
                                equipeDao.delete(idEquipe);
                                session.setAttribute("messageOk", "Votre équipe a été supprimée avec succès");
                            } catch (SQLException ex) {
                                session.setAttribute("messageError", "Problème rencontré lors de la suppression de l'équipe");
                                Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            String idProjet = request.getParameter("idProjet");
                            response.sendRedirect(this.getServletContext().getContextPath() + "/equipe/index?id=" + idProjet);
                        }

                    } else {
                        response.sendError(403, "problème, il ne s'agit pas de votre équipe");
                    }
                } else {
                    response.sendRedirect(this.getServletContext().getContextPath() + "/espacePersoEtudiant");
                }
            } else {
                response.sendRedirect(this.getServletContext().getContextPath() + "/espacePersoEtudiant");
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
