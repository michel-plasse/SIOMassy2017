/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EvaluationDao;
import dao.PersonneDao;
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
import javax.servlet.http.HttpSession;
import model.Evaluation;
import model.Personne;

/**
 *
 * @author admin
 */
@WebServlet(name = "EspacePersoEtudiant", urlPatterns = {"/EspacePersoEtudiant"})
public class EspacePersoEtudiantServlet extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //si l'utilisateur existe alors on le renvoie sur sa page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession maSession = request.getSession(true);
        Personne user = (Personne) maSession.getAttribute("user");  
        EvaluationDao eval = new EvaluationDao();
//        try {
//            //ArrayList<Evaluation> evaluation = eval.findAllEvalEleve(user.getId());
//            maSession.setAttribute("eval", evaluation);
//            if (user == null) {
//            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
//            }
//           // else {
//          //  request.getRequestDispatcher( "/WEB-INF/espacePersoEtudiant.jsp").forward(request, response);
//        //}
//        } catch (SQLException ex) {
//            Logger.getLogger(EspacePersoEtudiantServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
