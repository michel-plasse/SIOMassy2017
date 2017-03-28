/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersonneDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Personne;

/**
 *
 * @author nate
 */
@WebServlet(name = "TrombinoscopeServlet", urlPatterns = {"/Trombinoscope"})
public class TrombinoscopeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

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
                PersonneDao pdao = new PersonneDao();
        try {
            ArrayList<Personne> lesPersonnes = pdao.findAll();
            request.setAttribute("lesPersonnes", lesPersonnes);
        } catch (SQLException ex) {
            Logger.getLogger(TrombinoscopeServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerreur", "Problème avec la base de données");
        }
        request.getRequestDispatcher("/WEB-INF/trombinoscope.jsp").forward(request, response);
        
    }

}
