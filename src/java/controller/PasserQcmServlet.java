/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QcmDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Choix;
import model.Qcm;
import model.Question;

/**
 *
 * @author ghisfix
 */
@WebServlet(name = "PasserQcmServlet", urlPatterns = {"/passerqcm"})
public class PasserQcmServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        QcmDao qcmDao = new QcmDao();
        Qcm unQcm = null;
        
        try {
            unQcm = qcmDao.findById(1);
        } catch (SQLException ex) {
            Logger.getLogger(PasserQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(unQcm != null) {
            PrintWriter out = response.getWriter();
            for(Question uneQuestion : unQcm.getLesQuestions()){
                out.println(uneQuestion.getIdQuestion() + " - " + uneQuestion.getQuestion());
                for(Choix unChoix : uneQuestion.getLesChoix()) {
                    out.println(" + " + unChoix.getChoix());
                }
            }
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
