package com.by.addressbook.tests.contacts;

import com.by.addressbook.models.ContactData;
import com.by.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CompareContactDataTests extends TestBase {

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", " ").replaceAll("[-()]", " ");
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("test fname").withLastName("test lname")
                    .withEmail("qqq@qqq.qqq").withEmail2("www@www.www").withEmail3("eee@eee.eee")
                    .withHomePhone("111").withWorkPhone("222").withMobilePhone("333")
                    .withAddress("20 Forest st."));
        }
    }

    @Test
    public void testContactFields() {
        app.goTo().contactPage();
        ContactData contact = app.contact().all().iterator().next();
        app.contact().modify(contact.getId());
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm();

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(CompareContactDataTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
