package com.by.addressbook.tests.contacts;

import com.by.addressbook.models.ContactData;
import com.by.addressbook.models.GroupData;
import com.by.addressbook.models.Groups;
import com.by.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("firstName")
                    .withLastName("lastName").withBirthDay("8").withBirthMonth("October")
                    .withBirthYear("1990"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupListPage();
            app.group().create(new GroupData().withName("name").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testContactDeleteFromGroup() {
        app.goTo().contactPage();

        ContactData contactToAdd = app.db().contacts()
                .stream().filter(c -> c.getGroups().size() > 0).findFirst().orElse(null);
        if (contactToAdd == null) {
            ContactData anyContact = app.db().contacts().iterator().next();
            GroupData anyGroup = app.db().groups().iterator().next();

            app.goTo().contactPage();
            app.contact().selectContactById(anyContact.getId());
            app.contact().addToGroup(anyGroup.getId());

            contactToAdd = app.db().contacts().stream().filter(c -> c.getId().equals(anyContact.getId())).findFirst().orElseThrow();
        }

        Groups groupsBefore = contactToAdd.getGroups();
        GroupData groupToRemoveFrom = groupsBefore.iterator().next();

        app.contact().filterByGroup(groupToRemoveFrom.getId());
        app.contact().selectContactById(contactToAdd.getId());
        app.contact().removeSelectedFromGroup();

        ContactData finalContactToAdd = contactToAdd;
        ContactData deletedContact = app.db().contacts()
                .stream().filter((c) -> Objects.equals(c.getId(), finalContactToAdd.getId())).findFirst().orElseThrow();
        Groups groupsAfter = deletedContact.getGroups();
        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
        assertThat(groupsAfter, equalTo(groupsBefore.withOut(groupToRemoveFrom)));
    }
}
