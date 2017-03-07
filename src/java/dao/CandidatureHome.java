package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface CandidatureHome<T> extends Dao<T> {
    //ajouter les methodes supplémentaires nécessaires à la Dao
    public ArrayList<HashMap<String, String>> mapCandidatures()throws SQLException;
}
