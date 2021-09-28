package com.by.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void contactPage() {
        click(By.linkText("home"));
    }

    public void groupListPage() {
        click(By.linkText("groups"));
    }

    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.cssSelector("input[type=\"submit\"]"));
    }

    public void modify(int id) {
        String xpath = String.format("//tr[@name='entry' and .//input[@value='%d']]//img[@title='Edit']", id);
        click(By.xpath(xpath));
    }
}
