/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.qcm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import junit.framework.TestCase;
import model.Choix;
import model.Qcm;
import model.Question;

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
        int id = 1;
        QcmDao instance = new QcmDao();
        
        Choix choix11 = new Choix(1,"Léonidas " , false);
        Choix choix12 = new Choix(2,"Xénophon " , false);
        Choix choix13 = new Choix(3,"Néarque " , true);
        Choix choix21 = new Choix(4,"Persépolis " , false);
        Choix choix22 = new Choix(5,"Alexandrie " , false);
        Choix choix23 = new Choix(6,"Babylone" , true);
        Choix choix31 = new Choix(7,"1 000 de ses hommes" , false);
        Choix choix32 = new Choix(8,"10 000 de ses hommes" , true);
        Choix choix33 = new Choix(9,"300 de ses hommes" , false);
        Choix choix41 = new Choix(10,"Gaugamèles " , true);
        Choix choix42 = new Choix(11,"Gargamelle" , false);
        Choix choix43 = new Choix(12,"Pyrgotèle " , false);
        
        HashMap<Integer,Choix> leschoix1 = new HashMap<>();
        leschoix1.put(1, choix11);
        leschoix1.put(2, choix12);
        leschoix1.put(3, choix13);
        
        HashMap<Integer,Choix> leschoix2 = new HashMap<>();
        leschoix2.put(4, choix21);
        leschoix2.put(5, choix22);
        leschoix2.put(6, choix23);
        
        HashMap<Integer,Choix> leschoix3 = new HashMap<>();
        leschoix3.put(7, choix31);
        leschoix3.put(8, choix32);
        leschoix3.put(9, choix33);
        
        HashMap<Integer,Choix> leschoix4 = new HashMap<>();
        leschoix4.put(10, choix41);
        leschoix4.put(11, choix42);
        leschoix4.put(12, choix43);
        
        Question q1 = new Question(1, "L'amiral d'Alexandre le Grand qui explora les côtes du golfe Persique s'appelait:", leschoix1);
        Question q2 = new Question(2, "Alexandre le Grand mourut à:", leschoix2);
        Question q3 = new Question(3, "Lors des \" noces de Suse\", Alexandre le Grand organisa le mariage avec des Asiatiques de:", leschoix4);
        Question q4 = new Question(4, "Combien Alexandre le Grand a-t-il fondé de villes nommées Alexandrie?", leschoix3);

        ArrayList<Question> lesQuestions = new ArrayList<>();
        lesQuestions.add(q1);
        lesQuestions.add(q2);
        lesQuestions.add(q3);
        lesQuestions.add(q4);
        
        Qcm expResult = new Qcm(1, "\"Alexandre Le Grand\"", true, lesQuestions);
        expResult.setIdModule(3);
        Qcm result = instance.findById(id);
        
        assertEquals(expResult, result);
        
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
