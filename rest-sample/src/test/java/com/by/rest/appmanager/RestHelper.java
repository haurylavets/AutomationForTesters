package com.by.rest.appmanager;

import com.by.rest.models.Issue;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.Set;

public class RestHelper {
    private final AppManager app;

    public RestHelper(AppManager appManager) {
        app = appManager;
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject())
                                , new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        try {
            String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)))
                    .returnContent().asString();
            JsonElement parsed = new JsonParser().parse(json);
            JsonElement issue = parsed.getAsJsonObject().get("issues");
            Set<Issue> issues = new Gson().fromJson(issue, new TypeToken<Set<Issue>>() {
            }.getType());
            Issue foundIssue = issues.iterator().next();
            return !foundIssue.getStateName().equals("Closed");
        } catch (HttpResponseException e) {
            if (e.getStatusCode() == 404) {
                return false;
            }
            throw e;
        }
    }
}
