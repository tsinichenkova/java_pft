package ru.stqa.pft.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Stickers extends TestBase {

    @Test
    public void stickers() {
        driver.get("http://localhost:8443/litecard/");

        List<WebElement> categories = driver.findElements(By.xpath("//div[@class='content']//div[@class = 'box']//ul[contains(@class, 'products')]/../../../div[@class='box']"));
        List nameId = new ArrayList();
        for (WebElement element : categories) {
            nameId.add(element.getAttribute("id"));
        }
        for (int i = 0; i < categories.size(); i++) {
            List<WebElement> menuItems = driver.findElements(By.xpath("//*[@id='" + nameId.get(i) + "']//li//div[contains (@class, 'sticker')]"));

            for (int j = 1; j <= menuItems.size(); j++) {
                String xpath = "//*[@id='" + nameId.get(i) + "']//li[" + j + "]//div[contains (@class, 'sticker')]";
                assertTrue(areElementsPresent(By.xpath(xpath)));
                assertEquals(driver.findElements(By.xpath(xpath)).size(), 1);
            }
        }
    }
}
