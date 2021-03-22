package pme.appmanager;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class HelperBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    private static final String filePath = "D:\\Projects\\java_pft\\selenium-java\\src\\test\\resources\\xpath.json";

    protected String getXpath(String args) {
        try {
            FileReader json = new FileReader(filePath);
            return JsonPath.from(json).getString(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void click(String path) {
        wd.findElement(By.xpath(getXpath(path))).click();
    }

    public void sendKeys(String path, String value) {
        wd.findElement(By.xpath(getXpath(path))).sendKeys(value);
    }

    public void sleep(int sec) throws Exception {
        Thread.sleep(sec*1000);
    }

}
