package com.by.addressbook.tests.contacts;

import com.by.addressbook.models.ContactData;
import com.by.addressbook.models.Contacts;
import com.by.addressbook.tests.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("test fname").withLastName("test lname").withBirthDay("8").withBirthMonth("October").withBirthYear("1990");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((i) -> i.getId()).max().getAsInt()))));

    }
}


