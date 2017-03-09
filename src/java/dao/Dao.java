package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<T> {

    public void insert(T objetAInserer) throws SQLException;

    public boolean delete(int id) throws SQLException ;

    public boolean update(int idAncien, T nouveau)throws SQLException ;

    public T findById(int id) throws SQLException ;

    public ArrayList<T> findAll()throws SQLException ;

}

//commit commit
