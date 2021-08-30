package com.by.addressbook;

import com.by.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

    public class CreateNewContact {
        private WebDriver wd;

        @BeforeMethod(alwaysRun = true)
        public void setUp() throws Exception {
            wd = new ChromeDriver();
            wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            wd.get("http://localhost/addressbook/group.php");
            login("admin", "secret");
        }

        private void login(String username, String password) {
            wd.findElement(By.name("user")).click();
            wd.findElement(By.name("user")).clear();
            wd.findElement(By.name("user")).sendKeys(username);
            wd.findElement(By.id("LoginForm")).click();
            wd.findElement(By.name("pass")).click();
            wd.findElement(By.name("pass")).clear();
            wd.findElement(By.name("pass")).sendKeys(password);
            wd.findElement(By.id("LoginForm")).submit();
        }

        @Test
        public void testContactCreation() throws Exception {

            goToNewContactPage();
            fillContactForm(new ContactData("firstName", "middleName", "lastName", "testCompany", "street,1q", "test@test.com", "1", "April", "1980"));
            submitContactCreation();
            returnToHomePage();
        }

        private void returnToHomePage() {
            wd.findElement(By.linkText("home page")).click();
        }

        private void submitContactCreation() {
            wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
        }

        private void fillContactForm(ContactData contactData) {
            wd.findElement(By.name("firstname")).click();
            wd.findElement(By.name("firstname")).clear();
            wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
            wd.findElement(By.name("middlename")).click();
            wd.findElement(By.name("middlename")).clear();
            wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddleName());
            wd.findElement(By.name("lastname")).click();
            wd.findElement(By.name("lastname")).clear();
            wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
            wd.findElement(By.name("company")).click();
            wd.findElement(By.name("company")).clear();
            wd.findElement(By.name("company")).sendKeys(contactData.getCompanyName());
            wd.findElement(By.name("address")).click();
            wd.findElement(By.name("address")).clear();
            wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
            wd.findElement(By.name("email")).click();
            wd.findElement(By.name("email")).clear();
            wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
            wd.findElement(By.name("bday")).click();
            new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBirthDay());
            wd.findElement(By.name("bmonth")).click();
            new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBirthMonth());
            wd.findElement(By.name("byear")).click();
            wd.findElement(By.name("byear")).clear();
            wd.findElement(By.name("byear")).sendKeys(contactData.getBirthYear());
        }

        private void goToNewContactPage() {
            wd.findElement(By.linkText("add new")).click();
        }

        @AfterMethod(alwaysRun = true)
        public void tearDown() throws Exception {
            wd.quit();
        }

        private boolean isElementPresent(By by) {
            try {
                wd.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                wd.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

    }


