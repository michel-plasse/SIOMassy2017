package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;

import org.junit.Test;


import org.junit.Before;

import model.Personne;

public class PersonneDaoTest {

	@Test
	public void testFindById() throws SQLException {
		PersonneDao dao = new PersonneDao();
		Personne result = dao.findById(1);
		Personne expected = new Personne(1,"Leto",
				"Elias","leto@yahoo.com","12",
				"rue du chat","75001","Paris","France", "Jesuisunchat123!");
		assertEquals(expected, result);
	}

	@Test
	public void testInsert() throws SQLException {
		PersonneDao dao = new PersonneDao();
		Personne instance = new Personne(0,
				"Nouveau nom",
				"Nouveau prénom",
				"email@domaine.com",
				"n° de rue",
				"une rue",
				"12345",
				"Triffouillis les oies",
				"France", "Je sais faire miaou moi aussi");
		dao.insert(instance);
		assertEquals(9, instance.getId());
		Personne result = dao.findById(9);
		assertEquals(instance, result);
	}

	@Before
	public void resetDB() throws SQLException {
		Connection connection = ConnectionBd.getConnection();
		CallableStatement stmt = connection.prepareCall("CALL agriotes2017_reset()");
		stmt.executeUpdate();
	}
	
}
