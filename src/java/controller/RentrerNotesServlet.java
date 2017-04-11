package controller;

import dao.NoteDao;
import dao.PersonneDao;
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
import model.Note;
import model.Personne;

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
        
        String[] ids = request.getParameterValues("id");
        String[] notes = request.getParameterValues("note");
        String[] coms = request.getParameterValues("commentaire");
        assert notes.length >= coms.length;
        for (int i = 0; i < notes.length; i++) {
            Integer id = Integer.parseInt(ids[i]);
            Double note = (notes[i].equals("")) ? null : Double.parseDouble(notes[i]);
            String commentaire = (coms[i].equals("")) ? "" : coms[i];
            
            Note noteAjoutee = new Note();
            Personne mrpatate = new Personne();
            mrpatate.setId(id);
            noteAjoutee.setEtudiant(mrpatate);
            noteAjoutee.setNote(note);
            noteAjoutee.setCommentaire(commentaire);
            try {
                dao.insert(noteAjoutee);
                System.out.println("ok mr patate");
            } catch (SQLException ex) {
                Logger.getLogger(RentrerNotesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        

        String message = "Vos ids : " + String.join(", ", ids)
                + "<br/>Vos notes : " + String.join(",", notes)
                + "<br/>Vos commentaires : " + String.join(",", coms);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
    }

}
