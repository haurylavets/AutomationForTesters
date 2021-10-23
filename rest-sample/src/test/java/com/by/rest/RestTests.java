package com.by.rest;

import com.by.rest.appmanager.AppManager;
import com.by.rest.models.Issue;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests {

    private AppManager app = new AppManager();

    @Test

    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1537);
        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("Test description");
        int issueId = app.rest().createIssue(newIssue);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (app.rest().isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
