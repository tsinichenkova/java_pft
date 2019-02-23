package ru.stqa.pft.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    @Before
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver();
        System.out.println(((HasCapabilities) driver).getCapabilities());
//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe")));
//        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, 10);
        tlDriver.set(driver);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));
    }

    @After
    public void stop() {
        //driver.quit();
        //driver = null;
    }

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
