package pme;

import org.junit.Test;
import org.openqa.selenium.By;

public class Login extends TestBase2 {

    String BASE_URL = "http://doctor3.test.emias.mos.ru/web/etd-access-point?lgn=IrIlina&medicalEntityId=204&isNewLN=true&docSpecialityCode=69&medicalEmployeeJobInfoId=9406805045&roleId=4&availableResourceId=16071283&newUI=true&complexResourceId=14341268";

    @Test
    public void login() {
        driver.get(BASE_URL);
        driver.findElement(By.xpath("//app-medical-employee-select//sc-button/span[text()='Отменить']")).click();
        driver.findElement(By.xpath("//kpi-admission-plan-header//lu-form-field//input")).sendKeys("7794799791001458");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void login2() {
        driver.get("http://localhost:8443/litecard/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void login3() {
        driver.get("http://localhost:8443/litecard/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
