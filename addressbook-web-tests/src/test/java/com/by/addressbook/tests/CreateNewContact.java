package com.by.addressbook.tests;

import com.by.addressbook.models.ContactData;
import org.testng.annotations.Test;

public class CreateNewContact extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToNewContactPage();
        app.getContactHelper().fillContactForm(new ContactData("firstName", "middleName", "lastName", "testCompany", "street,1q", "test@test.com", "1", "April", "1980"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
    }
}


