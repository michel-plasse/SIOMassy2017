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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.objects.NativeString;
import static jdk.nashorn.internal.objects.NativeString.trim;
import model.Module;
import model.Personne;
import model.Qcm;

/**
 *
 * @author ghisfix
 */
@WebServlet(name = "CreerQcm2Servlet", urlPatterns = {"/qcm/creer"})
public class CreerQcm2Servlet extends HttpServlet {

    public static final String VUE = "/WEB-INF/qcm/creerv2.jsp";
    public static final String ATT_TITLE = "title";
    public static final String ATT_TITLE_VALUE = "Création QCM";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            Personne user = (Personne) session.getAttribute("user");
            if (user.isEst_formateur()) {
                ModuleDao moduleDao = new ModuleDao();
                ArrayList<Module> modules = null;
                try {
                    modules = moduleDao.findAll();
                } catch (SQLException ex) {
                    Logger.getLogger(CreerQcm2Servlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("modules", modules);
                request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
                request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            }

        } else {
            response.sendRedirect(this.getServletContext().getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            Personne user = (Personne) session.getAttribute("user");
            if (user.isEst_formateur()) {

                if (request.getParameter("submitqcm") != null) {
                    String titreQcmParam = request.getParameter("titre");
                    String idModuleParam = request.getParameter("module");

                    if (titreQcmParam != null && !trim(titreQcmParam).equals("") && idModuleParam != null) {
                        int idModule = -1;
                        try {
                            idModule = Integer.parseInt(idModuleParam);
                        } catch (NumberFormatException e) {
                            System.err.println("probleme parse id module");
                        }
                        if (idModule != -1) {
                            QcmDao qcmDao = new QcmDao();
                            int idNewQcm = -1;
                            Qcm newQcm = new Qcm();
                            newQcm.setIntitule(titreQcmParam);
                            newQcm.setValide(false);

                            try {
                                idNewQcm = qcmDao.insertLight(user.getId(), idModule, newQcm);
                            } catch (SQLException ex) {
                                Logger.getLogger(CreerQcm2Servlet.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            if (idNewQcm != -1) {
                                response.sendRedirect(this.getServletContext().getContextPath() + "/qcm/edit?id=" + idNewQcm);
                            } else {
                                session.setAttribute("messageError", "Problème avec les paramètres transmis");
                                request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
                                request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
                            }
                        }

                    } else {
                        session.setAttribute("messageError", "Problème avec les paramètres transmis");
                        request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
                        request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
                    }
                }
            } else {
                response.sendRedirect(this.getServletContext().getContextPath() + "/");
            }

        } else {
            response.sendRedirect(this.getServletContext().getContextPath() + "/");
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
