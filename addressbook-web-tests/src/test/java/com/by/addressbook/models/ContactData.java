package com.by.addressbook.models;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String companyName;
    private final String address;
    private final String email;
    private final String birthDay;
    private final String birthMonth;
    private final String birthYear;

    public ContactData(String firstName, String middleName, String lastName, String companyName, String address, String email, String birthDay, String birthMonth, String birthYear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
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
        return birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }
}
