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

        //Load jdbc driver
        Class.forName("org.postgresql.Driver");

        return con;

    }

    public void checkPatientTurnout(String patientId, String status) throws Exception {
        con = createSession();
        Statement stmt = con.createStatement();

        String query = "select *  from patient_turnout where patient_id =" + patientId;
        String queryCount = "select count (*)  from patient_turnout where patient_id =" + patientId;

        ResultSet rsCount = stmt.executeQuery(queryCount);
        while (rsCount.next()) {
            Assert.assertEquals("count", "1", rsCount.getString(1));
        }

        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Assert.assertNotNull("careEventId", rs.getString(1));
            Assert.assertEquals("patientId", patientId, rs.getString(2));
            Assert.assertNotNull("start_time", rs.getString(3));
            Assert.assertEquals("status", status, rs.getString(4));
        }

        con.close();
    }

    public void deletePatientTurnout(String patientId) throws Exception {
        con = createSession();
        Statement stmt = con.createStatement();
        String deleteQuery = "delete from patient_turnout where patient_id =" + patientId;
        stmt.execute(deleteQuery);
        con.close();
    }

    public void deletePatientAppointment(String patientId) throws Exception {
        con = createSession();
        Statement stmt = con.createStatement();
        String deleteQuery = "delete from patient_appointment where patient_id =" + patientId;
        stmt.execute(deleteQuery);
        con.close();
    }

    public void deleteMedicalExamination(String patientId) throws Exception {
        con = createSession();
        Statement stmt = con.createStatement();
        String deleteQuery = "delete from medical_examination where patient_id =" + patientId;
        stmt.execute(deleteQuery);
        con.close();
    }

    public void checkPatientAppointment(String patientId, String type, String description) throws Exception {
        con = createSession();
        Statement stmt = con.createStatement();

        String query = "select *  from patient_appointment where patient_id =" + patientId;
        String queryCount = "select count (*)  from patient_appointment where patient_id =" + patientId;

        ResultSet rsCount = stmt.executeQuery(queryCount);
        while (rsCount.next()) {
            Assert.assertEquals("count", "1", rsCount.getString(1));
        }

        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Assert.assertEquals("patientId", patientId, rs.getString(1));
            Assert.assertNotNull("appointment_id", rs.getString(2));
            Assert.assertNotNull("start_time", rs.getString(3));
            Assert.assertEquals("type", type, rs.getString(4));
            Assert.assertEquals("description", description, rs.getString(5));
        }

        con.close();
    }

    public void checkMedicalExamination(String patientId) throws Exception {
        con = createSession();
        Statement stmt = con.createStatement();

        String query = "select *  from medical_examination where patient_id =" + patientId;
        String queryCount = "select count (*)  from medical_examination where patient_id =" + patientId;

        ResultSet rsCount = stmt.executeQuery(queryCount);
        while (rsCount.next()) {
            Assert.assertNotEquals("count", "0", rsCount.getString(1));
        }

        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Assert.assertNotNull("id", rs.getString(1));
            Assert.assertNotNull("checkup_id", rs.getString(2));
            Assert.assertNotNull("create_date", rs.getString(3));
            Assert.assertNull("cancel_date", rs.getString(4));
            Assert.assertNotNull("composition_id", rs.getString(5));
            Assert.assertNotNull("document_id", rs.getString(6));
            Assert.assertEquals("patientId", patientId, rs.getString(7));
            Assert.assertNotNull("checkup_package_id", rs.getString(8));
        }

        con.close();
    }
}
