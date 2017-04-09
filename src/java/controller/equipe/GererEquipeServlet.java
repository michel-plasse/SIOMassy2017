/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.equipe;

import dao.EquipeDao;
import java.io.IOException;
import java.io.PrintWriter;
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
    public static final String PARAM_ID = "id";
    public static final String PARAM_USER = "user";
    public static final String ATT_EQUIPE = "equipeGeree";
    public static final String ATT_STAGIAIRES_LIBRES = "stagiairesSansEquipe";
    public static final String ATT_TITLE = "title";
    public static final String ATT_TITLE_VALUE = "Gérer votre equipe";
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        if(request.getParameter(PARAM_ID) != null && session.getAttribute(PARAM_USER) != null) {
            Integer idEquipe = null;
            try {
                idEquipe = Integer.parseInt(request.getParameter(PARAM_ID));
            }catch (NumberFormatException e) {
                System.out.println("id equipe non valide : " + e);
            }
            
            if(idEquipe != null) {
                Personne createurEquipe = (Personne) session.getAttribute(PARAM_USER);
                EquipeDao equipeDao = new EquipeDao();
                Equipe equipeAdministree = null;
                try {
                    equipeAdministree = equipeDao.findById(idEquipe);
                    System.out.println("récup equipe : ok...");
                } catch (SQLException ex) {
                    Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (equipeAdministree != null) {
                    if(equipeAdministree.getCreateur().getId() == createurEquipe.getId()) {
                        ArrayList<Personne> stagiairesSansEquipe = null;
                        try {
                            stagiairesSansEquipe = equipeDao.findAllNotInTeam(equipeAdministree.getUnProjet());
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
        
        response.sendError(404);
 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        out.print(request.getParameter("modifier"));
        out.print("...");
        out.print(request.getAttribute("manage"));
        out.print("...");
        
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
