/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EquipeDao;
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
import model.Equipe;
import model.Personne;
import model.Projet;

/**
 *
 * @author ghisfix
 */
@WebServlet(name = "ListerEquipesServlet", urlPatterns = {"/equipes"})
public class ListerEquipesServlet extends HttpServlet {
    public static final String PARAM_ID_PROJET = "id_projet";
    public static final String ATT_EQUIPES = "equipes";
    public static final String VUE_EQUIPES = "/WEB-INF/listedesequipes.jsp";
    public static final String ATT_TITLE = "title";
    public static final String ATT_TITLE_VALUE = "Liste des équipes";
    

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
            out.println("<title>Servlet ListerEquipesServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListerEquipesServlet at " + request.getContextPath() + "</h1>");
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
        
        String ParamIdProjet = request.getParameter(PARAM_ID_PROJET);
        Boolean isParsable = false;
        Integer idProjet = null;
        
        try {
            idProjet = Integer.parseInt(ParamIdProjet);
            isParsable = true;
        }catch(NumberFormatException e) {
            System.out.println("id projet non valide : "+e);
        }
        
        if(idProjet != null && isParsable) {
            ArrayList<Equipe> lesEquipesDuProjet = null;
            //en attendant projetdao***
            Projet projetConsulte = new Projet(idProjet);
            //***
            EquipeDao equipeDao = new EquipeDao();
            try {
                lesEquipesDuProjet = equipeDao.findAll(projetConsulte);
            } catch (SQLException ex) {
                Logger.getLogger(ListerEquipesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute(ATT_EQUIPES, lesEquipesDuProjet);
            request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
            
            //check si les infos sont bien récupérées
            
//            for(Equipe equipe: lesEquipesDuProjet) {
//                Collection<Personne> col = equipe.getLesMembres().values();
//                for(Personne personne : col) {
//                System.out.println(personne.getEmail());
//                }
//            }
            
            this.getServletContext().getRequestDispatcher(VUE_EQUIPES).forward(request, response);
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
