package dao;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xavier Claude PASSER
 */
public class ConnectionBdTest {
    
    public ConnectionBdTest() {
    }

    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        Connection result = ConnectionBd.getConnection();
        assertNotNull(result);
    }
    
}
