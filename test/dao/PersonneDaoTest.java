package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.ArrayList;

import org.junit.Test;

import org.junit.Before;

import model.Personne;
import model.TokenGenerator;

public class PersonneDaoTest {

    @Before
    public void resetDB() throws SQLException {
        Connection connection = ConnectionBd.getConnection();
        CallableStatement stmt = connection.prepareCall("CALL agriotes2017_reset()");
        stmt.executeUpdate();
    }

    @Test
    public void testFindById() throws SQLException {
        PersonneDao dao = new PersonneDao();
        Personne result = dao.findById(1);
        Personne expected = new Personne(1, "Leto",
                "Elias", "leto@yahoo.com", "12",
                "rue du chat", "75001", "Paris", "France", "Jesuisunchat123!");
        assertEquals(expected, result);
    }

    @Test
    public void testInsert() throws SQLException {
        PersonneDao dao = new PersonneDao();
        Personne instance = new Personne(0,
                "Nouveaunom",
                "Nouveauprénom",
                "email@domaine.com",
                "1",
                "une rue",
                "12345",
                "Triffouillis les oies",
                "France", "Je sais faire miaou moi aussi", "0123456789");
        TokenGenerator token = new TokenGenerator();
        instance.setToken(token.Token());
        dao.insert(instance);
        assertEquals(110, instance.getId());
        Personne result = dao.findById(110);
        assertEquals(instance, result);
    }

    public void testFindAll() throws Exception {
        System.out.println("findAll");
        PersonneDao instance = new PersonneDao();
        ArrayList<Personne> expResult = null;
        ArrayList<Personne> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testUpdate() throws Exception {
        System.out.println("update");
        int ancien = 0;
        Personne nouveau = null;
        PersonneDao instance = new PersonneDao();
        boolean expResult = false;
        boolean result = instance.update(ancien, nouveau);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = 0;
        PersonneDao instance = new PersonneDao();
        boolean expResult = false;
        boolean result = instance.delete(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetByLoginPassword() throws Exception {
        PersonneDao dao = new PersonneDao();
        Personne result = dao.getByLoginPassword("leto@yahoo.com", "p123");
        Personne expected = new Personne(1, "Leto",
                "Elias", "leto@yahoo.com", "12",
                "rue du chat", "75001", "Paris", "France", "p123");
        assertEquals(expected, result);
    }

    @Test
    public void testGetByLoginPasswordPasTrouve() throws Exception {
        PersonneDao dao = new PersonneDao();
        Personne result = dao.getByLoginPassword("leto@yahoo.com", "incorrect password");
        assertNull(result);
    }
}
