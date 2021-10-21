package com.by.mantis.appmanager;

import com.by.mantis.model.MantisUserData;
import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void start(MantisUserData user) {
        app.goTo().userManagePage(user);
        click(By.cssSelector("[value='Reset Password']"));
    }

    public void finish(String resetLink, String password) {
        wd.get(resetLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
