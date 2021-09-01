package com.by.addressbook.appManager;

import com.by.addressbook.models.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
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
}
