package ru.stqa.pft.mantis.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by user on 17.04.2017.
 */
public class SoapTests extends TestBase{
    @Test
    public  void  testGetProgects() throws MalformedURLException, ServiceException, RemoteException {

        Set<Project> projects = app.soap().getProjects();
        for(Project project : projects){
            System.out.println(project.getName());
        }

    }
    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(0000024);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test Issue").withDescription("Test issue description").
                withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(),created.getSummary());
        for (Project project : projects) {
            System.out.println("Attention: " + project.getStatus());
        }

    }


}
