package pme.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import pme.utils.Utils;

public class Elasticsearch extends TestBase {

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            Utils.cancelCareEvent();
        }
    };

    @Test
    public void checkElasticsearch() throws Exception {
        String patientId = "16267353";
        String OMS = "770000 5140220567";
        String year = "2021";

//        удаление пациента из таблицы patient_turnout
        app.db().deletePatientTurnout(patientId);

//        начало приема на UI
        app.search().closeWindow();
        app.search().searchPatient(OMS);
        app.careEvent().startCareEvent();
        app.careEvent().sleep(2);

//        проверка записи в таблицу patient_turnout
        app.db().checkPatientTurnout(patientId ,"1");

//        проверка записи в эластик
        String result = app.http().checkElastic(year + "_" + patientId);
        Assert.assertTrue(result.contains("\"patientTurnout\":[{\"careEventId\""));
        Assert.assertTrue(result.contains("\"status\":\"ACTUAL\""));

//        завершение приема на UI
        app.careEvent().cancelCareEvent();
    }
    
}
