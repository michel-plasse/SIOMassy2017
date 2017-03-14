/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.HashMap;
import junit.framework.TestCase;
import model.Candidature;

/**
 *
 * @author Xavier Claude PASSER
 */
public class CandidatureDaoTest extends TestCase {
    
    public CandidatureDaoTest(String testName) {
        super(testName);
    }

    public void testInsert() throws Exception {
        System.out.println("insert");
        Candidature objetAInserer = null;
        CandidatureDao instance = new CandidatureDao();
        instance.insert(objetAInserer);
        fail("The test case is a prototype.");
    }

    public void testDelete() {
        System.out.println("delete");
        int id = 0;
        CandidatureDao instance = new CandidatureDao();
        boolean expResult = false;
        boolean result = instance.delete(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testUpdate() {
        System.out.println("update");
        int idAncien = 0;
        Candidature nouveau = null;
        CandidatureDao instance = new CandidatureDao();
        boolean expResult = false;
        boolean result = instance.update(idAncien, nouveau);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testFindById() {
        System.out.println("findById");
        int id = 0;
        CandidatureDao instance = new CandidatureDao();
        Candidature expResult = null;
        Candidature result = instance.findById(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testFindAll() {
        System.out.println("findAll");
        CandidatureDao instance = new CandidatureDao();
        ArrayList<Candidature> expResult = null;
        ArrayList<Candidature> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testMapCandidatures() throws Exception {
        System.out.println("mapCandidatures");
        CandidatureDao instance = new CandidatureDao();
        ArrayList<HashMap<String, String>> expResult = null;
        ArrayList<HashMap<String, String>> result = instance.mapCandidatures();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
