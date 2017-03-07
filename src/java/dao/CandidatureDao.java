package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Candidature;

public class CandidatureDao implements CandidatureHome<Candidature> {

    @Override
    public int insert(Candidature objetAInserer) throws SQLException {
        // TODO Auto-generated method stub
        Connection connection = ConnectionBd.getConnection();
        return 0;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(int idAncien, Candidature nouveau) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Candidature findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Candidature> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
