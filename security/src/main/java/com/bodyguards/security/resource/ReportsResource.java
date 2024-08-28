package com.bodyguards.security.resource;


import com.bodyguards.security.Report;
import com.bodyguards.security.repository.ReportsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/database/projects")
public class ReportsResource {

    private ReportsRepository reportsRepository;

    public ReportsResource(ReportsRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }

    @GetMapping("all")
    public List<Report> getAll() {
        return reportsRepository.findAll();
    }
}
