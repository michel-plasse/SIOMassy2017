package dao;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xavier Claude PASSER
 */
public class ConnectionBdOldTest {
    
    public ConnectionBdOldTest() {
    }

    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        Connection result = ConnectionBdOld.getConnection();
        assertNotNull(result);
    }
    
}
