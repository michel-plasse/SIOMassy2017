/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import junit.framework.TestCase;
import model.Projet;

/**
 *
 * @author admin
 */
public class ProjetDaoTest extends  TestCase {
    public ProjetDaoTest (String testName){
        super(testName);
    }
    public void testInsert ()throws Exception{
        System.out.println("insert");
        Projet objetAInserer = null ;
        ProjetDao instance = new ProjetDao();
        instance.insert(objetAInserer);
        fail("the test case is a prototype.");
                
    }
    public void testDelete() throws SQLException{
        System.out.println("delete");
        int id = 0;
        ProjetDao instance = new ProjetDao();
        boolean expresult = false;
        boolean result = instance.delete(id);
        assertEquals(expresult, result);
        
                
    }
    public void testUpdate() throws SQLException{
        System.out.println("update");
        int idAncien=0;
        Projet nouveau= null;
        ProjetDao instance = new ProjetDao();
        boolean expResult = false;
        boolean result= instance.update(idAncien, nouveau);
        assertEquals(expResult, result);
        fail("the test case is a prototype.");
        
    }
    public void testFindById() throws SQLException{
        System.out.println("find by id");
        int id=0;
        ProjetDao instance = new ProjetDao();
        Projet expResult=null;
        Projet result=instance.findById(id);
        assertEquals(expResult, result);
        fail("the test case is a prototype.");
    }
    
}
