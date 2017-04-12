/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EvaluationDao;
import dao.FormationDao;
import dao.ModuleDao;
import dao.SessionFormationDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Evaluation;
import model.Formation;
import model.Module;
import model.Personne;
import model.SessionFormation;

/**
 *
 * @author admin
 */
@WebServlet(name = "CreerEvaluation", urlPatterns = {"/creerEvaluation"})
public class CreerEvaluationServlet extends HttpServlet {

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
        // L'utilisateur en session
        Personne user = (Personne) request.getSession(true).getAttribute("user");
        if(user == null) {
            System.out.println("pas connecté");
            // Rediriger vers login
            response.sendRedirect("login");
        }
        else if (!user.isEst_formateur()) {
            System.out.println("pas formateur");
            request.setAttribute("message", "Vous n'êtes pas un formateur : vous ne pouvez pas créer une évaluation");
            request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
        }
        else {
            try {
                ModuleDao moduleDao = new ModuleDao();
                ArrayList<Module> modules = moduleDao.findAll();
                SessionFormationDao sessionFormationDao = new SessionFormationDao();
                ArrayList<SessionFormation> sessions = sessionFormationDao.getSessionsOuvertes();
                
                request.setAttribute("modules", modules);
                request.setAttribute("sessions", sessions);
                
            } catch (SQLException ex) {
                Logger.getLogger(CreerEvaluationServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "probleme avec la bdd");
            }
            System.out.println("formateur");
            request.getRequestDispatcher("/WEB-INF/creerEvaluation.jsp").forward(request, response);
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
        EvaluationDao evaluationDao = new EvaluationDao();
        ModuleDao moduleDao = new ModuleDao();
        Evaluation evaluation ;
        HttpSession maSession = request.getSession(true);
        SessionFormationDao sessionFormationDao = new SessionFormationDao();
        Personne user = (Personne) maSession.getAttribute("user");
        String intitule = request.getParameter("intitule");
        int idSession = Integer.parseInt(request.getParameter("idSession"));
        int idModule = Integer.parseInt(request.getParameter("idSession"));
        
        try {
            
            String date = request.getParameter("date");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mi:ss");
            java.util.Date dateparsed = sdf.parse(date);
            java.sql.Date sqldate = new java.sql.Date(dateparsed.getTime());
            evaluationDao.insert(evaluation = new Evaluation(
                    moduleDao.findById(idModule),
                    user,
                    sessionFormationDao.findById(idSession),
                    sqldate,
                    intitule));
            
        } catch (ParseException ex ) {
            Logger.getLogger(CreerEvaluationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreerEvaluationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.getRequestDispatcher("/WEB-INF/espacePersonnelFormateur.jsp").forward(request, response);
        
        // Verifier que les données sont correctes
        // Appeler le DAO.insert
        // Renvoyer vers la bonne vue
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
