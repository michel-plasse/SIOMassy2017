package model;

import java.sql.Date;
import java.util.Objects;

public class SessionFormation {

    private int id_session;
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private String lieu;
    private boolean est_ouverte;

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public boolean isEst_ouverte() {
        return est_ouverte;
    }

    public void setEst_ouverte(boolean est_ouverte) {
        this.est_ouverte = est_ouverte;
    }

    public SessionFormation() {

    }

    public SessionFormation(int id_session, String nom, Date dateDebut, Date dateFin, String lieu, boolean est_ouverte) {
        this.id_session = id_session;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.est_ouverte = est_ouverte;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.id_session;
        hash = 73 * hash + Objects.hashCode(this.nom);
        hash = 73 * hash + Objects.hashCode(this.dateDebut);
        hash = 73 * hash + Objects.hashCode(this.dateFin);
        hash = 73 * hash + Objects.hashCode(this.lieu);
        hash = 73 * hash + (this.est_ouverte ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SessionFormation other = (SessionFormation) obj;
        if (this.id_session != other.id_session) {
            return false;
        }
        if (this.est_ouverte != other.est_ouverte) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.dateFin, other.dateFin)) {
            return false;
        }
        return true;
    }

}
