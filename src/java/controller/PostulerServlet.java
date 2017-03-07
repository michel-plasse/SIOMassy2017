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
import javax.servlet.http.HttpSession;
import model.Candidature;

/**
 * Servlet implementation class PostulerServlet
 */
@WebServlet("/postuler")
public class PostulerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // A mettre dans le doPost()
//        try {
//            Candidature candidature = new Candidature();
//            CandidatureHome dao = new CandidatureDao();
//            dao.insert(candidature);
//            response.sendRedirect("./");
//        } catch (SQLException e) {
//            request.setAttribute("message", "Pb avec la base de données");
//            request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
//        }
        HttpSession session = request.getSession();
       
        if(session.getAttribute("user") != null) {
            // Utilisateur connecté
            String idSession = request.getParameter("idSessionFormation");
            if(idSession != null && ) {
                
               String paramSessionFormation = request.getParameter("idSessionFormation");
               if(isParsable(paramSessionFormation)){
                   int id_SessionFormation = Integer.parseInt(paramSessionFormation);
                   
               }else{
                   //message erreur session selectionnée
               }

               SessionFormationDao sFD = new SessionFormationDao();

               if(sFD.isExistAndOpen(id_SessionFormation)) {
                   CandidatureDao cD = new CandidatureDao();
                   Candidature candidature = new Candidature()
                   cD.insert(objetAInserer)
               }


            }
        }else{
            //blabla
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
