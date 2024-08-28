package com.bodyguards.security;

import org.springframework.http.HttpHeaders;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static org.codehaus.plexus.util.Base64.encodeBase64;

public class Util {

    private Util() {
    }

    public static Map<String, Report> createReport() {
        Map<String, Report> owsapIssuesBreaDown = new HashMap<>();
        owsapIssuesBreaDown.put("owasp-a1", new Report("A1-Injection", null));
        owsapIssuesBreaDown.put("owasp-a2", new Report("A2-Broken Authentication", null));
        owsapIssuesBreaDown.put("owasp-a3", new Report("A3-Sensitive Data Exposure", null));
        owsapIssuesBreaDown.put("owasp-a4", new Report("A4-XML External Entities (XXE)", null));
        owsapIssuesBreaDown.put("owasp-a5", new Report("A5-Broken Access Control", null));
        owsapIssuesBreaDown.put("owasp-a6", new Report("A6-Security Misconfiguration", null));
        owsapIssuesBreaDown.put("owasp-a7", new Report("A7-Cross-Site Scripting XSS", null));
        owsapIssuesBreaDown.put("owasp-a8", new Report("A8-Insecure Deserialization", null));
        owsapIssuesBreaDown.put("owasp-a9", new Report("A9-Using Components with Known Vulnerabilities", null));
        owsapIssuesBreaDown.put("owasp-a10", new Report("A10-Insufficient Logging & Monitoring", null));
        return owsapIssuesBreaDown;
    }

    public static String getProjectsDataString(Map<String, ReportService> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, ReportService> projectsMap : map.entrySet()) {
            stringBuilder.append(String.format("Project name: %s%n", projectsMap.getKey()));
            for (Report report : projectsMap.getValue().getAllReports()) {
                stringBuilder.append(String.format("category: %s%n", report));
            }
        }
        return stringBuilder.toString();
    }

    public static HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }
}
