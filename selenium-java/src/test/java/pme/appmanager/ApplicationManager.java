package pme.appmanager;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private Properties properties;
    WebDriver driver;

    private String browser;
    private DbHelper dbHelper;
    public SearchHelper searchHelper;
    public CareEventHelper careEventHelper;
    public DispanserizationHelper dispanserizationHelper;
    public HttpHelper httpHelper;
    public BrowserMobProxy proxy;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();

        switch (browser) {
            case BrowserType.FIREFOX:
                driver = new FirefoxDriver();
                break;
            case BrowserType.CHROME:
                ProxyManager proxyManager = new ProxyManager();
                proxy = proxyManager.setUpProxy();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-fullscreen");
                options.setProxy(ClientUtil.createSeleniumProxy(proxy));
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(options);
                break;
            case BrowserType.IE:
                driver = new InternetExplorerDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(properties.getProperty("web.baseUrl"));
        proxy().newHar("har");
        proxy().enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        searchHelper = new SearchHelper(driver);
        careEventHelper = new CareEventHelper(driver);
        dispanserizationHelper = new DispanserizationHelper(driver);
        httpHelper = new HttpHelper();
    }

    public WebDriver driver() {
        return driver;
    }

    public void stop() {
        driver.quit();
    }

    public void confirmAlert() {
        driver.switchTo().alert().accept();
    }

    public SearchHelper search() {
        return searchHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public CareEventHelper careEvent() {
        return careEventHelper;
    }

    public DispanserizationHelper dispanserization() {
        return dispanserizationHelper;
    }

    public HttpHelper http() {
        return httpHelper;
    }

    public BrowserMobProxy proxy() {
        return proxy;
    }
}

