package ru.stqa.pft.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class Login extends TestBase {

    @Test
    public void login() {
        driver.get("http://localhost:8443/litecard/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
}
