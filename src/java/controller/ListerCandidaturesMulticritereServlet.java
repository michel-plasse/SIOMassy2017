/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CandidatureDao;
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

/**
 *
 * @author AveigA
 */
@WebServlet("/candidatures/multicriteres")
public class ListerCandidaturesMulticritereServlet extends HttpServlet {

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
            out.println("<title>Servlet ListerCandidaturesMulticritereServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListerCandidaturesMulticritereServlet at " + request.getContextPath() + "</h1>");
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
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String statut = request.getParameter("statut");
        String sessionFormation = request.getParameter("formationNom");
        String datePostule = request.getParameter("dateDePostulation");
        
        ArrayList<String> conditions = new ArrayList<>();
        
        //NOM
        if(!nom.isEmpty()){
            String condition = "p.nom LIKE '" + nom + "'";
            conditions.add(condition);
        }
        //PRENOM
        if(!prenom.isEmpty()){
            String condition = "p.prenom LIKE '" + prenom + "'";
            conditions.add(condition);
        }
        //STATUT
        if(!statut.isEmpty()){
            String condition = "e.libelle LIKE '" + statut + "'";
            conditions.add(condition);
        }
        //SESSION FORMATION
        if(!sessionFormation.isEmpty()){
            String condition = "f.nom LIKE '" + sessionFormation + "'";
            conditions.add(condition);
        }
        //DATE POSTULE
        if(!datePostule.isEmpty()){
            String condition = "c.date_effectue LIKE '" + datePostule + "'";
            conditions.add(condition);
        }
        
        try {
            CandidatureDao dao = new CandidatureDao();
            ArrayList<HashMap<String, String>> lesCandidatures = dao.findMultiCriteres(conditions);
            request.setAttribute("lesCandidatures", lesCandidatures);
            ArrayList<HashMap<String, String>> tousLesCandidatures = dao.mapCandidatures();
            request.setAttribute("tousLesCandidatures", tousLesCandidatures);
            request.getRequestDispatcher("/WEB-INF/candidatures.jsp").forward(request, response);

        } catch (SQLException ex) {
            request.setAttribute("message", "Pb avec la base de données");
            request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);        
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
