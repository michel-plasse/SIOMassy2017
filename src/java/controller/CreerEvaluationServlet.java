/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ModuleDao;
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
import model.Module;
import model.Personne;

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
                request.setAttribute("modules", modules);
                
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
        ModuleDao moduleDao = new ModuleDao();
        int nomModule = Integer.parseInt(request.getParameter("idModule"));

        Module module = new Module ( nomModule, moduleDao.findById(nomModule).getNom() );
        System.out.println(module.getNom());
        request.getRequestDispatcher("/WEB-INF/creerEvaluation.jsp").forward(request, response);
        // Verifier que les données sont correctes
        // Appeler le DAO.insert
        // Renvoyer vers la bonne vue
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
