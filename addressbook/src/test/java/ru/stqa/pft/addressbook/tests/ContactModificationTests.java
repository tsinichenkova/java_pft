package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePrediction() {
        if (app.db().contacts().size() == 0) {
        app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstName("Mike2").withLastName("Smith2"));
        }
    }
    
    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId())
                .withFirstName("Mike2")
                .withLastName("Smith2")
                .withNickname("Miky2")
                .withAddress("Canadа2")
                .withEmail("test@mail.ru2")
                .withHomeTelephone("12315462");
        app.goTo().HomePage();
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(
                before.whithout(modifyContact).whithAdded(contact)));
        verifyContactistInUI();
    }
}
