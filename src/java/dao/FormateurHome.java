package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Personne;
import model.Formateur;

interface FormateurHome extends Dao <Formateur> {
	//Ajouter toutes les méthodes supplémentaires à la DAO (besoin en plus de la DAO seule)
	public int findIdFromToken(String toktok) throws SQLException;
        public boolean activeUser(int id) throws SQLException;
//        public ArrayList<Formateur> findBySession(int id) throws SQLException;
    
}
