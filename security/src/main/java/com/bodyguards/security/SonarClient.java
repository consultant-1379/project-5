package com.bodyguards.security;

import com.bodyguards.security.sonarapi.Issue;
import com.bodyguards.security.sonarapi.IssueResponse;
import com.bodyguards.security.sonarapi.RulesResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Component("SonarClient")
public class SonarClient implements ISonarClient {

    private static String baseAddress = "https://sonarqube.lmera.ericsson.se/api/";

    private static Logger logger = Logger.getLogger(SonarClient.class);
    private static final String TOKEN = "4e99a852fa2f9fdc84dbb1a8270ee2f4cececd7c";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SonarConfiguration sonarConfiguration;

    @Override
    public Map<String, List<String>> getIssuesId() {
        Map<String, List<String>> oWSAPIssuesIdMap = new HashMap<>();
        String url = baseAddress + "rules/search?tags=owasp-a1,owasp-a2,owasp-a3,owasp-a4,owasp-a5,owasp-a6,owasp-a7,owasp-a8,owasp-a9,owasp-a10&languages=java";
        RulesResponse response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<String>(Util.createHeaders(sonarConfiguration.getToken(), "")),
                RulesResponse.class).getBody();
        response.getRules().forEach(rule -> {
            for (String ruleTag : rule.getSysTags()) {
                if (ruleTag.contains("owasp")) {
                    if (oWSAPIssuesIdMap.get(ruleTag) == null) {
                        oWSAPIssuesIdMap.put(ruleTag, new ArrayList<>());
                    }
                    oWSAPIssuesIdMap.get(ruleTag).add(rule.getKey());
                    break;
                }
            }
        });
        return oWSAPIssuesIdMap;
    }

    @Override
    public Map<String, ReportService> getOWASProperty() {
        String url = generateUrl();
        logger.debug("rules url: " + url);
        IssueResponse response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<String>(Util.createHeaders(sonarConfiguration.getToken(), "")),
                IssueResponse.class).getBody();

        Map<String, ReportService> reportMap = new TreeMap<>();

        Map<String, String> ruleToOwasp = generateMap();
        for (Issue issue : response.getIssues()) {
            String projectName = issue.getProject();
            reportMap.computeIfAbsent(projectName, x -> {
                ReportService reportService = new ReportServiceImpl();
                reportService.setReports(Util.createReport());
                return reportService;
            });
            reportMap.get(projectName).getReportByKey(ruleToOwasp.get(issue.getRule())).addIssue(issue);
        }
        return reportMap;
    }

    private Map<String, String> generateMap() {
        Map<String, List<String>> oWSAPIssuesCodeMap = getIssuesId();
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : oWSAPIssuesCodeMap.entrySet()) {
            for (String s : entry.getValue()) {
                map.put(s, entry.getKey());
            }
        }
        return map;
    }

    private String generateUrl() {
        Map<String, List<String>> oWSAPIssuesCodeMap = getIssuesId();
        StringBuilder url = new StringBuilder(baseAddress + "issues/search?rules=");
        for (Map.Entry<String, List<String>> entry : oWSAPIssuesCodeMap.entrySet()) {
            for (String tag : entry.getValue()) {
                url.append(tag + ",");
            }
        }
        url.deleteCharAt(url.length() - 1);
        url.append("&types=VULNERABILITY");
        return url.toString();
    }


}


