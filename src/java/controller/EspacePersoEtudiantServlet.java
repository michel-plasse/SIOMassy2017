/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EvaluationDao;
import dao.NoteDao;
import dao.PersonneDao;
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

/**
 *
 * @author admin
 */
@WebServlet(name = "EspacePersoEtudiant", urlPatterns = {"/espacePersoEtudiant"})
public class EspacePersoEtudiantServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    HttpSession maSession = request.getSession(true);
    Personne user = (Personne) maSession.getAttribute("user");
    boolean champsrenseignes = true;
    
    String nom = request.getParameter("nom");
    String prenom = request.getParameter("prenom");
    String no_rue = request.getParameter("no_rue");
    String nom_rue = request.getParameter("nom_rue");
    String code_postal = request.getParameter("code_postal");
    String ville = request.getParameter("ville");
    String pays = request.getParameter("pays");
    String no_phone = request.getParameter("no_phone");
    String email = request.getParameter("email");
    
     if (nom.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("nom", "Veuillez entrer votre nom.");
            System.out.println("Rentre dans if condition");
        }
        if (prenom.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("prenom", "Veuillez entrer votre pr�nom.");
        }
        if (email.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("email", "Veuillez entrer votre email.");
        }
       
    
     if (champsrenseignes) {
            try {
//                Personne personneAjoutee = new Personne(nom, prenom, no_rue, nom_rue, code_postal, ville,
//                        pays, no_phone, email);
                PersonneDao dao = new PersonneDao();
                user.setNo_tel(request.getParameter("no_phone"));
                
                dao.update(user.getId(), user);
                } catch (SQLException e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
            }}
     
            response.sendRedirect("espacePersoEtudiant");
    
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
            NoteDao daoNote = new NoteDao();
            //EvaluationDao daoEval = new EvaluationDao();

            try {

                ArrayList<Note> lesNotes = daoNote.findNoteById(user.getId());
                System.out.println("lesNotes : " + lesNotes.size());
                maSession.setAttribute("lesNotes", lesNotes);
                System.out.println(daoNote.findNoteById(1));
                request.getRequestDispatcher("/WEB-INF/espacePersoEtudiant.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EspacePersoEtudiantServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Pb avec la base de données. Voir le fichier journal à " + (new Date()));
                request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
            }
        }
    }
}