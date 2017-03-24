package model;

public class Personne {
	private int id;
	private String email;	
	private String nom;
	private String prenom;
	private String no_rue;
	private String rue;
	private String code_postal;
	private String ville;
	private String pays;
	private String mot_de_passe;
	private String token;
        private boolean inscriptionValide;
        private String photo;

    public boolean isInscriptionValide() {
        return inscriptionValide;
    }

    public void setInscriptionValide(boolean inscriptionValide) {
        this.inscriptionValide = inscriptionValide;
    }

    public Personne(int id, String nom, String prenom, String email, String no_rue, String nom_rue, String code_postal, String ville, String pays, String password) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.no_rue = no_rue;
        this.rue = nom_rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.pays = pays;
        this.mot_de_passe = password;
    }

    public Personne(int id, String email, String nom, String prenom, String no_rue, String rue, String code_postal, String ville, String pays, String mot_de_passe, String photo) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.no_rue = no_rue;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.pays = pays;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
    }
    
   


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNo_rue() {
		return no_rue;
	}


	public void setNo_rue(String no_rue) {
		this.no_rue = no_rue;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCode_postal() {
		return code_postal;
	}


	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}


	public String getMot_de_passe() {
		return mot_de_passe;
	}


	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}
        public String getToken() {
		return token;
	}

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
        
        
	
	
	public void setToken(String token) {
		this.token = token;
	}
        

	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", no_rue=" + no_rue + ", rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville + ", pays=" + pays + ", mot_de_passe=" + mot_de_passe + ", token=" + token + ", inscriptionValide=" + inscriptionValide + ", photo=" + photo + '}';
    }





	
	

}
