package pme.test;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import pme.appmanager.RunnerExtension;
import pme.utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientTurnout extends TestBase {

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            try {
                String content = Utils.getContent("createCareEvent");
                System.out.println(content);
                String careEventId = JsonPath.from(content).getString("careEvent.careEventId");
                String mejiId = JsonPath.from(content).getString("careEvent.mejiId");
                System.out.println(careEventId);
                app.http().cancelCareEvent(careEventId, mejiId);

            } catch (IOException c) {
                c.printStackTrace();
            }
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

//    @AfterTest
//    public static void checkStatus(ITestResult result) throws FileNotFoundException {
//        if (result.isSuccess()) {
//            String content = Utils.getContent("createCareEvent");
//            System.out.println(content);
//            return;
//        } else {
//            // добавить завершение приема
//        }
//    }



}
