/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.equipe;

import dao.EquipeDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Equipe;
import model.Personne;
import model.Projet;

/**
 *
 * @author ghisfix
 */
@WebServlet(name = "GererEquipeServlet", urlPatterns = {"/equipe/gerer"})
public class GererEquipeServlet extends HttpServlet {
    
    public static final String VUE = "/WEB-INF/equipe/gerer.jsp";
    public static final String PARAM_ID = "id";
    public static final String PARAM_USER = "user";
    public static final String ATT_EQUIPE = "equipeGeree";
    public static final String ATT_STAGIAIRES_LIBRES = "stagiairesSansEquipe";
    public static final String ATT_TITLE = "title";
    public static final String ATT_TITLE_VALUE = "Gérer votre equipe";
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //HashMap<String,String[]> messages = new HashMap<String, String[]>();
        
        HttpSession session = request.getSession();
        
        if(request.getParameter(PARAM_ID) != null && session.getAttribute(PARAM_USER) != null) {
            Integer idEquipe = null;
            try {
                idEquipe = Integer.parseInt(request.getParameter(PARAM_ID));
            }catch (NumberFormatException e) {
                System.out.println("id equipe non valide : " + e);
            }
            
            if(idEquipe != null) {
                Personne createurEquipe = (Personne) session.getAttribute(PARAM_USER);
                EquipeDao equipeDao = new EquipeDao();
                Equipe equipeAdministree = null;
                try {
                    equipeAdministree = equipeDao.findById(idEquipe);
                    System.out.println("récup equipe : ok...");
                } catch (SQLException ex) {
                    Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (equipeAdministree != null) {
                    if(equipeAdministree.getCreateur().getId() == createurEquipe.getId()) {
                        ArrayList<Personne> stagiairesSansEquipe = null;
                        try {
                            stagiairesSansEquipe = equipeDao.findAllNotInTeam(equipeAdministree.getUnProjet());
                        } catch (SQLException ex) {
                            Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        request.setAttribute(ATT_EQUIPE, equipeAdministree);
                        request.setAttribute(ATT_STAGIAIRES_LIBRES, stagiairesSansEquipe);
                        request.setAttribute(ATT_TITLE, ATT_TITLE_VALUE);
                        
                        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
                        
                    }
                    
                }
                
            }
            
 
        }
        
        response.sendError(404);
 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        PrintWriter out = response.getWriter();
//        out.print(request.getParameter("modifier"));
//        out.print("...");
//        out.print(request.getParameter("supprimer"));
//        out.print("...");
//       
          HttpSession session = request.getSession();
          
          if(request.getParameter("create") != null) {
              Integer idProjet = null;
              
              try {
                      idProjet = Integer.parseInt(request.getParameter("idProjet"));
                      
              } catch (NumberFormatException ex) {
                 Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
              }
              
              if (idProjet != null) {
                EquipeDao equipeDao = new EquipeDao();
                Projet projet = new Projet(idProjet);
                Personne createurNv = (Personne) session.getAttribute(PARAM_USER);
                boolean utilisateurValide = false;
                
                //*******/!\ foireux, pour check si user valide et sans equipe****
                ArrayList<Personne> stagiairesSansEquipe = null;
                  try {
                      stagiairesSansEquipe = equipeDao.findAllNotInTeam(projet);
                  } catch (SQLException ex) {
                      Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
                for(Personne stagiaire : stagiairesSansEquipe) {
                    if(stagiaire.getId() == createurNv.getId()){
                        utilisateurValide = true;
                    }
                }
                //****************/!\foireux*******************
                
                if (utilisateurValide) {
                    Equipe nouvelleEquipe = new Equipe();
                    nouvelleEquipe.setCreateur(createurNv);
                    nouvelleEquipe.setUnProjet(projet);
                    Integer idEquipeNv = null;
                    
                    try {
                        idEquipeNv = equipeDao.insertReturnId(nouvelleEquipe);
                    } catch (SQLException ex) {
                        Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (idEquipeNv != null) {
                        response.sendRedirect(this.getServletContext().getContextPath() + "/equipe/gerer?id=" + idEquipeNv);
                    }
                }
              }
          }
          
          if(request.getParameter(PARAM_ID) != null && session.getAttribute(PARAM_USER) != null) {
            Integer idEquipe = null;
            try {
                idEquipe = Integer.parseInt(request.getParameter(PARAM_ID));
            }catch (NumberFormatException e) {
                System.out.println("id equipe non valide : " + e);
            }
            
            
            if (idEquipe != null ) {
                EquipeDao equipeDao = new EquipeDao();
                Equipe equipe = null;
                
                try {
                    equipe = equipeDao.findById(idEquipe);
                } catch (SQLException ex) {
                    Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (equipe != null) {
                    
                    Personne createur = (Personne) session.getAttribute(PARAM_USER);

                    if (equipe.getCreateur().getId() == createur.getId()) {

                        if (request.getParameter("modifier") != null) {
                            int compteurAjout = 0;
                            int compteurSupp = 0;
                            String[] ajouts = request.getParameterValues("add");
                            String[] suppressions = request.getParameterValues("delete");
                            //ajout de nouveaux membres
                            if(ajouts != null) {
                                for(String ajout : ajouts) {
                                    try {
                                        equipeDao.ajouterMembre(idEquipe, Integer.parseInt(ajout));
                                        compteurAjout ++;
                                    } catch  (NumberFormatException | SQLException ex) {
                                        Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } 
                            }
                            //retirer des memnres de l'equipe
                            if (suppressions != null) {
                                for(String suppression : suppressions) {
                                    try {
                                        equipeDao.retirerMembre(idEquipe, Integer.parseInt(suppression));
                                        compteurSupp ++;
                                    } catch (NumberFormatException | SQLException ex) {
                                        Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            //message feedback ? envoi compteur?
                            request.setAttribute("compteurAjout", compteurAjout);
                            request.setAttribute("compteurSupp", compteurSupp);

                            response.sendRedirect(this.getServletContext().getContextPath() + "/equipe/gerer?id="+idEquipe);
                        }

                        if(request.getParameter("supprimer") != null) {
                            try {
                                equipeDao.delete(idEquipe);
                            } catch (SQLException ex) {
                                Logger.getLogger(GererEquipeServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            //message confirmation?
                            String idProjet = request.getParameter("idProjet");

                            response.sendRedirect(this.getServletContext().getContextPath() + "/equipe/index?id_projet="+idProjet);
                        }

                    }
                }
            }
          }

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
