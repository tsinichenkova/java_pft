package pme.test;

import org.junit.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class PatientAppointment extends TestBase {

    @Test
    public void checkAppointment() throws Exception {
        String patientId = "1346324091";
//        удаление пациента из таблицы patient_appointment и medical_examination
        app.db().deletePatientAppointment(patientId);
        app.db().deleteMedicalExamination(patientId);

//        начало приема на UI
        app.search().closeWindow();
        app.search().searchPatient("770000 3044191067");
        app.careEvent().startCareEvent();

//        назначение на диспансеризацию и запись на прем
        app.dispanserization().startDispanserization();
        app.careEvent().gotoPrintScreen();
        app.careEvent().recordPatient();

//        проверка записи в таблицу patient_appointment
        app.db().checkPatientAppointment(patientId ,"2", "Цитологическое исследование мазка с шейки матки");
    }
}
