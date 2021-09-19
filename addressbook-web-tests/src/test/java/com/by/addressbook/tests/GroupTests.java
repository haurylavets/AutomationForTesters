package com.by.addressbook.tests;

import com.by.addressbook.models.GroupData;
import org.testng.annotations.Test;

public class GroupTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupListPage();
        app.getGroupHelper().createGroup(new GroupData("test3", null, null));
    }

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupListPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test3", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getNavigationHelper().initGroupEdit();
        app.getGroupHelper().fillGroupForm(new GroupData("test4", "testHeader", "testFooter"));
        app.getGroupHelper().submitGroupUpdate();
    }

    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().goToGroupListPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test3", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
    }
}
