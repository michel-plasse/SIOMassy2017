package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<T> {

    public int insert(T objetAInserer) throws SQLException;

    public boolean delete(int id);

    public boolean update(int idAncien, T nouveau);

    public T findById(int id);

    public ArrayList<T> findAll();

}

//commit commit
