package com.by.addressbook.appManager;

import com.by.addressbook.models.GroupData;
import com.by.addressbook.models.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    private Groups groupCache = null;

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupEdit();
        fillGroupForm(group);
        submitGroupUpdate();
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
        groupCache = null;
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        groupCache = null;
    }

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupEdit() {
        click(By.cssSelector("[name=edit]"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
        returnToGroupListPage();
    }

    public void create(GroupData group) {
        goToNewGroupPage();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupListPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void submitGroupUpdate() {
        click(By.cssSelector("[name=update]"));
        groupCache = null;
        returnToGroupListPage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }

}
