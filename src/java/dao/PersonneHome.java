package dao;

import java.sql.SQLException;
import model.Personne;

public interface PersonneHome extends Dao <Personne> {
	//Ajouter toutes les méthodes supplémentaires à la DAO (besoin en plus de la DAO seule)
	public int findIdFromToken(String toktok) throws SQLException;
        public boolean activeUser(int id) throws SQLException;
        
	

}
