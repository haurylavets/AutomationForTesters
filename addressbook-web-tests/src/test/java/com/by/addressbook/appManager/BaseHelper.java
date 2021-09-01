package com.by.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BaseHelper {
    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    protected void select(By locator, String text) {
        click(locator);
        new Select(wd.findElement(locator)).selectByVisibleText(text);
    }

    protected void acceptAlert() {
        wd.switchTo().alert().accept();
    }
}
