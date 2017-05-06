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
import javax.servlet.http.HttpSession;
import model.EtatCandidature;
import model.Formation;
import model.Personne;

/**
 *
 * @author AveigA
 */
@WebServlet("/candidatures")
public class ListerCandidaturesServlet extends HttpServlet {
    
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
            String order_by = " ORDER BY date_effet DESC";

            //RECHERCHE
            if(request.getParameter("recherche") != null && !request.getParameter("recherche").isEmpty()){
                String recherche = request.getParameter("recherche");
                String condition = "(p.nom LIKE '%" + recherche + "%' OR p.prenom LIKE '%" + recherche + "%')";
                conditions.add(condition);
            }
            //ETAT
            if(request.getParameter("etat") != null && !request.getParameter("etat").isEmpty()){
                String etat = request.getParameter("etat");
                String condition = "e.id_etat_candidature = '" + etat + "'";
                conditions.add(condition);
            }
            //SESSION FORMATION
            if(request.getParameter("formation") != null && !request.getParameter("formation").isEmpty()){            
                String formation = request.getParameter("formation");
                String condition = "f.id_formation = '" + formation + "'";
                conditions.add(condition);
            }        
            //DATE
            if(request.getParameter("date") != null && !request.getParameter("date").isEmpty()){            
                String date = request.getParameter("date");
                String condition = "DATE_FORMAT(c.date_effet, '%d/%m/%Y') = '" + date + "'";
                conditions.add(condition);
            }
            //TRIER PAR
            if(request.getParameter("trier") != null && !request.getParameter("trier").isEmpty()){
                String trier = request.getParameter("trier");
                //par etat de candidature
                if(trier.equals("etat"))
                    order_by = "ORDER BY e.libelle ASC";
                //par formation
                else if(trier.equals("formation"))
                    order_by = " ORDER BY f.nom ASC";
                else
                    order_by = " ORDER BY p." + trier + " ASC";
            }

            try {            
                CandidatureDao candidatureDao = new CandidatureDao();
                ArrayList<HashMap<String, String>> lesCandidatures = candidatureDao.mapCandidatures(conditions, order_by, "");
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
        //connecté mais le personne n'est pas gestionnaire
        else{
            request.setAttribute("message", "vous n'avez pas le droit pour acceder cette page");
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
