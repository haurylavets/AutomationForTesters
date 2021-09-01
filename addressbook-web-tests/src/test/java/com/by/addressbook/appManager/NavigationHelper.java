package com.by.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void goToGroupListPage() {
        click(By.linkText("groups"));
    }

    public void returnToGroupListPage() {
        click(By.linkText("group page"));
    }

    public void goToNewGroupPage() {
        click(By.name("new"));
    }

    public void goToNewContactPage() {
        click(By.linkText("add new"));
    }

    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.cssSelector("input[type=\"submit\"]"));
    }

    public void initGroupEdit() {
        click(By.cssSelector("[name=edit]"));
    }

    public void initContactEdit() {
        click(By.cssSelector("img[title=Edit]"));
    }
}
