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
            request.setAttribute("message", "la question ne peux pas être supprimée le Qcm est validé" + ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/creerQcm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //si boutton supprimé est cliqué
        if (request.getParameter("supprimerQuestion") != null) {
            supprimerQuestion(request, response);
        }

        //si boutton creer est cliqué
        if (request.getParameter("creer") != null) {
            creer(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void supprimerQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {

        QuestionDao questionDao = new QuestionDao();
        int idQcm = Integer.parseInt(request.getParameter("idQcm"));
        int idQuestion = Integer.parseInt(request.getParameter("supprimerQuestion"));
        System.out.println(idQuestion);

        try {
            questionDao.delete(idQuestion);
            response.sendRedirect("CreerQcm?idQcm=" + idQcm);
        } catch (SQLException ex) {
            Logger.getLogger(CreerQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void creer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String question = request.getParameter("question");

        HashMap<Integer, Choix> lesChoix = new HashMap<>();

        for (int i = 1; i < 5; i++) {
            System.out.println("reponse " + i + " : " + request.getParameter("reponse" + i));
            Choix reponse = new Choix(request.getParameter("reponse" + i), (request.getParameter("valide").equals(i + "")) ? true : false);
            if (reponse.getLibelle() != null && !reponse.getLibelle().equals("")) {
                lesChoix.put(i, reponse);
            }
        }

        if (lesChoix.size() >= 2 && !question.isEmpty()) {
            QuestionDao questionDao = new QuestionDao();
            int idQcm = Integer.parseInt(request.getParameter("idQcm"));
            Question laQuestion = new Question(question, lesChoix);
            try {

                questionDao.insert(idQcm, laQuestion);
                response.sendRedirect("CreerQcm?idQcm=" + idQcm);
            } catch (SQLException ex) {
                Logger.getLogger(CreerQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Pb avec bd : " + ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "les champs ne sont pas remplis , il faut au moins deux reponses par question ");
            request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
        }
    }

}
