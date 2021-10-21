package com.by.mantis.tests;

import com.by.mantis.model.MailMessage;
import com.by.mantis.model.MantisUserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

    @Test
    public void testChangePassword() throws IOException {
        long now = System.currentTimeMillis();

        app.goTo().login("administrator", "root");

        MantisUserData testUser = app.db().users().stream().filter(user -> !user.getUserName().equals("administrator"))
                .findAny().orElseThrow();

        app.resetPassword().start(testUser);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String resetLink = findResetLink(mailMessages, testUser.getEmail());
        String newPassword = String.format("newPassword%s", now);
        ;
        app.resetPassword().finish(resetLink, newPassword);
        assertTrue(app.newSession().login(testUser.getUserName(), newPassword));

    }

    private String findResetLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return (regex.getText(mailMessage.text));
    }
}

