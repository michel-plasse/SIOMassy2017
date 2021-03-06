package controller;

import dao.EvaluationDao;
import dao.NoteDao;
import dao.PersonneDao;
import dao.ProjetDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Evaluation;
import model.Note;
import model.Personne;
import model.Projet;

/**
 *
 * @author Xavier Claude PASSER
 */
@WebServlet(name = "EspacePersoFormateur", urlPatterns = {"/espacePersoFormateur"})
public class EspacePersoFormateurServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession maSession = request.getSession(true);
        Personne user = (Personne) maSession.getAttribute("user");

        if (user == null) {
            // Pas connecté
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            ProjetDao projetDao = new ProjetDao();
            ArrayList<Projet> lesProjets = null;
            EvaluationDao evalDao = new EvaluationDao();
            ArrayList<Evaluation> lesEvaluations = null;
            ArrayList<Evaluation> lesEvaluationsPassees = null;

            try {
                lesProjets = projetDao.findAllByIdFormateur(user.getId());
                lesEvaluations = evalDao.findAllEvalFormateur(user.getId());
                lesEvaluationsPassees = evalDao.findPastEvalFormateur(user.getId());
            } catch (SQLException ex) {
                System.out.println("controller.EspacePersoFormateurServlet.doGet()");
                Logger.getLogger(EspacePersoFormateurServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            // try {
            request.setAttribute("lesProjets", lesProjets);
            request.setAttribute("lesEvals", lesEvaluations);
            request.setAttribute("lesEvalsPassees", lesEvaluationsPassees);
            request.getRequestDispatcher("WEB-INF/espacePersonnelFormateur.jsp").forward(request, response);

            // } catch (SQLException ex) {
            //    Logger.getLogger(EspacePersoFormateurServlet.class.getName()).log(Level.SEVERE, null, ex);
            //    request.setAttribute("message", "Pb avec la base de données. Voir le fichier journal à " + (new Date()));
            //    request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
            //}
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
