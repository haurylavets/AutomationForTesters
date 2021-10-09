package com.by.addressbook.models;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    private Integer id = Integer.MAX_VALUE;
    @Expose
    private String firstName;
    @Expose
    private String middleName;
    @Expose
    private String lastName;
    @Expose
    @Column(name = "company")
    private String companyName;
    @Expose
    @Column(name = "address", columnDefinition = "text")
    private String address;
    @Expose
    @Column(columnDefinition = "text")
    private String email;
    @Expose
    @Column(columnDefinition = "text")
    private String email2;
    @Expose
    @Column(columnDefinition = "text")
    private String email3;
    @Expose
    @Column(name = "home", columnDefinition = "text")
    private String homePhone;
    @Expose
    @Column(name = "mobile", columnDefinition = "text")
    private String mobilePhone;
    @Expose
    @Column(name = "work", columnDefinition = "text")
    private String workPhone;
    @Expose
    @Column(name = "bday")
    private Byte birthDay;
    @Expose
    @Column(name = "bmonth")
    private String birthMonth;
    @Expose
    @Column(name = "byear")
    private String birthYear;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Expose
    @Column(columnDefinition = "mediumtext")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))

    private Set<GroupData> groups = new HashSet<GroupData>();

    public Groups getGroups() {
        return new Groups(groups);
    }


    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDay() {
        return birthDay == null ? null : String.valueOf(birthDay);
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public ContactData withId(Integer id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
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


    public ContactData withBirthDay(String birthDay) {
        this.birthDay = Byte.parseByte(birthDay);
        return this;
    }

    public ContactData withBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
        return this;
    }

    public ContactData withBirthYear(String birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(companyName, that.companyName) && Objects.equals(address, that.address) && Objects.equals(email, that.email) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, companyName, address, email, homePhone, mobilePhone);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workHome) {
        this.workPhone = workHome;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public File getPhoto() {
        return photo == null ? null : new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
