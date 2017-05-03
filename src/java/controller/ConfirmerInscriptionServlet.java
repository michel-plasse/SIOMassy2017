/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PersonneDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "ConfirmerInscription", urlPatterns = {"/ConfirmerInscription"})
public class ConfirmerInscriptionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PersonneDao pers = new PersonneDao();
        String token = request.getParameter("token");
        String pattern = "(^[a-zA-Z0-9]{26,32}$)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(token);
        HttpSession session = request.getSession();

        if (m.matches()) {
            int id_personne = 0;
            boolean result = false;
            try {
                id_personne = pers.findIdFromToken(token);
            } catch (SQLException ex) {
                Logger.getLogger(ConfirmerInscriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //PrintWriter out = response.getWriter();
            //out.print(id_personne);
            if (id_personne != -1) {
                try {
                    result = pers.activeUser(id_personne);
                    session.setAttribute("messageOk", "Votre inscription a été validée avec succès.");
                } catch (SQLException ex) {
                    Logger.getLogger(ConfirmerInscriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
                    session.setAttribute("messageError", "Problème rencontré lors de la validation de votre inscription.");
                }

            }
            //out.print(result);
            response.sendRedirect("login");

        } else {
            request.setAttribute("messageErreurValidation", "Une erreur s'est produite lors de la confirmation, veuillez entrez a nouveau vos informations.");
            //response.sendRedirect(this.getServletContext().getContextPath() + "/inscrire" );
            request.getRequestDispatcher("/WEB-INF/inscrire.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
