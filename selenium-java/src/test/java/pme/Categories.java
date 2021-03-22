package pme;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Categories extends TestBase2 {

    @Test
    public void categories() {
        driver.get("http://localhost:8443/litecard/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> menuItems = driver.findElements(By.xpath("//*[@id=\"app-\"]"));

        List nameMenu = new ArrayList();
        for (WebElement element : menuItems) {
            nameMenu.add(element.getText());
        }

        for (int i = 0; i < menuItems.size(); i++) {
            driver.findElement(By.xpath("//*[@id=\"app-\"]/a/span[text() = '" + nameMenu.get(i) + "']")).click();
            areElementsPresent(By.xpath("//*[@id=\"content\"]/h1"));

            List<WebElement> menuItemsLi = driver.findElements(By.xpath("//*[@id=\"app-\"]/ul//span"));
            List nameCategories = new ArrayList();
            for (WebElement element : menuItemsLi) {
                nameCategories.add(element.getText());
            }

            for (int j = 0; j < menuItemsLi.size(); j++) {
                driver.findElement(By.xpath("//*[@id=\"app-\"]/ul//span[text()='" + nameCategories.get(j) + "']")).click();
                areElementsPresent(By.xpath("//*[@id=\"content\"]/h1"));
            }
        }
    }
}
