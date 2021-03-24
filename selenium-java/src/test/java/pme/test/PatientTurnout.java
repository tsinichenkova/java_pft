package pme.test;

import org.junit.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class PatientTurnout extends TestBase {

    @Test
    public void checkCreatePatientTurnout() throws Exception {
//        удаление пациента из таблицы patient_turnout
        app.db().deletePatientTurnout("22658737");

//        начало приема на UI
        app.search().closeWindow();
        app.search().searchPatient("770150 3980341455");
        app.careEvent().startCareEvent();
        app.careEvent().sleep(2);

//        проверка записи в таблицу patient_turnout
        app.db().checkPatientTurnout("22658737" ,"1");

//        завершение приема на UI
        app.careEvent().cancelCareEvent();
    }

    @Test
    public void checkCanceledPatientTurnout() throws Exception {
//        удаление пациента из таблицы patient_turnout
        app.db().deletePatientTurnout("22658737");

//        начало приема на UI и отмена приема
        app.search().closeWindow();
        app.search().searchPatient("770150 3980341455");
        app.careEvent().startCareEvent();
        app.careEvent().cancelCareEvent();

//        проверка записи в таблицу patient_turnout
        app.db().checkPatientTurnout("22658737", "0");
    }

    @AfterMethod
    public static void checkStatus(ITestResult result) {
        if (result.isSuccess()) {
            return;
        } else {
            // добавить завершение приема
        }
    }


}
