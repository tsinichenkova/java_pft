package pme.appmanager;

import org.openqa.selenium.WebDriver;

public class DispanserizationHelper extends HelperBase {

    public DispanserizationHelper(WebDriver wd) {
        super(wd);
    }

    public void startDispanserization() throws Exception {
        click("Диспансеризация.Назначить");
        click("Диспансеризация.кнопкаНазначить");
        sleep(2);
    }
}
