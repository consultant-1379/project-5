package com.bodyguards.security;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.Comparator.naturalOrder;

@Component
public class ReportServiceImpl implements ReportService {
    Logger logger = Logger.getLogger(ReportServiceImpl.class);

    private Map<String, Report> reports = new TreeMap<>();

    @Override
    public void addReportToService(String key, Report report) {
        if (reports.containsKey(key)) {
            logger.debug("Issue is Already in List");
        } else {
            reports.put(key, report);
        }
    }

    @Override
    public void removeReportFromService(String key) {
        if (reports.containsKey(key)) {
            reports.remove(key);
        } else {
            logger.debug("Issue does not exist in List");
        }
    }


    @Override
    public Report getReportByKey(String key) {
        return reports.get(key);
    }

    @Override
    public void setReports(Map<String, Report> reports) {
        this.reports = reports;
    }

    @Override
    public List<Report> getAllReports() {
        List<Report> reportArray = new ArrayList<Report>(reports.values());
        //Sort stream in order using get category
        reportArray = reportArray.stream()
                .sorted((report1, report2) -> report1.getCategory().compareTo(report2.getCategory()))
                .collect(Collectors.toCollection(ArrayList::new));
        // temp store A10 and remove and re-add in last position
        // saves having to implement a comparator to order by category - if more time would implement correctly
        if(reportArray.size() > 9){
            Report a10 = reportArray.get(1);
            reportArray.remove(1);
            reportArray.add(a10);
        }
        return reportArray;
    }

    @Override
    public List<Integer> calculateVulnerabilityTotals() {

        List<Integer> results = new ArrayList<Integer>();

        //Initialize an arraylist to be returned
        //each position represents a vulnerability i.e position 0 = CRITICAL, position 3 = MINOR
        for (int i = 0; i < 5; i++) {
            results.add(0);
        }

        List<Report> reportList = getAllReports();

        //Loop over each report and its vulnerabilities keyset
        for (Report report : reportList) {
            for (IssueType key : report.getVulnerabilities().keySet()) {
                //based on the key, then increment the total values
                switch (key) {
                    case BLOCKER:
                        results.set(0, results.get(0) + report.getVulnerabilities().get(key));
                        break;
                    case CRITICAL:
                        results.set(1, results.get(1) + report.getVulnerabilities().get(key));
                        break;
                    case MAJOR:
                        results.set(2, results.get(2) + report.getVulnerabilities().get(key));
                        break;
                    case MINOR:
                        results.set(3, results.get(3) + report.getVulnerabilities().get(key));
                        break;
                    case INFO:
                        results.set(4, results.get(4) + report.getVulnerabilities().get(key));
                        break;
                }
            }
        }
        return results;
    }

    @Override
    public void calculateIndividualVulnerabilities() {
        for(Map.Entry<String,Report> report : reports.entrySet()){
            report.getValue().calculateVulnerability();
            report.getValue().calculateReportRating();
        }
    }
}
