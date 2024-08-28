package com.bodyguards.security;

import com.bodyguards.security.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ReportServiceController {
    @Autowired
    SonarClient sonarClient;

    @Autowired
    ReportsRepository reportsRepository;

    @GetMapping("/")
    public String displayHome(Model model) {
        Map<String, ReportService> projectsMap = sonarClient.getOWASProperty();
        //add project names to model
        model.addAttribute("projects", projectsMap.keySet());

        return "index";
    }

    @GetMapping("/project")
    public String displayProject(Model model, @RequestParam("projectName") String projectName) {
        Map<String, ReportService> projectsMap = sonarClient.getOWASProperty();
        ReportService reportService = projectsMap.get(projectName);
        reportService.calculateIndividualVulnerabilities();
        List<Integer> totalVulnerabilities = reportService.calculateVulnerabilityTotals();

        //save reports to database
        for(Report report : reportService.getAllReports()){
            reportsRepository.save(report);
        }

        //add data to model
        model.addAttribute("project", projectName);
        model.addAttribute("reports", reportService.getAllReports());
        model.addAttribute("totals", reportService.calculateVulnerabilityTotals());

        return "projectView";
    }
}
