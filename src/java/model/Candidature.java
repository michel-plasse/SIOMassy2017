package model;

public class Candidature {
    private Personne personne;
    private SessionFomation sessionFomation;
    private EtatCandidature etatCandidature;

    public Candidature(Personne personne, SessionFomation sessionFomation, EtatCandidature etatCandidature) {
        this.personne = personne;
        this.sessionFomation = sessionFomation;
        this.etatCandidature = etatCandidature;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public SessionFomation getSessionFomation() {
        return sessionFomation;
    }

    public void setSessionFomation(SessionFomation sessionFomation) {
        this.sessionFomation = sessionFomation;
    }

    public EtatCandidature getEtatCandidature() {
        return etatCandidature;
    }

    public void setEtatCandidature(EtatCandidature etatCandidature) {
        this.etatCandidature = etatCandidature;
    }
        
    

}
