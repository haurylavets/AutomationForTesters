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

public class MoveContactToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupListPage();
            app.group().create(new GroupData().withName("name").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testContactMoveToGroup() {
        app.goTo().contactPage();
        Groups allGroups = app.db().groups();

        ContactData contactToAdd = app.db().contacts().stream()
                .filter(c -> c.getGroups().size() < allGroups.size()).findFirst().orElse(null);
        if (contactToAdd == null) {
            addNewContact();
            contactToAdd = app.db().contacts().stream()
                    .filter(c -> c.getGroups().size() < allGroups.size()).findFirst().orElseThrow();
        }

        Groups groupsBefore = contactToAdd.getGroups();
        GroupData toGroup = allGroups.stream().filter(g -> !groupsBefore.contains(g)).findFirst().orElseThrow();

        app.contact().selectContactById(contactToAdd.getId());
        app.contact().addToGroup(toGroup.getId());
        ContactData finalContactToAdd = contactToAdd;
        ContactData updatedContact = app.db().contacts()
                .stream().filter((c) -> Objects.equals(c.getId(), finalContactToAdd.getId())).findFirst().orElseThrow();

        Groups groupsAfter = updatedContact.getGroups();
        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(toGroup)));
    }

    private void addNewContact() {
        app.goTo().contactPage();
        app.contact().create(new ContactData().withFirstName("firstName")
                .withLastName("lastName").withBirthDay("8").withBirthMonth("October")
                .withBirthYear("1990"));
    }
}
