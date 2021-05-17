package pme.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import pme.utils.Utils;

public class PatientTurnout extends TestBase {

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            Utils.cancelCareEvent();
        }
    };


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

    @Test
    public void checkGetPackage() throws Exception {
//        начало приема на UI
        app.search().closeWindow();
        app.search().searchPatient("7700008099270138");
        app.careEvent().startCareEvent();
        app.careEvent().sleep(2);

//        проверка метода getPackage
        String content = Utils.getContent("getPackage");
        System.out.println(content);
        Assert.assertTrue(content.contains("{\"checkupPackage\":{\"id\":148,\"name\":\"Диспансеризация женщин 83 года (1 этап)\",\"packageType\":\"DISPANSERIZATION\",\"checkups\":[{\"id\":2,\"name\":\"Измерение роста, массы тела, окружности талии и расчет ИМТ\",\"checkupType\":\"CONSULTATION\",\"content\":\"{\\\"diagnosisCode\\\": \\\"Z00\\\", \\\"diagnosisName\\\": \\\"Общий осмотр и обследование лиц, не имеющих жалоб или установленного диагноза\\\", \\\"survey\\\": \\\"Рост, вес, окружность талии, индекс массы тела\\\", \\\"priorityId\\\": 8, \\\"priorityName\\\": \\\"Свой период\\\", \\\"lastMonthOfValidity\\\": 12, \\\"specialityName\\\": \\\"Медицинская сестра (медбрат)\\\", \\\"specializationId\\\": \\\"96\\\", \\\"receptionType\\\": \\\"777\\\", \\\"groupNumber\\\": \\\"1\\\", \\\"groupName\\\": \\\"Доврачебный проф. осмотр\\\", \\\"specialty\\\": \\\"70\\\", \\\"manual\\\": \\\"Подготовка не требуется\\\"}\",\"optional\":true,\"cctExamination\":null,\"examinationDocumentId\":null,\"examinationResultDocumentId\":null,\"examinationCreateDate\":null,\"examinationResultCreateDate\":null,\"status\":\"incomplete\",\"transferExaminationDocumentId\":null,\"transferExaminationCreateDate\":null,\"transfer\":false,\"partialPreconditionResult\":[]},"));

//        завершение приема на UI
        app.careEvent().cancelCareEvent();
    }

    @Test
    public void checkGetPreventiveMedicalExaminationResultByPatient() throws Exception {
//        начало приема на UI
        app.search().closeWindow();
        app.search().searchPatient("7700008099270138");
        app.careEvent().startCareEvent();
        app.careEvent().sleep(2);

//        проверка метода getPreventiveMedicalExaminationResultByPatient
        String content = Utils.getContent("getPreventiveMedicalExaminationResultByPatient");
        System.out.println(content);
        Assert.assertTrue(content.contains("\"preventiveMedicalExamination\":{\"patientId\":10194603,\"status\":\"UNASSIGNED\",\"beginDate\":null,\"examinationCount\":null,\"examinationResultCount\":null,\"completionDate\":null,\"type\":null,\"checkupsStatuses\":[],\"lastTurnoutDate\":1620939600000,\"earliestAppointment\":null}}"));

//        завершение приема на UI
        app.careEvent().cancelCareEvent();
    }



}
