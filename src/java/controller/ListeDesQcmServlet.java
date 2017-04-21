/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ModuleDao;
import dao.qcm.QcmDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Module;
import model.Personne;
import model.Qcm;

/**
 *
 * @author thomas
 */
@WebServlet(name = "ListeDesQcmServlet", urlPatterns = {"/ListeDesQcmServlet"})
public class ListeDesQcmServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListeDesQcmServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListeDesQcmServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Personne user = (Personne) request.getSession(true).getAttribute("user");
        QcmDao qcmDao = new QcmDao();
        ModuleDao moduleDao = new ModuleDao();
        
        
        if (!user.isEst_formateur()){
            System.out.println("pas formateur");
            request.setAttribute("message", "Vous n'êtes pas un formateur : vous ne pouvez pas créer une évaluation");
            request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
        }
        else if (user == null){
            System.out.println("pas connecté");
            response.sendRedirect("login");
        }
        else{
            try {
                System.out.println(user.getId());
                ArrayList<Qcm> lesQcm =qcmDao.findAllByFormateur(user.getId());
                ArrayList<Module> modules = moduleDao.findAll();
                
                request.setAttribute("lesQcm", lesQcm);
                request.setAttribute("modules", modules);
                
                request.getRequestDispatcher("/WEB-INF/listeDesQcm.jsp").forward(request, response);
                
            } catch (SQLException ex) {
            Logger.getLogger(ListeDesQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Personne user = (Personne) request.getSession(true).getAttribute("user");
        QcmDao qcmDao = new QcmDao();
        int idModule = Integer.parseInt(request.getParameter("idModule"));
        String intituleQcm = request.getParameter("intitule");
        Qcm nouveauQcm = new Qcm();
        nouveauQcm.setIntitule(intituleQcm);
        nouveauQcm.setValide(false);
        int idGenere = -1;
        
        try {
            idGenere = qcmDao.insert(user.getId(), idModule, nouveauQcm);
            System.out.println("nouveau qcm créé !");
        } catch (SQLException ex) {
            Logger.getLogger(ListeDesQcmServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(this.getServletContext().getContextPath()+"creerQcm?idQcm="+idGenere);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
