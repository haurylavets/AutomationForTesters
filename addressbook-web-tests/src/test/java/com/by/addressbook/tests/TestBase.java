package com.by.addressbook.tests;

import com.by.addressbook.appManager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        app.init();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }
}
