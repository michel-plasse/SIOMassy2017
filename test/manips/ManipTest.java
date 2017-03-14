/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manips;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author AveigA
 */
public class ManipTest {

    @Test
    public void testJoin() {
        String[] conditions = {"nom LIKE 'A%'", "date_effet='2017-04-10'"};
        String expected = "nom LIKE 'A%' AND date_effet='2017-04-10'";
        String result = String.join(" AND ", conditions);
        assertEquals(expected, result);
    }

    @Test
    public void testJoinVide() {
        ArrayList<String> conditionsVides = new ArrayList<>();
        assertEquals("", String.join(" AND ", conditionsVides));
    }
}
