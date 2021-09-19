package com.by.addressbook.appManager;

import com.by.addressbook.models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void goToNewGroupPage() {
        click(By.name("new"));
    }

    public void returnToGroupListPage() {
        click(By.linkText("group page"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }


    public void submitGroupUpdate() {
        click(By.cssSelector("[name=update]"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void createGroup(GroupData group) {
        goToNewGroupPage();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupListPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
