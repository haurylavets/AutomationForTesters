package com.by.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class BaseHelper {
    protected WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text != null) {
            click(locator);
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void selectFile(By locator, File file) {
        if (file != null && file.exists()) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    protected void select(By locator, String text) {
        if (text != null) {
            click(locator);
            new Select(wd.findElement(locator)).selectByVisibleText(text);
        }
    }

    protected void selectByValue(By locator, String text) {
        if (text != null) {
            click(locator);
            new Select(wd.findElement(locator)).selectByValue(text);
        }
    }

    protected void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    protected boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
