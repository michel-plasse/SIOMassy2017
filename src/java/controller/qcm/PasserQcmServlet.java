/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.qcm;

import dao.qcm.QcmDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Choix;
import model.Personne;
import model.Qcm;
import model.Question;

/**
 *
 * @author ghisfix
 */
@WebServlet(name = "PasserQcmServlet", urlPatterns = {"/qcm/passer"})
public class PasserQcmServlet extends HttpServlet {

    public static final String VUE_PASSER = "/WEB-INF/qcm/passer.jsp";
    public static final String VUE_RESULTAT = "/WEB-INF/qcm/resultat.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        QcmDao qcmDao = new QcmDao();
//        Qcm unQcm = null;
//        
//        try {
//            unQcm = qcmDao.findById(1);
//        } catch (SQLException ex) {
//            Logger.getLogger(PasserQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        if(unQcm != null) {
//            PrintWriter out = response.getWriter();
//            for(Question uneQuestion : unQcm.getLesQuestions()){
//                out.println(uneQuestion.getIdQuestion() + " - " + uneQuestion.getQuestion());
//                for(Choix unChoix : uneQuestion.getLesChoix()) {
//                    out.println(" + " + unChoix.getChoix());
//                }
//            }
//        }
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            Personne user = (Personne) session.getAttribute("user");
            Integer idQcm = null;

            try {
                idQcm = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                System.out.println("Id QCM invalide");
            }

            if (idQcm != null) {
                QcmDao qcmDao = new QcmDao();
                Qcm unQcm = null;
                int isAlreadyDone = 0;

                try {
                    isAlreadyDone = qcmDao.isAlreadyDone(user.getId(), idQcm);
                } catch (SQLException ex) {
                    Logger.getLogger(PasserQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (isAlreadyDone == -1) {
                    try {
                        unQcm = qcmDao.findById(idQcm);
                    } catch (SQLException ex) {
                        Logger.getLogger(PasserQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (unQcm != null) {
                        request.setAttribute("qcm", unQcm);
                        request.setAttribute("title", "Quizz : " + unQcm.getIntitule());
                        this.getServletContext().getRequestDispatcher(VUE_PASSER).forward(request, response);
                    }
                } else {
                    response.sendRedirect(this.getServletContext().getContextPath() + "/qcm/resultat?id=" + idQcm);
                }

            }
        } else {
            response.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            Personne user = (Personne) session.getAttribute("user");
            String idQcmPasse = request.getParameter("idQcm");
            Integer idQcm = null;

            try {
                idQcm = Integer.parseInt(idQcmPasse);
            } catch (NumberFormatException e) {
                System.out.println("Erreur id QCM invalide");
            }

            if (idQcm != null) {
                QcmDao qcmDao = new QcmDao();
                int isAlreadyDone = 0;
                try {
                    isAlreadyDone = qcmDao.isAlreadyDone(user.getId(), idQcm);
                } catch (SQLException ex) {
                    Logger.getLogger(PasserQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (isAlreadyDone == -1) {
                    Qcm qcmPasse = null;
                    try {
                        qcmPasse = qcmDao.findById(idQcm);
                    } catch (SQLException ex) {
                        Logger.getLogger(PasserQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (qcmPasse != null) {
                        float compteurBonnesRep = 0;
                        float nbBonnesRep = 0;
                        int note;
                        for (Question q : qcmPasse.getLesQuestions()) {
                            nbBonnesRep += q.getNbBonnesRep();
                            if (request.getParameterValues(String.valueOf(q.getIdQuestion())) != null) {
                                String[] repsQuest = request.getParameterValues(String.valueOf(q.getIdQuestion()));
                                for (String r : repsQuest) {
                                    int rep = Integer.parseInt(r);
                                    q.getLesChoix().get(rep).setEstChoisi(true);
                                    if (q.getLesChoix().get(rep).isEstCorrect()) {
                                        compteurBonnesRep++;
                                    } else if (!q.getLesChoix().get(rep).isEstCorrect()) {
                                        compteurBonnesRep--;
                                    }
                                }
                            }
                        }
                        if (nbBonnesRep < 0) {
                            nbBonnesRep = 0;
                        }

                        try {
                            qcmDao.insertPassage(user.getId(), qcmPasse);
                        } catch (SQLException ex) {
                            Logger.getLogger(PasserQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        note = (int) ((compteurBonnesRep / nbBonnesRep) * 100);

                        request.setAttribute("note", note);
                        request.setAttribute("qcmPasse", qcmPasse);

                        this.getServletContext().getRequestDispatcher(VUE_RESULTAT).forward(request, response);

                    }

                } else {
                    response.sendRedirect(this.getServletContext().getContextPath() + "qcm/resultat?id=" + idQcm);
                }
            }

        } else {
            response.sendError(403);
        }

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
