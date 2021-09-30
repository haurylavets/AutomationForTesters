package com.by.addressbook.tests.contacts;

import com.by.addressbook.models.ContactData;
import com.by.addressbook.models.Contacts;
import com.by.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("firstName").withLastName("lastName").withBirthDay("8").withBirthMonth("October").withBirthYear("1990"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().modify(modifiedContact.getId());
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("firstName").withLastName("lastName")
                .withCompanyName("companyName").withAddress("Str").withEmail("aaa@gmail.com")
                .withBirthDay("8").withBirthMonth("October").withBirthYear("1990");
        app.contact().fillContactForm(contact);
        app.contact().submitContactUpdate();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }

}


