package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
//        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomeTelephone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("mobile"), contactData.getMobileTelephone());
        type(By.name("work"), contactData.getWorkTelephone());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }
    

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }
    

    public void create(ContactData contactData) {
        gotoAddNewContact();
        fillContactForm(contactData, true);
        submitContactCreation();
        contactCache = null;
        gotoHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        gotoHomePage();
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteSelectedContact();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String phones = element.findElements(By.tagName("td")).get(5).getText();
            String address = element.findElements(By.tagName("td")).get(3).getText();
            String emails = element.findElements(By.tagName("td")).get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
            .withAllPhones(phones).withAddress(address).withallEmails(emails));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm (ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withHomeTelephone(home).withMobileTelephone(mobile).withWorkTelephone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withAddress(address);
    }

    public void addInSelectGroup(int id,int index) {
        selectContactById(id);
        Select dropdown =  new Select(wd.findElement(By.xpath("//select[@name='to_group']")));
        dropdown.selectByValue(Integer.toString(index));
        wd.findElement(By.xpath("//input[@name='add']")).click();
    }


    public void deleteFromSelectGroup(int id, int index) {
        Select dropdown = new Select(wd.findElement(By.xpath("//select[@name='group']")));
        dropdown.selectByValue(Integer.toString(index));
        selectContactById(id);
        wd.findElement(By.xpath("//input[@name='remove']")).click();

    }

}
