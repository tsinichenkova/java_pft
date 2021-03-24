package pme.test;

import org.junit.Test;

public class MedicalExamination extends TestBase {

    @Test
    public void checkMedicalExamination() throws Exception {
        String patientId = "17022685";
//        удаление пациента из таблицы medical_examination
        app.db().deleteMedicalExamination(patientId);

//        начало приема на UI
        app.search().closeWindow();
        app.search().searchPatient("770200 9015130967");
        app.careEvent().startCareEvent();

//        назначение на диспансеризацию
        app.dispanserization().startDispanserization();
        app.careEvent().stopCareEvent();

//        проверка записи в таблицу medical_examination
        app.db().checkMedicalExamination(patientId);
    }
}
