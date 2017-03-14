package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionBdTest {

	@Test
	public void test() throws SQLException {
		Connection result = ConnectionBd.getConnection();
		assertNotNull(result);
	}

}
