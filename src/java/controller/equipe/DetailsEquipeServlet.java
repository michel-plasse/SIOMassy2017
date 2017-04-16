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
import model.Equipe;

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
        
        String ParamIdEquipe = request.getParameter(PARAM_ID_EQUIPE);
        Integer idEquipe = null;
        
        try {
            idEquipe = Integer.parseInt(ParamIdEquipe);
        }catch(NumberFormatException e) {
            System.out.println("id equipe non valide : "+e);
        }
        
        
        
        if(idEquipe != null) {
            EquipeDao equipeDao = new EquipeDao();
            Equipe DetailEquipe = null;
            
            try {
                DetailEquipe = equipeDao.findById(idEquipe);
            } catch (SQLException ex) {
                Logger.getLogger(ListerEquipesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute(ATT_EQUIPE, DetailEquipe);
            request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
            
            
            this.getServletContext().getRequestDispatcher(VUE_EQUIPE).forward(request, response);
        }
        
        response.sendError(404, "Erreur, votre requête ne peut aboutir.");
        
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
