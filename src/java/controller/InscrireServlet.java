package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TokenGenerator;

import dao.PersonneDao;
import java.io.PrintWriter;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import model.Personne;
import outils.ServeurSMTP;

@WebServlet("/inscrire")
public class InscrireServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/inscrire.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean champsrenseignes = true;

        // Email & PW
        String email = request.getParameter("email");
        String emailcheck = request.getParameter("email2");
        String password = request.getParameter("password");
        String passwordcheck = request.getParameter("password2");

        // Nom
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String no_phone = request.getParameter("no_phone");

        // Adresse
        String no_rue = request.getParameter("no_rue");
        String nom_rue = request.getParameter("nom_rue");
        String code_postal = request.getParameter("code_postal");
        String ville = request.getParameter("ville");
        String pays = request.getParameter("pays");

        request.setAttribute("leNom", nom);
        request.setAttribute("lePrenom", prenom);

        if (nom.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("nom", "Veuillez entrer votre nom.");
            System.out.println("Rentre dans if condition");
        }
        if (prenom.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("prenom", "Veuillez entrer votre prénom.");
        }
        if (no_phone.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("no_phone", "Veuillez entrer votre numéro de téléphone.");
        }
        if (email.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("email", "Veuillez entrer votre email.");
        }
        if (emailcheck.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("email2", "Veuillez confirmer votre email.");
        }
        if (password.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("password", "Veuillez entrer votre mot de passe.");
        }
        if (passwordcheck.isEmpty()) {
            champsrenseignes = false;
            request.setAttribute("password2", "Veuillez confirmer votre mot de passe.");
        }
        if (!password.equals(passwordcheck)) {
            champsrenseignes = false;
            request.setAttribute("password2", "Le mot de passe entré ne correspond pas.");
        }
        if (!email.equals(emailcheck)) {
            champsrenseignes = false;
            request.setAttribute("email2", "L'email entré ne correspond pas.");
        }

        Personne personneAjoutee = new Personne(0, nom, prenom, email, no_rue, nom_rue, code_postal, ville,
                pays, password, no_phone);
        request.setAttribute("personne", personneAjoutee);

        // Sinon envoi Personne à BDD
        if (champsrenseignes) {
            try {

                PersonneDao dao = new PersonneDao();
                TokenGenerator token = new TokenGenerator();
                personneAjoutee.setToken(token.Token());
                request.setAttribute("leToken", personneAjoutee.getToken());
                dao.insert(personneAjoutee);
                //  envoyerMail(personneAjoutee);
//              PrintWriter out = response.getWriter();
//              out.print(personneAjoutee.getEmail() +" : "+ personneAjoutee.getToken());
                request.getRequestDispatcher("/WEB-INF/inscrireOk.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("message", e.getMessage());
                //request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
                request.getRequestDispatcher("/WEB-INF/inscrire.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/inscrire.jsp").forward(request, response);
        }
        /*
             catch (MessagingException exc) {
                request.setAttribute("message", "Votre inscription est enregistrée, mais pb pour vous le confirmer par mail");
                request.getRequestDispatcher("WEB-INF/message.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/inscrire.jsp").forward(request, response);
        }*/

//Abandon de la solution mail cause proxy.
        /*public void envoyerMail(Personne personne) throws MessagingException {
        String from = "greta@gmail.com";
        String to = personne.getEmail();
        String subject = "Votre inscription sur Agriotes.";
        String token = personne.getToken();
        int id = personne.getId();
        String body = "é€â Veuillez confirmer votre inscription en cliquant sur : "
                + "<a href='http://localhost/SIOMassy2017/confirmerInscription?token="+token+"'>confirmer</a>";
        MimeMessage mail = ServeurSMTP.newEmail(from, to, subject);
        mail.setContent(body, "text/html; charset=utf-8");
        javax.mail.Transport.send(mail);
    }*/
    }
}
