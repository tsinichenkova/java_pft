package pme.appmanager;

import org.openqa.selenium.WebDriver;

public class SearchHelper extends HelperBase {

    public SearchHelper(WebDriver wd) {
        super(wd);
    }

    public void closeWindow() {
        click("МодальноеОкноВыбораМедРаботника.кнопкаОтменить");
    }

    public void searchPatient(String value) throws Exception {
        sendKeys("Поиск.ПолеПоиска", value);
        click("Поиск.КнопкаНайти");
        moveMouse("Поиск.Пациент");
        click("Поиск.КнопкаОткрытьКарту");
    }

}
