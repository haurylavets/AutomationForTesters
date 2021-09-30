package com.by.addressbook.tests.group;

import com.by.addressbook.models.GroupData;
import com.by.addressbook.models.Groups;
import com.by.addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupListPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test3"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test4")
                .withHeader("testHeader").withFooter("testFooter");
        app.group().modify(group);
        Groups after = app.group().all();
        assertThat(app.group().count(), equalTo(before.size()));
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    }
}
