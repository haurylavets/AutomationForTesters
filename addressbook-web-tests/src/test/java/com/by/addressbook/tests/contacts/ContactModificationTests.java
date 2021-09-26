package com.by.addressbook.tests.contacts;

import com.by.addressbook.models.ContactData;
import com.by.addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("firstName", null, "lastName", null, null, null, "8", "October", "1990"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().initContactEdit(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "firstName", null, "lastName", "companyName", "Str", "aaa@gmail.com", "8", "October", "1990");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactUpdate();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}


