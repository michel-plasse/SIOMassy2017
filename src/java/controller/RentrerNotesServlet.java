package controller;

import dao.EvaluationDao;
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
        EvaluationDao dao = new EvaluationDao();

        try {
            ArrayList<Note> lesNotes = dao.findByEval(1);
            request.setAttribute("lesNotes", lesNotes);
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
        Personne etudiant = new Personne();
        
        String[] ids = request.getParameterValues("id");
        String[] notes = request.getParameterValues("note");
        String[] coms = request.getParameterValues("commentaire");
        assert notes.length >= coms.length;
        
        for (int i = 0; i < notes.length; i++) {
            Integer id = Integer.parseInt(ids[i]);
            Double note = (notes[i].equals("")) ? null : Double.parseDouble(notes[i]);
            String commentaire = (coms[i].equals("")) ? "" : coms[i];
            
            Note noteAjoutee = new Note();
            noteAjoutee.setId_note(id);
            noteAjoutee.setNote(note);
            noteAjoutee.setCommentaire(commentaire);
            //etudiant.setId(id);
            //noteAjoutee.setEtudiant(etudiant);
            
            try {
                dao.update(noteAjoutee.getId_note(),noteAjoutee);
            } catch (SQLException ex) {
                Logger.getLogger(RentrerNotesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    
        
        
        

        String message = "Vos ids : " + String.join(", ", ids)
                + "<br/>Vos notes : " + String.join(", ", notes)
                + "<br/>Vos commentaires : " + String.join(", ", coms);
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
    }

}
