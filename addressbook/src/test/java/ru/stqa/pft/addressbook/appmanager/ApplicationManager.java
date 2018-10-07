package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    FirefoxDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost:8443/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public void confirmAlert() {
        wd.switchTo().alert().accept();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
