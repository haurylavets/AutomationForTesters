package com.by.addressbook.appManager;

import com.by.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void goToNewContactPage() {
        click(By.linkText("add new"));
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
        returnToHomePage();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("input[value=Delete]"));
        acceptAlert();
    }

    public void createContact(ContactData contact) {
        goToNewContactPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String firstName = element.findElements(By.tagName("td")).get(2).getText();

            ContactData contact = new ContactData(id, firstName, null, lastName, null, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}

