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
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("firstName")
                    .withLastName("lastName").withBirthDay("8").withBirthMonth("October")
                    .withBirthYear("1990"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        app.goTo().contactPage();
        app.contact().modify(modifiedContact.getId());
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstName("firstName").withLastName("lastName")
                .withCompanyName("companyName").withAddress("Str").withEmail("aaa@gmail.com")
                .withHomePhone("1235").withMobilePhone("74834")
                .withBirthDay("8").withBirthMonth("October").withBirthYear("1985");
        app.contact().fillContactForm(contact);
        app.contact().submitContactUpdate();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }
}


