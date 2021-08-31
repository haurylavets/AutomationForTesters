package com.by.addressbook.tests;

import com.by.addressbook.models.GroupData;
import org.testng.annotations.Test;

public class CreateNewGroup extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getNavigationHelper().goToNewGroupPage();
        app.getGroupHelper().fillGroupForm(new GroupData("test3", "testHeader", "testFooter"));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().returnToGroupPage();
    }
}
