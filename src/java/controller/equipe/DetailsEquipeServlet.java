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
 * @author admin
 */
@WebServlet(name = "VotreEquipeServlet", urlPatterns = {"/equipe/details"})
public class DetailsEquipeServlet extends HttpServlet {

    public static final String PARAM_ID_EQUIPE = "id";
    public static final String ATT_EQUIPE = "equipe";
    public static final String VUE_EQUIPE = "/WEB-INF/equipe/details.jsp";
    public static final String ATT_TITLE = "title";
    public static final String ATT_TITLE_VALUE = "Votre Equipe";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Personne user = (Personne) session.getAttribute("user");

        String ParamIdEquipe = request.getParameter(PARAM_ID_EQUIPE);
        Integer idEquipe = null;

        try {
            idEquipe = Integer.parseInt(ParamIdEquipe);
        } catch (NumberFormatException e) {
            System.out.println("id equipe non valide : " + e);
        }

        if (idEquipe != null) {
            EquipeDao equipeDao = new EquipeDao();
            Equipe DetailEquipe = null;

            try {
                DetailEquipe = equipeDao.findById(idEquipe);
            } catch (SQLException ex) {
                Logger.getLogger(ListerEquipesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (DetailEquipe != null) {
                if (user.getId() == DetailEquipe.getCreateur().getId() || DetailEquipe.getLesMembres().containsKey(user.getId())) {
                    request.setAttribute(ATT_EQUIPE, DetailEquipe);
                    request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
                    this.getServletContext().getRequestDispatcher(VUE_EQUIPE).forward(request, response);
                } else {
                    response.sendError(403);
                }
            } else {
                response.sendRedirect(this.getServletContext().getContextPath() + "/espacePersoEtudiant");
            }
        } else {
            response.sendRedirect(this.getServletContext().getContextPath() + "/espacePersoEtudiant");
        }
    }

    @Override
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
