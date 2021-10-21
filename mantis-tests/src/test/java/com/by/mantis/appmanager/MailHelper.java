package com.by.mantis.appmanager;

import com.by.mantis.model.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private Wiser wiser;

    public MailHelper() {
    }

    public static MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<MailMessage> waitForMail(int count, long timeout) {
        if (wiser == null) {
            throw new RuntimeException("SMTP server is not started");
        }
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout) {
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map(MailHelper::toModelMail).collect(Collectors.toList());
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new Error("No mail :(");
    }

    public void start() {
        if (wiser != null) {
            throw new RuntimeException("SMTP server is already started");
        }
        wiser = new Wiser();
        wiser.start();
    }

    public void stop() {
        if (wiser == null) {
            throw new RuntimeException("SMTP server is not started");
        }
        wiser.stop();
        wiser = null;
    }
}
