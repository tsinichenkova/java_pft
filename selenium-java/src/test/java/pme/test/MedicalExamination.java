package pme.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import pme.utils.Utils;

public class MedicalExamination extends TestBase {

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            Utils.cancelCareEvent();
        }
    };

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

    @Test
    public void cancelMedicalExamination() throws Exception {
        String patientId = "117323466";
//        удаление пациента из таблицы medical_examination
        app.db().deleteMedicalExamination(patientId);

//        начало приема на UI
        app.search().closeWindow();
        app.search().searchPatient("770000 5020021067");
        app.careEvent().startCareEvent();

//        назначение на диспансеризацию
        app.dispanserization().startDispanserization();
        app.careEvent().stopCareEvent();

//        отмена диспансеризации
        app.search().searchPatient("770000 5020021067");
        app.careEvent().startCareEvent();
        app.dispanserization().cancelExamination();
        app.careEvent().stopCareEventForSign();

//        проверка записи в таблицу medical_examination
        app.db().checkCancelMedicalExamination(patientId, "2");
    }
}
