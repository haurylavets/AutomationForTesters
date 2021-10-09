package com.by.addressbook.appManager;

import com.by.addressbook.models.ContactData;
import com.by.addressbook.models.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    private Contacts contactCache = null;

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompanyName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail());
        type(By.name("email3"), contactData.getEmail());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        select(By.name("bday"), contactData.getBirthDay());
        select(By.name("bmonth"), contactData.getBirthMonth());
        type(By.name("byear"), contactData.getBirthYear());
        selectFile(By.name("photo"), contactData.getPhoto());
    }

    public void submitContactCreation() {
        click(By.cssSelector("input[value=Enter]"));
        contactCache = null;
    }


    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitContactUpdate() {
        click(By.cssSelector("input[value=Update]"));
        contactCache = null;
        returnToHomePage();
    }

    public void create(ContactData contact) {
        goToNewContactPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void deleteSelectedContact() {
        click(By.cssSelector("input[value=Delete]"));
        acceptAlert();
        contactCache = null;
    }

    public void modify(int id) {
        String xpath = String.format("//tr[@name='entry' and .//input[@value='%d']]//img[@title='Edit']", id);
        click(By.xpath(xpath));
    }

    public void addToGroup(int groupId) {
        selectByValue(By.cssSelector("select[name=to_group]"), String.valueOf(groupId));
        click(By.cssSelector("input[name=add]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allemails = cells.get(4).getText();
            String allphones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAddress(address)
                    .withAllEmails(allemails)
                    .withAllPhones(allphones));
        }
        return new Contacts(contactCache);
    }

    public ContactData contactInfoFromEditForm() {
        ContactData contactData = new ContactData();
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");

        return contactData.withFirstName(firstName).withLastName(lastName)
                .withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
    }


    public void filterByGroup(int id) {
        selectByValue(By.cssSelector("select[name=group]"), String.valueOf(id));
    }

    public void removeSelectedFromGroup() {
        click(By.cssSelector("input[name=remove]"));
    }
}

