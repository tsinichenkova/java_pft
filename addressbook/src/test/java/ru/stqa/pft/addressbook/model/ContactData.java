package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Expose
    @Column(name = "firstname")
    private  String firstName;

    @Expose
    @Column(name = "lastname")
    private  String lastName;

    @Expose
    @Column(name = "nickname")
    private  String nickname;

    @Expose
    @Column(name = "address")
    @Type(type =  "text")
    private  String address;

    @Expose
    @Column(name = "home")
    @Type(type =  "text")
    private  String homeTelephone;

    @XStreamOmitField
    @Expose
    @Column(name = "mobile")
    @Type(type =  "text")
    private  String mobileTelephone;

    @XStreamOmitField
    @Expose
    @Column(name = "work")
    @Type(type =  "text")
    private  String workTelephone;

    @Expose
    @Column(name = "email")
    @Type(type =  "text")
    private  String email;

    @XStreamOmitField
    @Transient
    private String allPhones;

    @XStreamOmitField
    @Column(name = "email2")
    @Type(type =  "text")
    private String email2;

    @XStreamOmitField
    @Column(name = "email3")
    @Type(type =  "text")
    private String email3;

    @XStreamOmitField
    @Transient
    private String allEmails;

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @XStreamOmitField
    @Column(name = "photo")
    @Type(type =  "text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getAbsolutePath();
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomeTelephone(String homeTelephone) {
        this.homeTelephone = homeTelephone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withallEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withMobileTelephone(String mobileTelephone) {
        this.mobileTelephone = mobileTelephone;
        return this;
    }

    public ContactData withWorkTelephone(String workTelephone) {
        this.workTelephone = workTelephone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getMobileTelephone() {
        return mobileTelephone;
    }

    public String getWorkTelephone() {
        return workTelephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getallEmails() {
        return allEmails;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, id);
    }
}
