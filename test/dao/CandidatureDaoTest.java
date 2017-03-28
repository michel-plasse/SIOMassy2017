/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Candidature;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
/**
 *
 * @author Xavier Claude PASSER
 */
public class CandidatureDaoTest{
    
    @Before
    public void resetDB() throws SQLException {
        Connection connection = ConnectionBd.getConnection();
        CallableStatement stmt = connection.prepareCall("CALL agriotes2017_reset()");
        stmt.executeUpdate();
    }

    public void testInsert() throws Exception {
        System.out.println("insert");
        Candidature objetAInserer = null;
        CandidatureDao instance = new CandidatureDao();
        instance.insert(objetAInserer);
        fail("The test case is a prototype.");
    }

    public void testDelete() throws SQLException {
        System.out.println("delete");
        int id = 0;
        CandidatureDao instance = new CandidatureDao();
        boolean expResult = false;
        boolean result = instance.delete(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testUpdate() throws SQLException {
        System.out.println("update");
        int idAncien = 0;
        Candidature nouveau = null;
        CandidatureDao instance = new CandidatureDao();
        boolean expResult = false;
        boolean result = instance.update(idAncien, nouveau);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testFindById() throws SQLException {
        System.out.println("findById");
        int id = 0;
        CandidatureDao instance = new CandidatureDao();
        Candidature expResult = null;
        Candidature result = instance.findById(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testFindAll() throws SQLException {
        System.out.println("findAll");
        CandidatureDao instance = new CandidatureDao();
        ArrayList<Candidature> expResult = null;
        ArrayList<Candidature> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testMapCandidatures() throws Exception {
        ArrayList<String> conditions = new ArrayList<>();        
        
        CandidatureDao dao = new CandidatureDao();
        ArrayList<HashMap<String, String>> result = dao.mapCandidatures(conditions);
        
        assertEquals(7, result.size());
        
    }
   
}
