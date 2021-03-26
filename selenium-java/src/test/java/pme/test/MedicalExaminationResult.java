package pme.test;

import org.junit.Test;

public class MedicalExaminationResult extends TestBase {

    @Test
    public void checkMedicalExaminationResult() throws Exception {
        String patientId = "19438564";
//        удаление пациента из таблицы medical_examination
        app.db().deleteMedicalExaminationResult(patientId);

//        начало приема на UI, авторизация от Медсестра ОМП
        app.driver().get("http://doctor3.test.emias.mos.ru/web/etd-access-point?medicalEntityId=204&isNewLN=true&docSpecialityCode=69&medicalEmployeeJobInfoId=9406805045&roleId=7061&availableResourceId=16071283&newUI=true&complexResourceId=14341268");
        app.search().closeWindow();
        app.search().searchPatient("7754230875000453");
        app.careEvent().startCareEventOMP();

//        создание результата
        app.dispanserization().createResult();
        app.careEvent().stopCareEventForOMP();

//        проверка записи в таблицу medical_examination_result
        app.db().checkMedicalExaminationResult(patientId, "3");
        app.db().checkMedicalExaminationResult(patientId, "6");
    }
}
