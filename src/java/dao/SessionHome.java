package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.SessionFormation;

public interface SessionHome extends Dao<SessionFormation>{

	ArrayList<SessionFormation> getSessionsOuvertes() throws SQLException;
        public boolean isExistAndOpen(int id) throws SQLException;

}
