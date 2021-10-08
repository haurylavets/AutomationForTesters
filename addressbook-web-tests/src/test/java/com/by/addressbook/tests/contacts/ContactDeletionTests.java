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
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("firstName").withLastName("lastName")
                    .withCompanyName("companyName").withAddress("Str").withEmail("aaa@gmail.com")
                    .withBirthDay("8").withBirthMonth("October").withBirthYear("1985"));
        }
    }

    @Test
    public void testContactDelete() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.goTo().contactPage();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}



