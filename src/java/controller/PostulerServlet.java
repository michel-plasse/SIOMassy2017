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
import javax.servlet.http.HttpSession;
import model.Candidature;

/**
 * Servlet implementation class PostulerServlet
 */
@WebServlet("/postuler")
public class PostulerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        //pour tester
	session.setAttribute("user", "pouet");
       
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
        // TODO Auto-generated method stub
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
