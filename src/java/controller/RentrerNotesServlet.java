package controller;

import dao.EvaluationDao;
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
import javax.servlet.http.HttpSession;
import model.Note;
import model.Personne;

@WebServlet("/rentrerNotes")
public class RentrerNotesServlet extends HttpServlet {

    public static final String VUE = "/WEB-INF/rentrerNotes.jsp";
    public static final String VUE_ERROR = "/WEB-INF/message.jsp";
    
    public RentrerNotesServlet() {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // verifier que la personne connecté et formateur
        Personne user = (Personne) request.getSession(true).getAttribute("user");
        if (user == null) {
            System.out.println("pas connecté");
            // Rediriger vers login
            response.sendRedirect("login");
        } else if (!user.isEst_formateur()) {
            System.out.println("pas formateur");
            request.setAttribute("message", "Vous n'êtes pas un formateur : vous ne pouvez pas créer une évaluation");
            request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
        } else {
            // verifier que idEvaluation est entier
            String idEvaluationParam = request.getParameter("id");
            int idEvaluation = Integer.parseInt(idEvaluationParam);
            // verifier que c'est une eval du formateur
            NoteDao noteDao = new NoteDao();
            boolean reponse = false;
            
            try {
                reponse = noteDao.checkIdFormateur(idEvaluation, user.getId());
            } catch (SQLException ex) {
                Logger.getLogger(RentrerNotesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (reponse) {
                
                EvaluationDao dao = new EvaluationDao();
                
                try {
                    ArrayList<Note> lesNotes = dao.findByEval(idEvaluation);
                    request.setAttribute("lesNotes", lesNotes);
                } catch (SQLException ex) {
                    Logger.getLogger(RentrerNotesServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", "Pb avec la base de données");
                    this.getServletContext().getRequestDispatcher(VUE_ERROR).forward(request, response);
                    return;
                }
                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            } else {
                response.sendRedirect("espacePersoFormateur");
            }
            
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
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
                dao.update(noteAjoutee.getId_note(), noteAjoutee);
                session.setAttribute("messageOk", "Vos notes sont bien enregistrées");
            } catch (SQLException ex) {
                Logger.getLogger(RentrerNotesServlet.class.getName()).log(Level.SEVERE, null, ex);
                session.setAttribute("messageError", "Problème rencontré lors de l'insertion des notes");
            }
        }
        
        response.sendRedirect("espacePersoFormateur");
    }
    
}
