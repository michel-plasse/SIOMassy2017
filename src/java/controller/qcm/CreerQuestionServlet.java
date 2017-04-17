/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.qcm;

import dao.ModuleDao;
import dao.qcm.QcmDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.objects.NativeString.trim;
import model.Choix;
import model.Module;
import model.Personne;
import model.Qcm;
import model.Question;

/**
 *
 * @author ghisfix
 */
@WebServlet(name = "CreerQuestionServlet", urlPatterns = {"/qcm/edit"})
public class CreerQuestionServlet extends HttpServlet {

    public static final String VUE = "/WEB-INF/qcm/edit.jsp";
    public static final String ATT_TITLE = "title";
    public static final String ATT_TITLE_VALUE = "Edition QCM";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            Personne user = (Personne) session.getAttribute("user");
            if (user.isEst_formateur()) {
                if (request.getParameter("id") != null) {
                    String idQcmParam = request.getParameter("id");
                    int idQcm = -1;
                    try {
                        idQcm = Integer.parseInt(idQcmParam);
                    } catch (NumberFormatException e) {
                        System.err.println("probleme id qcm");
                    }

                    if (idQcm != -1) {
                        QcmDao qcmDao = new QcmDao();
                        boolean isCorrect = false;
                        try {
                            isCorrect = qcmDao.isCheckedQcmAndUser(idQcm, user.getId());
                        } catch (SQLException ex) {
                            Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (isCorrect) {
                            Qcm qcmEdit = null;
                            try {
                                qcmEdit = qcmDao.findById(idQcm);
                            } catch (SQLException ex) {
                                Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (qcmEdit != null) {
                                ModuleDao moduleDao = new ModuleDao();
                                ArrayList<Module> modules = null;
                                try {
                                    modules = moduleDao.findAll();
                                } catch (SQLException ex) {
                                    Logger.getLogger(CreerQcm2Servlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                request.setAttribute("modules", modules);
                                request.setAttribute("qcm", qcmEdit);
                                request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
                                request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
                            } else {
                                response.sendRedirect(this.getServletContext().getContextPath() + "/qcm/creer");
                            }
                        } else {
                            response.sendRedirect(this.getServletContext().getContextPath() + "/qcm/creer");
                        }

                    }
                }
            }

        } else {
            response.sendRedirect(this.getServletContext().getContextPath() + "/qcm/creer");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            Personne user = (Personne) session.getAttribute("user");
            if (user.isEst_formateur()) {
                if (request.getParameter("id") != null) {
                    String idQcmParam = request.getParameter("id");
                    int idQcm = -1;
                    try {
                        idQcm = Integer.parseInt(idQcmParam);
                    } catch (NumberFormatException e) {
                        System.err.println("probleme id qcm");
                    }

                    if (idQcm != -1) {
                        QcmDao qcmDao = new QcmDao();
                        boolean isCorrect = false;
                        try {
                            isCorrect = qcmDao.isCheckedQcmAndUser(idQcm, user.getId());
                        } catch (SQLException ex) {
                            Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (isCorrect) {

                            //Modif info
                            if (request.getParameter("modif") != null) {
                                String titreQcmParam = request.getParameter("titre");
                                String moduleQcmParam = request.getParameter("module");
                                String etatQcmParam = request.getParameter("active");

                                int idModule = Integer.parseInt(moduleQcmParam);
                                boolean etat = Boolean.valueOf(etatQcmParam);
                                
                                try {
                                    Qcm updateQcm = new Qcm();
                                    updateQcm.setIdQcm(idQcm);
                                    updateQcm.setIntitule(titreQcmParam);
                                    updateQcm.setValide(etat);
                                    
                                    qcmDao.updateLight(idModule, updateQcm);
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                            //Suppression d'une question
                            if (request.getParameter("delete") != null) {
                                String idDeleteQParam = request.getParameter("delete");
                                int idQuestToDelete = Integer.parseInt(idDeleteQParam);

                                try {
                                    qcmDao.deleteQuestion(idQuestToDelete);
                                } catch (SQLException ex) {
                                    Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            //END Suppression d'une question

                            //Ajout d'une Question
                            boolean ajoutQuestion = true;
                            if (request.getParameter("question") != null) {

                                String questionParam = trim(request.getParameter("question"));

                                if (!questionParam.isEmpty()) {
                                    Question question = new Question();
                                    HashMap<Integer, Choix> lesChoix = new HashMap<>();
                                    question.setQuestion(questionParam);

                                    for (int i = 1; i < 7; i++) {
                                        if (request.getParameter("reponse[" + i + "]") != null) {
                                            String rep = trim(request.getParameter("reponse[" + i + "]"));
                                            if (!rep.isEmpty()) {
                                                Choix choix = new Choix();
                                                choix.setIdChoix(i);
                                                choix.setChoix(rep);
                                                if (request.getParameter("correcte[" + i + "]") != null) {
                                                    choix.setEstCorrect(true);
                                                } else {
                                                    choix.setEstCorrect(false);
                                                }
                                                lesChoix.put(choix.getIdChoix(), choix);
                                            }
                                        }
                                    }
                                    question.setLesChoix(lesChoix);

                                    if (question.getLesChoix().size() >= 2) {
                                        try {
                                            qcmDao.insertQuestion(idQcm, question);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
                                            ajoutQuestion = false;
                                        }
                                    }

                                }
                            }

                            Qcm qcmEdit = null;
                            try {
                                qcmEdit = qcmDao.findById(idQcm);
                            } catch (SQLException ex) {
                                Logger.getLogger(CreerQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (qcmEdit != null) {
                                if (!ajoutQuestion) {
                                    session.setAttribute("messageError", "Problème rencontré lors de l'ajout de votre question.");
                                }

                                ModuleDao moduleDao = new ModuleDao();
                                ArrayList<Module> modules = null;
                                try {
                                    modules = moduleDao.findAll();
                                } catch (SQLException ex) {
                                    Logger.getLogger(CreerQcm2Servlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                request.setAttribute("modules", modules);
                                request.setAttribute("qcm", qcmEdit);
                                request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
                                request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
                            } else {
                                response.sendRedirect(this.getServletContext().getContextPath() + "/qcm/creer");
                            }
                        } else {
                            response.sendError(403, "problème identifiants");
                        }
                    }
                } else {
                    response.sendRedirect(this.getServletContext().getContextPath() + "/qcm/creer");
                }

            }
        } else {
            response.sendError(403, "problème identifiants");
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
