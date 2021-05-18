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

    public void startCareEventOMP() {
        click("РаботаСПриемом.НачатьПрием");
    }

    public void cancelCareEvent() throws Exception {
        click("РаботаСПриемом.ОтменитьПрием");
        click("РаботаСПриемом.ПримичнаОтмены");
        click("РаботаСПриемом.Отмена");
        sleep(2);
    }

    public void gotoPrintScreen() {
        click("РаботаСПриемом.Завершить");
        click("РаботаСПриемом.ЧекбоксНеТребуются");
        click("РаботаСПриемом.СохранитьИПродолжить");
        click("РаботаСПриемом.ЗакрытьСообщение");
        click("РаботаСПриемом.ПодписатьИПродолжить");
    }

    public void stopCareEvent() throws Exception {
        click("РаботаСПриемом.Завершить");
        click("РаботаСПриемом.ЧекбоксНеТребуются");
        click("РаботаСПриемом.СохранитьИПродолжить");
        click("РаботаСПриемом.ЗакрытьСообщение");
        click("РаботаСПриемом.ПодписатьИПродолжить");
        sleep(5);
        click("РаботаСПриемом.ЗавершитьПрием");
        sleep(2);
    }

    public void stopCareEventForOMP() throws Exception {
        click("РаботаСПриемом.Завершить");
        click("РаботаСПриемом.ПодписатьИПродолжить");
        sleep(5);
        click("РаботаСПриемом.ЗавершитьПриемПриПодписании");
        sleep(2);
    }

    public void stopCareEventForSign() throws Exception {
        click("РаботаСПриемом.Завершить");
        click("РаботаСПриемом.ЧекбоксНеТребуются");
        click("РаботаСПриемом.СохранитьИПродолжить");
        click("РаботаСПриемом.ЗакрытьСообщение");
        click("РаботаСПриемом.ЗавершитьПриемПриПодписании");
        sleep(2);
    }

    public void recordPatient() throws Exception {
        click("ЗаписьПациента.кнопкаЗаписатьПациента");
        click("ЗаписьПациента.кнопкаВыбораИсследования");
        click("ЗаписьПациента.кнопкаОткрытияМО");
        click("ЗаписьПациента.кнопкаВыбораМО");
        click("ЗаписьПациента.кнопкаВыбрать");
        click("ЗаписьПациента.свободныйCлот");
        click("ЗаписьПациента.кнопкаЗаписать");
        sleep(3);
        click("ЗаписьПациента.кнопкаВернуться");
        sleep(3);
        click("РаботаСПриемом.ЗавершитьПрием");
        sleep(2);
    }



}
