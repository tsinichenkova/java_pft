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

    public void stopCareEvent(String value) {
        sendKeys("Поиск.ПолеПоиска", value);
        click("Поиск.КнопкаНайти");
    }

}
