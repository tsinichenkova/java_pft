package pme.appmanager;

import org.junit.Assert;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DbHelper {

    public Connection con;

    public DbHelper() {
    }

    public Connection createSession() throws Exception {
        Properties properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        //Connection URL
        String dbUrl = properties.getProperty("db.url");

        //Database Username
        String username = properties.getProperty("db.username");

        //Database Password
        String password = properties.getProperty("db.password");

        //Create Connection to DB
        con = DriverManager.getConnection(dbUrl, username, password);

        //Load mysql jdbc driver
        Class.forName("org.postgresql.Driver");

        return con;

    }

    public void checkPatientTurnout() throws Exception {
        con = createSession();
        Statement stmt = con.createStatement();

        String query = "select *  from patient_turnout where patient_id = 22658737";
        String queryCount = "select count (*)  from patient_turnout where patient_id = 22658737";

        ResultSet rsCount = stmt.executeQuery(queryCount);
        while (rsCount.next()) {
            Assert.assertEquals("count", "1", rsCount.getString(1));
        }

        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Assert.assertNotNull("careEventId", rs.getString(1));
            Assert.assertEquals("patientId", "22658737", rs.getString(2));
            Assert.assertNotNull("start_time", rs.getString(3));
            Assert.assertEquals("status", "1", rs.getString(4));
        }

        String deleteQuery = "delete from patient_turnout where patient_id = 22658737";
        stmt.execute(deleteQuery);

        con.close();
    }
}
