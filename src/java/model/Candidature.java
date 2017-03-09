package model;

public class Candidature {
    private Personne personne;
    private SessionFormation sessionFomation;
    private EtatCandidature etatCandidature;

    public Candidature(Personne personne, SessionFormation sessionFomation, EtatCandidature etatCandidature) {
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

    public SessionFormation getSessionFomation() {
        return sessionFomation;
    }

    public void setSessionFomation(SessionFormation sessionFomation) {
        this.sessionFomation = sessionFomation;
    }

    public EtatCandidature getEtatCandidature() {
        return etatCandidature;
    }

    public void setEtatCandidature(EtatCandidature etatCandidature) {
        this.etatCandidature = etatCandidature;
    }
        
    

}
