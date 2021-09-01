package com.by.addressbook.tests;

import com.by.addressbook.models.GroupData;
import org.testng.annotations.Test;

public class ModificationGroup extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupListPage();
        app.getGroupHelper().selectGroup();
        app.getNavigationHelper().initGroupEdit();
        app.getGroupHelper().fillGroupForm(new GroupData("test4", "testHeader", "testFooter"));
        app.getGroupHelper().submitGroupUpdate();
        app.getNavigationHelper().returnToGroupListPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
    }

}
