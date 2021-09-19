package com.by.addressbook.tests;

import com.by.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class ContactTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("firstName", null, null, null, null, null, "8", "October", "1990"));
    }

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstName", null, null, null, null, null, "8", "October", "1990"));
        }
        app.getNavigationHelper().initContactEdit();
        app.getContactHelper().fillContactForm(new ContactData("firstName", "middleName", "lastName", "companyName", "Str", "aaa@gmail.com", "8", "October", "1990"));
        app.getContactHelper().submitContactUpdate();
    }

    @Test
    public void testContactDelete() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstName", null, null, null, null, null, "8", "October", "1990"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
    }
}


