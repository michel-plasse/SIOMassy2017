/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.qcm;

import static controller.qcm.PasserQcmServlet.VUE_RESULTAT;
import dao.qcm.QcmDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
@WebServlet(name = "ResultatQcmServlet", urlPatterns = {"/qcm/resultat"})
public class ResultatQcmServlet extends HttpServlet {

    public static final String VUE_RESULTAT = "/WEB-INF/qcm/resultat.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Personne user = (Personne) session.getAttribute("user");
        String idQcmPasse = request.getParameter("id");
        Integer idQcm = null;

        try {
            idQcm = Integer.parseInt(idQcmPasse);
        } catch (NumberFormatException e) {
            System.out.println("Erreur id QCM invalide");
        }

        if (idQcm != null) {
            QcmDao qcmDao = new QcmDao();
            int isAlreadyDone = -1;

            try {
                isAlreadyDone = qcmDao.isAlreadyDone(user.getId(), idQcm);
            } catch (SQLException ex) {
                Logger.getLogger(ResultatQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (isAlreadyDone != -1) {
                Qcm qcmPasse = null;
                HashSet<Integer> lesChoix = null;

                try {
                    qcmPasse = qcmDao.findById(idQcm);
                } catch (SQLException ex) {
                    Logger.getLogger(PasserQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (qcmPasse != null) {
                    try {
                        lesChoix = qcmDao.findAnsByIdPassage(user.getId(), qcmPasse.getIdQcm());
                    } catch (SQLException ex) {
                        Logger.getLogger(ResultatQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (lesChoix != null) {
                        float compteurBonnesRep = 0;
                        float nbBonnesRep = 0;
                        int note;
                        for (Question q : qcmPasse.getLesQuestions()) {
                            nbBonnesRep += q.getNbBonnesRep();
                            for (int idChoix : lesChoix) {
                                if (q.getLesChoix().containsKey(idChoix)) {
                                    q.getLesChoix().get(idChoix).setEstChoisi(true);
                                    if (q.getLesChoix().get(idChoix).isEstCorrect()) {
                                        compteurBonnesRep++;
                                    }
                                }
                            }
                        }

                        note = (int) ((compteurBonnesRep / nbBonnesRep) * 100);

                        request.setAttribute("note", note);
                        request.setAttribute("qcmPasse", qcmPasse);

                        this.getServletContext().getRequestDispatcher(VUE_RESULTAT).forward(request, response);
                    }

                }

            } else {

                response.sendRedirect(this.getServletContext().getContextPath() + "/qcm/passer?id=" + idQcm);
            }
        } else {
            response.sendRedirect(this.getServletContext().getContextPath() + "/");
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
