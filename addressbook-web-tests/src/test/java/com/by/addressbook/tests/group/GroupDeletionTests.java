package com.by.addressbook.tests.group;

import com.by.addressbook.models.GroupData;
import com.by.addressbook.models.Groups;
import com.by.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupListPage();
            app.group().create(new GroupData().withName("test5"));
        }
    }

    @Test
    public void testGroupDelete() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();

        app.goTo().groupListPage();
        app.group().delete(deletedGroup);
        Groups after = app.db().groups();
        assertThat(app.group().count(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.withOut(deletedGroup)));
    }

}

