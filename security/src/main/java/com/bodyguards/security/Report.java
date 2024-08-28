package com.bodyguards.security;

import com.bodyguards.security.sonarapi.Issue;

import java.util.*;

import static com.bodyguards.security.IssueType.*;

//Resource Class for reports of OWASP Top 10 by categories and severities
public class Report {
    private String category;
    private Character rating = 'A';
    private TreeMap<IssueType, Integer> vulnerabilities = new TreeMap<>();
    private List<Issue> issues = new ArrayList<>();

    //Constructor
    public Report(String category, TreeMap<IssueType, Integer> vulnerabilities) {
        this.category = category;
        this.vulnerabilities = vulnerabilities;
        if(vulnerabilities != null){
            calculateReportRating();
        }
    }

    //Getters and Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Character getRating() {
        return rating;
    }

    public void setRating(Character rating) {
        this.rating = rating;
    }

    public TreeMap<IssueType, Integer> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(TreeMap<IssueType, Integer> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public void addIssue(Issue issue) {
        this.issues.add(issue);
    }

    public List<Issue> getAllIssue() {
        return this.issues;
    }

    public void calculateVulnerability() {
        createVulnerabilityMap();
        for (Issue issue : issues) {
            IssueType issueType = IssueType.valueOf(issue.getSeverity());
            vulnerabilities.put(issueType, vulnerabilities.get(issueType) + 1);
        }
    }


    private void createVulnerabilityMap() {
        vulnerabilities = new TreeMap<>();
        vulnerabilities.put(BLOCKER, 0);
        vulnerabilities.put(CRITICAL, 0);
        vulnerabilities. put(MAJOR, 0);
        vulnerabilities.put(IssueType.MINOR, 0);
        vulnerabilities.put(IssueType.INFO, 0);

    }

    public void calculateReportRating() {
        boolean ratingChange = false;
        for(IssueType key : vulnerabilities.keySet()){
            switch (key) {
                case BLOCKER:
                    if(vulnerabilities.get(BLOCKER) > 0){
                        setRating('E');
                        ratingChange =true;
                    }
                    break;
                case CRITICAL:
                    if(vulnerabilities.get(CRITICAL) > 0){
                        setRating('D');
                        ratingChange =true;
                    }
                    break;
                case MAJOR:
                    if(vulnerabilities.get(MAJOR) > 0){
                        setRating('C');
                        ratingChange =true;
                    }
                    break;
                case MINOR:
                    if(vulnerabilities.get(MINOR) > 0){
                        setRating('B');
                        ratingChange =true;
                    }
                    break;
                case INFO:
                    if(vulnerabilities.get(INFO) > 0){
                        setRating('A');
                        ratingChange =true;
                    }
                    break;
                default:
                    setRating('A');
            }
            if(ratingChange == true){
                break;
            }
        }

    }

    @Override
    public String toString() {
        return "Report{" +
                "category='" + category + '\'' +
                ", rating=" + rating +
                ", vulnerabilities=" + vulnerabilities +
                ", issues=" + issues +
                '}';
    }
}

