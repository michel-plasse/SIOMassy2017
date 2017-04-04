package controller;

import dao.NoteDao;
import dao.PersonneDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Note;
import model.Personne;
import model.SessionFormation;

@WebServlet("/rentrerNotes")
public class RentrerNotesServlet extends HttpServlet {
    
    public RentrerNotesServlet() {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String vue = "/WEB-INF/rentrerNotes.jsp";
        PersonneDao dao = new PersonneDao();
        
        try {
            ArrayList<Personne> lesPersonnes = dao.findByEtat();
            request.setAttribute("lesPersonnes", lesPersonnes);
        } catch (SQLException ex) {
            Logger.getLogger(RentrerNotesServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Pb avec la base de données");
            vue = "/WEB-INF/message.jsp";
        }
        
        this.getServletContext().getRequestDispatcher(vue).forward(request, response);
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vue = "/WEB-INF/rentrerNotes.jsp";
        NoteDao dao = new NoteDao();
        //Double note = request.getParameter("note");
        PrintWriter out = response.getWriter();
        Map<String, String[]> listparam = request.getParameterMap();
        Set<String> parameterNames = request.getParameterMap().keySet();
        for (String id : parameterNames) {
            
            int id_personne = Integer.parseInt(id);
            double valeur = Double.parseDouble(listparam.get(id)[0]);
            String commentaire = listparam.get(id)[1];
            Note uneNote = new Note();
            
            uneNote.setCommentaire(commentaire);
            uneNote.setNote(valeur);
        }
    }
    
}
