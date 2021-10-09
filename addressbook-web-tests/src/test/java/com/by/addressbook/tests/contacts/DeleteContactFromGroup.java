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
        Contacts before = app.db().contacts();
        ContactData contactToAdd = before.iterator().next();
        Groups groupsBefore = contactToAdd.getGroups();

        if (contactToAdd.getGroups().size() == 0) {
            GroupData anyGroup = app.db().groups().iterator().next();

            app.contact().selectContactById(contactToAdd.getId());
            app.contact().addToGroup(anyGroup.getId());

            groupsBefore = groupsBefore.withAdded(anyGroup);
        }

        GroupData groupToRemoveFrom = groupsBefore.iterator().next();

        app.contact().filterByGroup(groupToRemoveFrom.getId());
        app.contact().selectContactById(contactToAdd.getId());
        app.contact().removeSelectedFromGroup();

        ContactData deletedContact = app.db().contacts()
                .stream().filter((c) -> Objects.equals(c.getId(), contactToAdd.getId())).findFirst().orElseThrow();
        Groups groupsAfter = deletedContact.getGroups();
        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() - 1));
        assertThat(groupsAfter, equalTo(groupsBefore.withOut(groupToRemoveFrom)));
    }
}
