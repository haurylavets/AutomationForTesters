package com.by.addressbook.tests.contacts;

import com.by.addressbook.models.ContactData;
import com.by.addressbook.models.Contacts;
import com.by.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("firstName").withLastName("lastName")
                    .withBirthDay("8").withBirthMonth("October").withBirthYear("1985"));
        }
    }

    @Test
    public void testContactDelete() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withOut(deletedContact)));
    }

}



