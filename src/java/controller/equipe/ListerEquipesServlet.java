/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.equipe;

import dao.equipe.EquipeDao;
import dao.PersonneDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
import model.Projet;

/**
 *
 * @author ghisfix
 */
@WebServlet(name = "ListerEquipesServlet", urlPatterns = {"/equipe/index"})
public class ListerEquipesServlet extends HttpServlet {

    public static final String PARAM_ID_PROJET = "id";
    public static final String ATT_EQUIPES = "equipes";
    public static final String ATT_IDPROJET = "idProjet";
    public static final String ATT_STAGIAIRES_LIBRES = "stagiaires";
    public static final String VUE_EQUIPES = "/WEB-INF/equipe/index.jsp";
    public static final String ATT_TITLE = "title";
    public static final String ATT_TITLE_VALUE = "Liste des équipes";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String ParamIdProjet = request.getParameter(PARAM_ID_PROJET);
        Personne stagiaireQuiConsulte = (Personne) session.getAttribute("user");
        Integer idProjet = null;

        try {
            idProjet = Integer.parseInt(ParamIdProjet);
        } catch (NumberFormatException e) {
            System.out.println("id projet non valide : " + e);
        }

        if (idProjet != null) {
            ArrayList<Equipe> lesEquipesDuProjet = null;
            ArrayList<Personne> lesStagiairesSansEquipe = null;
            EquipeDao equipeDao = new EquipeDao();
            try {
                lesEquipesDuProjet = equipeDao.findAll(idProjet);
                lesStagiairesSansEquipe = equipeDao.findAllNotInTeam(idProjet);
            } catch (SQLException ex) {
                Logger.getLogger(ListerEquipesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute(ATT_IDPROJET, idProjet);
            request.setAttribute(ATT_EQUIPES, lesEquipesDuProjet);
            request.setAttribute(ATT_STAGIAIRES_LIBRES, lesStagiairesSansEquipe);
            request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);

            this.getServletContext().getRequestDispatcher(VUE_EQUIPES).forward(request, response);
        }else{
            response.sendRedirect(this.getServletContext().getContextPath() + "/espacePersoEtudiant");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
