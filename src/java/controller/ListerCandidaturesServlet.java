/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CandidatureDao;
import dao.EtatCandidatureDao;
import dao.FormationDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EtatCandidature;
import model.Formation;

/**
 *
 * @author AveigA
 */
@WebServlet("/candidatures")
public class ListerCandidaturesServlet extends HttpServlet {

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
            out.println("<title>Servlet listeCandidatureServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listeCandidatureServlet at " + request.getContextPath() + "</h1>");
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
        
        ArrayList<String> conditions = new ArrayList<>();
        
        
        //RECHERCHE
        if(request.getParameter("recherche") != null && !request.getParameter("recherche").isEmpty()){
            String recherche = request.getParameter("recherche");
            String condition = "p.nom LIKE '%" + recherche + "%' OR p.prenom LIKE '%" + recherche + "%'";
            conditions.add(condition);
        }
        //STATUT
        if(request.getParameter("statut") != null && !request.getParameter("statut").isEmpty()){
            String statut = request.getParameter("statut");
            String condition = "e.libelle = '" + statut + "'";
            conditions.add(condition);
        }
        //SESSION FORMATION
        if(request.getParameter("formationNom") != null && !request.getParameter("formationNom").isEmpty()){            
            String sessionFormation = request.getParameter("formationNom");
            String condition = "f.nom = '" + sessionFormation + "'";
            conditions.add(condition);
        }        
        //DATE
        if(request.getParameter("date") != null && !request.getParameter("date").isEmpty()){            
            String date = request.getParameter("date");
            String condition = "DATE_FORMAT(c.date_effet, '%d/%m/%Y') = '" + date + "'";
            conditions.add(condition);
        }
        
        try {            
            CandidatureDao candidatureDao = new CandidatureDao();
            ArrayList<HashMap<String, String>> lesCandidatures = candidatureDao.mapCandidatures(conditions);
            request.setAttribute("lesCandidatures", lesCandidatures);
            
            EtatCandidatureDao etatCandidatureDao = new EtatCandidatureDao();
            ArrayList<EtatCandidature> lesEtats = etatCandidatureDao.findAll();
            request.setAttribute("lesEtats", lesEtats);
            
            FormationDao formationDao = new FormationDao();
            ArrayList<Formation> lesFormations = formationDao.findAll();
            request.setAttribute("lesFormations", lesFormations);
            
            request.getRequestDispatcher("WEB-INF/candidatures.jsp").forward(request, response);

        } catch (SQLException ex) {
            request.setAttribute("message", "Pb avec la base de données");
            request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);        
        }

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
