package pme.appmanager;

import org.openqa.selenium.WebDriver;

public class CareEventHelper extends HelperBase {

    public CareEventHelper(WebDriver wd) {
        super(wd);
    }

    public void startCareEvent() {
        click("РаботаСПриемом.НачатьПрием");
        click("РаботаСПриемом.ЦельПриема");
        click("РаботаСПриемом.Сохранить");
    }

    public void stopCareEvent() throws Exception {
        click("РаботаСПриемом.ОтменитьПрием");
        click("РаботаСПриемом.ПримичнаОтмены");
        click("РаботаСПриемом.Отмена");
        sleep(2);
    }

}
