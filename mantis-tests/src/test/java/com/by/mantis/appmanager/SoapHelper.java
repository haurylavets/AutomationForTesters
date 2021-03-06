package com.by.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import com.by.mantis.model.Issue;
import com.by.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class SoapHelper {
    private final String password;
    private final String administrator;
    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {

        this.app = app;
        this.administrator = app.getProperty("web.adminLogin");
        this.password = app.getProperty("web.adminPassword");
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(administrator, password);
        return asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("web.mantis.soapUrl")));
        return mc;
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(administrator, password, BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(administrator, password, issueData);
        IssueData createdIssueData = mc.mc_issue_get(administrator, password, issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withDescription(createdIssueData.getDescription()).withSummary(createdIssueData.getSummary())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));
    }

    public boolean isIssueOpen(int issueId) {
        try {
            MantisConnectPortType mc = getMantisConnect();
            if (!mc.mc_issue_exists(administrator, password, BigInteger.valueOf(issueId))) {
                return false;
            }
            IssueData issue = mc.mc_issue_get(administrator, password, BigInteger.valueOf(issueId));
            return !asList("closed", "resolved").contains(issue.getStatus().getName());
        } catch (RemoteException | MalformedURLException | ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
