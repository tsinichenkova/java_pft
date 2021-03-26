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

    public void cancelExamination() throws Exception {
        click("Диспансеризация.Диспансеризация");
        click("Диспансеризация.ДоврачебныйПрофОсмотр");
        click("Диспансеризация.ОтменитьНазначение");
        click("Диспансеризация.ПричнаОтмены");
        click("Диспансеризация.кнопкаОтменитьНазначение");
        sleep(2);
    }

    public void createResult() throws Exception {
        click("Протокол.кнопкаСоздания");
        sendKeys("Протокол.ИМТ", "15");
        sendKeys("Протокол.Талия", "60");
        sendKeys("Протокол.СистолическоеДавление", "80");
        sendKeys("Протокол.ДиастолическоеДавление", "120");
        click("Протокол.Тонометрия");
        sendKeys("Протокол.Давление", "75");
        click("Протокол.кнопкаСохранить");
        sleep(5);
    }
}
