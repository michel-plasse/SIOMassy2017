package controller;

import dao.NoteDao;
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
import model.SessionFormation;


@WebServlet("/rentrerNotes")
public class RentrerNotesServlet extends HttpServlet {

    public RentrerNotesServlet(){
    
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
      doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vue = "/WEB-INF/rentrerNotes.jsp";
        /* try {
            NoteDao dao = new NoteDao();
            ArrayList<SessionFormation> lesSessionsOuvertes = dao.getSessionsOuvertes();
            request.setAttribute("lesSessions", lesSessionsOuvertes);
        } catch (SQLException ex) {
            Logger.getLogger(ListerSessionsOuvertesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Pb avec la base de données");
            vue = "/WEB-INF/message.jsp";
        }*/
    }
}
