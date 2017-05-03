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
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EtatCandidature;
import model.Formation;
import model.Personne;

/**
 *
 * @author admin
 */
@WebServlet("/espacePersoGestionnaire")
public class EspacePersoGestionnaireServlet extends HttpServlet {
    
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
        HttpSession maSession = request.getSession(true);
        Personne user = (Personne) maSession.getAttribute("user");
        // Pas connecté
        if(user == null){            
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        //connecté et personne est gestionaire
        else if(user.isEst_gestionnaire()){
            ArrayList<String> conditions = new ArrayList<>();
            String order_by = "date_effet DESC";
            String limit = "LIMIT 5";
            try {            
                CandidatureDao candidatureDao = new CandidatureDao();
                ArrayList<HashMap<String, String>> lesCandidatures = candidatureDao.mapCandidatures(conditions, order_by, limit);
                request.setAttribute("lesCandidatures", lesCandidatures);

                EtatCandidatureDao etatCandidatureDao = new EtatCandidatureDao();
                ArrayList<EtatCandidature> lesEtats = etatCandidatureDao.findAll();
                request.setAttribute("lesEtats", lesEtats);

                FormationDao formationDao = new FormationDao();
                ArrayList<Formation> lesFormations = formationDao.findAll();
                request.setAttribute("lesFormations", lesFormations);

                request.getRequestDispatcher("/WEB-INF/espacePersoGestionnaire.jsp").forward(request, response);

            } catch (SQLException ex) {
                request.setAttribute("message", "Pb avec la base de données");
                request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);        
            }
        }
        //connecté mais le personne n'est pas gestionnaire
        else{
            request.setAttribute("message", "vous n'avez le droit pour acceder cette page");
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
    }

}
