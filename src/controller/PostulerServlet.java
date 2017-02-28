package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidatureDao;
import dao.CandidatureHome;
import model.Candidature;

/**
 * Servlet implementation class PostulerServlet
 */
@WebServlet("/postuler")
public class PostulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// A mettre dans le doPost()
		try {
			Candidature candidature = new Candidature();
			CandidatureHome dao = new CandidatureDao();
			dao.insert(candidature);
			response.sendRedirect("./");
		} catch (SQLException e) {
			request.setAttribute("msg", "Pb avec la base de données");
			request.getRequestDispatcher("WEB-INF/candidature.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
