/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Module;

/**
 *
 * @author admin
 */
public class ModuleDao implements ModuleHome {

    private Connection connection;

    @Override
    public void insert(Module objetAInserer) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int idAncien, Module nouveau) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Module findById(int id) throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        Module leModule = null;
        ResultSet res = stmt.executeQuery("SELECT id_module, nom FROM module"
                + " WHERE id_module = " + id);

        if (res.next()) {
            leModule = new Module(
                    res.getInt("id_module"),
                    res.getString("nom"));
        }
        return leModule; 
    }

    @Override
    public ArrayList<Module> findAll() throws SQLException {
        connection = ConnectionBd.getConnection();
        Statement stmt = connection.createStatement();
        ArrayList<Module> result = new ArrayList<>();
        ResultSet res = stmt.executeQuery("SELECT id_module, nom FROM module");

        while (res.next()) {
            Module leModule = new Module(
                    res.getInt("id_module"),
                    res.getString("nom"));
            result.add(leModule);
        }
        return result;
    }

}
