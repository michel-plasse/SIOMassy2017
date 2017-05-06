/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CandidatureDao;
import dao.EtatCandidatureDao;
import dao.FormationDao;
import dao.PersonneDao;
import dao.SessionFormationDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Candidature;
import model.EtatCandidature;
import model.Formation;
import model.Personne;
import model.SessionFormation;

/**
 *
 * @author admin
 */
@WebServlet("/candidature")
public class CandidatureServlet extends HttpServlet {
    
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
            if(request.getParameter("personne") != null && !request.getParameter("personne").isEmpty()
                    && request.getParameter("session") != null && !request.getParameter("session").isEmpty()
                    && request.getParameter("formation") != null && !request.getParameter("formation").isEmpty()
                    && request.getParameter("etat") != null && !request.getParameter("etat").isEmpty()){
                
                int idPersonne = Integer.parseInt(request.getParameter("personne"));
                int idSession = Integer.parseInt(request.getParameter("session"));
                int idFormation = Integer.parseInt(request.getParameter("formation"));
                int idEtat = Integer.parseInt(request.getParameter("etat"));
                
                ArrayList<String> conditions = new ArrayList<>();
                
                String personneConditions = "c.id_personne = '" + idPersonne + "'";
                String sessionConditions = "c.id_session = '" + idSession + "'";
                String etatConditions = "c.id_etat_candidature = '" + idEtat + "'";
                conditions.add(personneConditions);
                conditions.add(sessionConditions);
                conditions.add(etatConditions);
                
                try {
                    
                    CandidatureDao candidatureDao = new CandidatureDao();
                    ArrayList<HashMap<String, String>> candidature = candidatureDao.mapCandidatures(conditions, "", "");
                
                    PersonneDao personneDao = new PersonneDao();
                    SessionFormationDao sessionFormationDao = new SessionFormationDao();
                    FormationDao formationDao = new FormationDao();
                    EtatCandidatureDao etatCandidatureDao = new EtatCandidatureDao();
                    
                    if(candidature.size() == 1){
                        Personne personne = personneDao.findById(idPersonne);
                        SessionFormation sessionFormation = sessionFormationDao.findById(idSession);
                        Formation formation = formationDao.findById(idFormation);
                        EtatCandidature etatCandidature = etatCandidatureDao.findById(idEtat);
                        if(personne != null && sessionFormation != null && formation != null && etatCandidature != null){
                            
                            ArrayList<EtatCandidature> lesEtats = etatCandidatureDao.findAll();
                            request.setAttribute("lesEtats", lesEtats);
                            request.setAttribute("personne", personne);
                            request.setAttribute("session", sessionFormation);
                            request.setAttribute("formation", formation);
                            request.setAttribute("etat", etatCandidature);
                            request.setAttribute("unCandidature", candidature);
                            request.getRequestDispatcher("WEB-INF/candidature.jsp").forward(request, response);
                        }
                        else{
                            request.setAttribute("message", "La candidature demandée n'existe pas");
                            request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
                        }
                    }
                    else{
                        request.setAttribute("message", "La candidature demandée n'existe pas");
                        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
                    }
                }catch (SQLException ex) {
                    request.setAttribute("message", "Pb avec la base de données");
                    request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("message", "La page demandée n’existe pas, error 404");
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
        HttpSession maSession = request.getSession(true);
        Personne user = (Personne) maSession.getAttribute("user");
        // Pas connecté
        if(user == null){            
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        //connecté et personne est gestionaire
        else if(user.isEst_gestionnaire()){
            if(request.getParameter("personne") != null && !request.getParameter("personne").isEmpty()
                    && request.getParameter("session") != null && !request.getParameter("session").isEmpty()
                    && request.getParameter("formation") != null && !request.getParameter("formation").isEmpty()
                    && request.getParameter("etat") != null && !request.getParameter("etat").isEmpty()){
                
                int idPersonne = Integer.parseInt(request.getParameter("personne"));
                int idSession = Integer.parseInt(request.getParameter("session"));
                int idFormation = Integer.parseInt(request.getParameter("formation"));
                int idEtat = Integer.parseInt(request.getParameter("etat"));
                
                PersonneDao personneDao = new PersonneDao();
                SessionFormationDao sessionFormationDao = new SessionFormationDao();
                FormationDao formationDao = new FormationDao();
                EtatCandidatureDao etatCandidatureDao = new EtatCandidatureDao();
                
                try {
                    Personne personne = personneDao.findById(idPersonne);
                    SessionFormation sessionFormation = sessionFormationDao.findById(idSession);
                    Formation formation = formationDao.findById(idFormation);
                    EtatCandidature etatCandidature = etatCandidatureDao.findById(idEtat);
                    
                    if(personne != null && sessionFormation != null && etatCandidature != null){
                        Candidature candidature = new Candidature(personne, sessionFormation, etatCandidature);
                        CandidatureDao candidatureDao = new CandidatureDao();
                        boolean result = candidatureDao.update(idSession, candidature);
                        if(result){
                            response.sendRedirect(this.getServletContext().getContextPath() + "/candidature?personne=" + idPersonne + "&session=" + idSession + "&formation=" + idFormation + "&etat=" + idEtat);
                        }
                        
                    }else{
                        request.setAttribute("message", "La candidature demandée n'existe pas");
                        request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
                    }
                    
                }catch (SQLException ex) {
                    request.setAttribute("message", "Pb avec la base de données");
                    request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("message", "La page demandée n’existe pas, error 404");
                request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);  
            } 
        }
        //connecté mais le personne n'est pas gestionnaire
        else{
            request.setAttribute("message", "vous n'avez pas le droit pour acceder cette page");
            request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response); 
        }        
    }

}
