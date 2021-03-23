package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private SessionFactory sessionFactory;

    public DbHelper() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for (GroupData group : result) {
            System.out.println(group);
        }
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        for (ContactData contact : result) {
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }
}
