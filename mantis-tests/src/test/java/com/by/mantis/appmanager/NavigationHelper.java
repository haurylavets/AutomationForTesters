package com.by.mantis.appmanager;

import com.by.mantis.model.MantisUserData;
import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        click(By.cssSelector("[value='Login']"));
        type(By.name("password"), password);
        click(By.cssSelector("[value=Login]"));
    }

    public void userManagePage(MantisUserData user) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        type(By.id("search"), user.getUserName());
        click(By.cssSelector("[value=\"Apply Filter\"]"));
        click(By.linkText(user.getUserName()));
    }
}
