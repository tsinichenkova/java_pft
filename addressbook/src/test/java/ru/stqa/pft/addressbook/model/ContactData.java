package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String address;
    private final String homeTelephone;
    private final String email;
    private int id;

    public ContactData(int id, String firstName, String lastName, String nickname, String address, String homeTelephone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.address = address;
        this.homeTelephone = homeTelephone;
        this.email = email;
        this.id = id;
    }

    public ContactData(String firstName, String lastName, String nickname, String address, String homeTelephone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.address = address;
        this.homeTelephone = homeTelephone;
        this.email = email;
        this.id = Integer.MAX_VALUE;
    }

    public void setId(int id) {
        this.id = id;
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
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName);
    }
}
