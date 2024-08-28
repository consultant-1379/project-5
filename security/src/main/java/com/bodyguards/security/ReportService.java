package com.bodyguards.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ReportService {
    void addReportToService(String key, Report report);

    void removeReportFromService(String key);

    Report getReportByKey(String key);

    void setReports(Map<String, Report> reports);

    List<Report> getAllReports();

    List<Integer> calculateVulnerabilityTotals();

    void calculateIndividualVulnerabilities();
}
