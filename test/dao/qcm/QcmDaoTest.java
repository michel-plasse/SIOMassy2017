/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.qcm;

import java.util.ArrayList;
import java.util.HashSet;
import junit.framework.TestCase;
import model.Qcm;

/**
 *
 * @author admin
 */
public class QcmDaoTest extends TestCase {
    
    public QcmDaoTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testFindAll() throws Exception {
        System.out.println("findAll");
        QcmDao instance = new QcmDao();
        ArrayList<Qcm> expResult = null;
        ArrayList<Qcm> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testInsert_3args() throws Exception {
        System.out.println("insert");
        int idFormateur = 0;
        int idModule = 0;
        Qcm nouveauQcm = null;
        QcmDao instance = new QcmDao();
        int expResult = 0;
        int result = instance.insert(idFormateur, idModule, nouveauQcm);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = 0;
        QcmDao instance = new QcmDao();
        boolean expResult = false;
        boolean result = instance.delete(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testUpdate() throws Exception {
        System.out.println("update");
        int idAncien = 0;
        Qcm nouveau = null;
        QcmDao instance = new QcmDao();
        boolean expResult = false;
        boolean result = instance.update(idAncien, nouveau);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testFindById() throws Exception {
        System.out.println("findById");
        int id = 0;
        QcmDao instance = new QcmDao();
        Qcm expResult = null;
        Qcm result = instance.findById(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testInsert_Qcm() throws Exception {
        System.out.println("insert");
        Qcm objetAInserer = null;
        QcmDao instance = new QcmDao();
        instance.insert(objetAInserer);
        fail("The test case is a prototype.");
    }

    public void testInsertPassage() throws Exception {
        System.out.println("insertPassage");
        int idUser = 0;
        Qcm qcmRepsChoisies = null;
        QcmDao instance = new QcmDao();
        instance.insertPassage(idUser, qcmRepsChoisies);
        fail("The test case is a prototype.");
    }

    public void testFindAnsByIdPassage() throws Exception {
        System.out.println("findAnsByIdPassage");
        int idUser = 0;
        int idQcm = 0;
        QcmDao instance = new QcmDao();
        HashSet<Integer> expResult = null;
        HashSet<Integer> result = instance.findAnsByIdPassage(idUser, idQcm);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testIsAlreadyDone() throws Exception {
        System.out.println("isAlreadyDone");
        int idUser = 0;
        int idQcm = 0;
        QcmDao instance = new QcmDao();
        int expResult = 0;
        int result = instance.isAlreadyDone(idUser, idQcm);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testRendValideQcm() throws Exception {
        System.out.println("rendValideQcm");
        int idQcm = 0;
        QcmDao instance = new QcmDao();
        boolean expResult = false;
        boolean result = instance.rendValideQcm(idQcm);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testRendArchiveQcm() throws Exception {
        System.out.println("rendArchiveQcm");
        int idQcm = 0;
        QcmDao instance = new QcmDao();
        boolean expResult = false;
        boolean result = instance.rendArchiveQcm(idQcm);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testFindAlreadyDoneForPersonne() throws Exception {
        System.out.println("findAlreadyDoneForPersonne");
        int idPersonne = 0;
        QcmDao instance = new QcmDao();
        ArrayList<Qcm> expResult = null;
        ArrayList<Qcm> result = instance.findAlreadyDoneForPersonne(idPersonne);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testFindAllNotDone() throws Exception {
        System.out.println("findAllNotDone");
        int idPersonne = 0;
        QcmDao instance = new QcmDao();
        ArrayList<Qcm> expResult = null;
        ArrayList<Qcm> result = instance.findAllNotDone(idPersonne);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
