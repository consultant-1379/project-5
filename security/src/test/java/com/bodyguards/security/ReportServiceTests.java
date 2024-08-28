package com.bodyguards.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReportServiceTests {
    Report testReport1;
    Report testReport2;
    TreeMap<IssueType, Integer> testVulnerabilities;

    @BeforeEach
    public void setup(){
        testVulnerabilities = new TreeMap<>();
        testVulnerabilities.put(IssueType.BLOCKER,1);
        testVulnerabilities.put(IssueType.CRITICAL,2);
        testVulnerabilities.put(IssueType.MAJOR,3);
        testVulnerabilities.put(IssueType.MINOR,4);
        testVulnerabilities.put(IssueType.INFO,5);

        testReport1 = new Report("Broken Authenication",testVulnerabilities);
        testReport2 = new Report("Data Injection",testVulnerabilities);
    }

    @Autowired
    ReportService reportService;

    @Test
    public void addReports()	 {
        //Add report and test if added
        reportService.addReportToService("owasp-a5",testReport1);
        //Initialise an arraylist with all reports
        List<Report> reports = reportService.getAllReports();
        assertEquals(1, reports.size());

        // Test that duplicates are not added
        reportService.addReportToService("owasp-a5",testReport1);
        reports = reportService.getAllReports();
        assertEquals(1, reports.size());

        // Test addition on non duplicate report
        reportService.addReportToService("owasp-a1",testReport2);
        reports = reportService.getAllReports();
        assertEquals(2, reports.size());

        //clear report service
        reportService.removeReportFromService("owasp-a5");
        reportService.removeReportFromService("owasp-a1");
    }

    @Test
    public void removeReport()	 {
        TreeMap<IssueType, Integer> testVulnerabilities = new TreeMap<>();

        //create some test reports
        Report testReport1 = new Report("Broken Authenication", testVulnerabilities);

        //Add report and test if added
        reportService.addReportToService("owasp-a5",testReport1);
        //Initialise an arraylist with all reports
        List<Report> reports = reportService.getAllReports();
        assertEquals(1, reports.size());

        reportService.removeReportFromService("owasp-a5");
        reports = reportService.getAllReports();
        assertEquals(0, reports.size());
    }

    @Test
    public void calculateVulnerabilityTotals(){
        reportService.addReportToService("owasp-a5",testReport1);
        reportService.addReportToService("owasp-a1",testReport2);

        List<Integer> results = reportService.calculateVulnerabilityTotals();
        ArrayList<Integer> testResults = new ArrayList<Integer>();
        testResults.add(2);
        testResults.add(4);
        testResults.add(6);
        testResults.add(8);
        testResults.add(10);

        assertEquals(results, testResults);
    }

    @Test
    public void calculateRating(){
        //Should produce E rating as blocker exists
        testReport1.calculateReportRating();
        assertEquals(testReport1.getRating(), 'E');

        //Create a test report which should produce a C rating e.g Major highest priority issue
        testVulnerabilities = new TreeMap<>();
        testVulnerabilities.put(IssueType.BLOCKER,0);
        testVulnerabilities.put(IssueType.CRITICAL,0);
        testVulnerabilities.put(IssueType.MAJOR,3);
        testVulnerabilities.put(IssueType.MINOR,4);
        testVulnerabilities.put(IssueType.INFO,5);

        testReport2 = new Report("Broken Authentication",testVulnerabilities);
        testReport2.calculateReportRating();
        assertEquals(testReport2.getRating(), 'C');
    }

    @Test
    public void createVulnerabilityMap(){

    }

}
