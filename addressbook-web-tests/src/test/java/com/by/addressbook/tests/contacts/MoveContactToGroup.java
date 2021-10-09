package com.by.addressbook.tests.contacts;

import com.by.addressbook.models.ContactData;
import com.by.addressbook.models.Contacts;
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
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstName("firstName")
                    .withLastName("lastName").withBirthDay("8").withBirthMonth("October")
                    .withBirthYear("1990"));
        }
    }

    @Test
    public void testContactMoveToGroup() {
        app.goTo().contactPage();
        Contacts before = app.db().contacts();
        ContactData contactToAdd = before.iterator().next();
        Groups groupsBefore = contactToAdd.getGroups();

        Groups allGroups = app.db().groups();
        if (groupsBefore.size() == allGroups.size()) {
            addNewGroup();
        }
        GroupData toGroup = allGroups.stream().filter(g -> !groupsBefore.contains(g)).findFirst().orElseThrow();

        app.contact().selectContactById(contactToAdd.getId());
        app.contact().addToGroup(toGroup.getId());
        ContactData updatedContact = app.db().contacts()
                .stream().filter((c) -> Objects.equals(c.getId(), contactToAdd.getId())).findFirst().orElseThrow();

        Groups groupsAfter = updatedContact.getGroups();
        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(toGroup)));
    }


    private void addNewGroup() {
        app.goTo().groupListPage();
        app.group().create(new GroupData().withName("test5"));
    }
}
