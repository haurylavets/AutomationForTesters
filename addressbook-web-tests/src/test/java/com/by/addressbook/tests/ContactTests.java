package com.by.addressbook.tests;

import com.by.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("firstName", "middleName", "lastName", "testCompany", "street,1q", "test@test.com", "1", "April", "1980"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
    }

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        app.getNavigationHelper().initContactEdit();
        app.getContactHelper().fillContactForm(new ContactData("firstName", "middleName", "lastName", "testCompany", "street,1q", "test@test.com", "1", "April", "1980"));
        app.getContactHelper().submitContactUpdate();
    }

    @Test
    public void testContactDelete() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
    }
}


