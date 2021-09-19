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

    public void goToGroupListPage() {
        click(By.linkText("groups"));
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
