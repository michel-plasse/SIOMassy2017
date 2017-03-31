/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import junit.framework.TestCase;
import model.SessionFormation;

/**
 *
 * @author admin
 */
public class SessionFormationDaoTest extends TestCase {
    
    public void testGetSessionsOuvertes() throws Exception {
        System.out.println("getSessionsOuvertes");
        SessionFormationDao instance = new SessionFormationDao();
        ArrayList<SessionFormation> expResult = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDebut = new Date(df.parse("2017-05-01").getTime());
        Date dateFin = new Date(df.parse("2018-04-15").getTime());
        expResult.add(new SessionFormation(1, "BTS SIO", "Ceci est une description", dateDebut, dateFin, true));
        ArrayList<SessionFormation> result = instance.getSessionsOuvertes();
        assertEquals(expResult, result);
    }

}
