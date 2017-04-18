/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.qcm.QcmDao;
import dao.qcm.QuestionDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Choix;
import model.Qcm;
import model.Question;

/**
 *
 * @author admin
 */
@WebServlet(name = "CreerQcmServlet", urlPatterns = {"/CreerQcm"})
public class CreerQcmServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreerQcmServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreerQcmServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QcmDao qcmDao = new QcmDao();
        Qcm qcm = new Qcm();
        int idQcm = Integer.parseInt(request.getParameter("idQcm"));
        try {
            qcm = qcmDao.findById(idQcm);
            request.setAttribute("qcm", qcm);
        } catch (SQLException ex) {
            Logger.getLogger(CreerQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/creerQcm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String question = request.getParameter("question");
        HashMap<Integer,Choix> lesChoix = new HashMap<>();
        Choix reponse1 = new Choix(request.getParameter("reponse1"), true);
        Choix reponse2 = new Choix(request.getParameter("reponse2"), true);
        Choix reponse3 = new Choix(request.getParameter("reponse3"), true);
        Choix reponse4 = new Choix(request.getParameter("reponse4"), true);
        lesChoix.put(1,reponse1);
        lesChoix.put(2,reponse2);
        lesChoix.put(3,reponse3);
        lesChoix.put(4,reponse4);
        int idQcm = Integer.parseInt(request.getParameter("idQcm"));
        Question laQuestion = new Question(question, lesChoix);
        QuestionDao questionDao = new QuestionDao();
        
        try {
            
            questionDao.insert(idQcm, laQuestion);
            
        } catch (SQLException ex) {
            Logger.getLogger(CreerQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
