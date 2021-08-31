package com.by.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ChromeDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
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
}
