package dao;

import java.util.ArrayList;

public interface Dao<T> {
	
	public int insert(T objetAInserer);
	public boolean delete(int id);
	public boolean update(int idAncien, T nouveau);
	public T findById(int id);
	public ArrayList<T> findAll();
	
}

//commit commit