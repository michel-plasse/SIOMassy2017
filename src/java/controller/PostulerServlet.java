package controller;

import dao.SessionFormationDao;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidatureDao;
import dao.CandidatureHome;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.Candidature;
import model.EtatCandidature;
import model.Personne;
import model.SessionFormation;

/**
 * Servlet implementation class PostulerServlet
 */
@WebServlet("/postuler")
public class PostulerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Personne user = new Personne(2, "Monoi", "Chat", "croquette", "no_rue", "rue", "code_postal", "ville", "pays", "ot_de_passe");

        HttpSession session = request.getSession();
        //pour tester
	session.setAttribute("user", user);
       
        if(session.getAttribute("user") != null) {
            // Utilisateur connecté
            String idSession = request.getParameter("idSessionFormation");
            if(idSession != null) {

               String paramSessionFormation = request.getParameter("idSessionFormation");
               
               if(isParsable(paramSessionFormation)){     
                   
                   try {
                       int id_SessionFormation = Integer.parseInt(paramSessionFormation);
                       SessionFormationDao sFD = new SessionFormationDao();
                       if(sFD.isExistAndOpen(id_SessionFormation)) {
                          //manque infos session/formation à envoyer à la jsp
                          request.setAttribute("idsession", id_SessionFormation);
                          getServletContext().getRequestDispatcher("/WEB-INF/postuler.jsp").forward(request, response);

                       }else{
                           //message erreur session selectionnée
                            PrintWriter out = response.getWriter();
                            out.println("probleme avec id session(session non ouverte ou inexistante)");
                            //message erreur session selectionnée
                       }
                   } catch (SQLException e) {
                       request.setAttribute("message", "Pb avec la base de données / check formation");
                       request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
                   }
               }else{

                   //message erreur session selectionnée
                   PrintWriter out = response.getWriter();
                   out.println("id formation invalide.");
                   //message erreur session selectionnée
               }

            }else{
                //message erreur session selectionnée
                   PrintWriter out = response.getWriter();
                   out.println("id session non précisé");
                   //message erreur session selectionnée
            }
            
        }else{
            //message erreur session selectionnée
                   PrintWriter out = response.getWriter();
                   out.println("pas connecté");
                   //message erreur session selectionnée
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if(request.getParameter("idsession") != null && session.getAttribute("user") != null) {
            try {
                int id_SessionFormation = Integer.parseInt(request.getParameter("idsession"));
                SessionFormationDao sFD = new SessionFormationDao();
                SessionFormation uneSession = sFD.findById(id_SessionFormation);
                Personne user = (Personne) session.getAttribute("user");
                EtatCandidature etatCdt = new EtatCandidature(1, "libelle");
                Candidature uneCandidature = new Candidature(user, uneSession, etatCdt);
                
                CandidatureDao candidatureDao = new CandidatureDao();
                
                candidatureDao.insert(uneCandidature);
                
                //manque envoi mail
                
                request.getRequestDispatcher("postulerOk.jsp").forward(request, response);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(PostulerServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "Pb avec la base de données / check formation");
                request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
            }
            
        }
        
        doGet(request, response);
    }
    
    public static boolean isParsable(String input){
        boolean parsable = true;
        try{
            Integer.parseInt(input);
        }catch(NumberFormatException e){
            parsable = false;
        }
        return parsable;

    }
}
