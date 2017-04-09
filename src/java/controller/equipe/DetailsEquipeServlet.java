/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.equipe;

import controller.equipe.ListerEquipesServlet;
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
import model.Equipe;
import model.Personne;
import model.Projet;

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
            out.println("<title>Servlet VotreEquipeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VotreEquipeServlet at " + request.getContextPath() + "</h1>");
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
        
        String ParamIdEquipe = request.getParameter(PARAM_ID_EQUIPE);
        Boolean isParsable = false;
        Integer idEquipe = null;
        
        try {
            idEquipe = Integer.parseInt(ParamIdEquipe);
            isParsable = true;
        }catch(NumberFormatException e) {
            System.out.println("id equipe non valide : "+e);
        }
        
        
        
        if(idEquipe != null && isParsable) {
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
        processRequest(request, response);
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
