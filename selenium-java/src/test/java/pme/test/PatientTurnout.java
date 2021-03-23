package pme.test;

import org.junit.Test;

public class PatientTurnout extends TestBase {

    @Test
    public void login() throws Exception {
        app.search().closeWindow();
        app.search().searchPatient("770150 3980341455");
        app.careEvent().startCareEvent();
        app.careEvent().sleep(50);
        app.db().checkPatientTurnout();
    }


}
