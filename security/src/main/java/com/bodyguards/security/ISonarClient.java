package com.bodyguards.security;

import java.util.List;
import java.util.Map;

public interface ISonarClient {

    Map<String, List<String>> getIssuesId();

    Map<String, ReportService> getOWASProperty();
}
