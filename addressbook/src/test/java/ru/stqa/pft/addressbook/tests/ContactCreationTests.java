package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoAddNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Mike", "Smith", "Miky", "Canada", "256854796523", "myke@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoGroupPage();
    }


}
