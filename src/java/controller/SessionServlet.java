package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ConnectionBd;
import dao.SessionFormationDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SessionFormation;

@WebServlet("/sessionsOuvertes")
public class SessionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public SessionServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vue = "/WEB-INF/listerSessionsOuvertes.jsp";
        try {
            SessionFormationDao dao = new SessionFormationDao();
            ArrayList<SessionFormation> lesSessionsOuvertes = dao.getSessionsOuvertes();
            request.setAttribute("lesSessions", lesSessionsOuvertes);
        } catch (SQLException ex) {
            Logger.getLogger(SessionServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Pb avec la base de données");
            vue = "/WEB-INF/message.jsp";
        }
        request.getRequestDispatcher(vue).forward(request, response);
    }

}