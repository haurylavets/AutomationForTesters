package com.by.addressbook.appManager;

import com.by.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompanyName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        select(By.name("bday"), contactData.getBirthDay());
        select(By.name("bmonth"), contactData.getBirthMonth());
        type(By.name("byear"), contactData.getBirthYear());
    }

    public void submitContactCreation() {
        click(By.cssSelector("input[value=Enter]"));
    }

    public void submitContactUpdate() {
        click(By.cssSelector("input[value=Update]"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("input[value=Delete]"));
        acceptAlert();
    }
}
